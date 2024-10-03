package kr.yooni.product.common.filter

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.yooni.product.common.http.Response
import org.slf4j.LoggerFactory
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.StandardCharsets

// TODO : FilterConfig 설정 주석처리로 미사용
class MessageConvertFilter(private val messageSourceAccessor: MessageSourceAccessor) : Filter {

    private val log = LoggerFactory.getLogger(this::class.java)
    private val gson: Gson = Gson()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if (!(request is HttpServletRequest && response is HttpServletResponse)) {
            throw ServletException("I18nFilter only supports HTTP requests")
        }
        doFilterInternal(request, response, chain)
    }

    private fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val contentCachingRequestWrapper = ContentCachingRequestWrapper(request)
        val contentCachingResponseWrapper = ContentCachingResponseWrapper(response)

        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper)

        contentCachingResponseWrapper.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)

        val payload: String? = String(contentCachingResponseWrapper.contentAsByteArray)

        // TODO: XML 응답 다 없어지면, 제거
        val responseBody = try {
            gson.fromJson(payload, Map::class.java)
        } catch (
            e: JsonSyntaxException
        ) {
            log.error(e.message)
            mapOf<String, String>("data" to payload.toString())
        }

        runCatching {
            response.outputStream.write(
                gson.toJson(Response.from(responseBody, messageSourceAccessor)).toByteArray(StandardCharsets.UTF_8)
            )
        }.onFailure {
            log.error(it.message)
            runCatching {
                response.outputStream.write(
                    gson.toJson(convertToResponse(contentCachingResponseWrapper, responseBody)).toByteArray(StandardCharsets.UTF_8)
                )
            }.onFailure {
                log.error(it.message)
            }
        }
    }

    private fun convertToResponse(response: HttpServletResponse, responseBody: Map<*, *>?): Response<*> {
        val status = HttpStatus.valueOf(response.status)
        when (status.series().value()) {
            // Http status 2XX
            2 -> return Response.of(
                Response.ResponseCode.OK.code,
                messageSourceAccessor.getMessage(Response.ResponseCode.OK.code)
                    .takeIf { it -> it.isNotEmpty() } ?: Response.ResponseCode.OK.message,
                responseBody
            )
            // Http status 4XX
            4 -> return Response.of(
                code = Response.ResponseCode.BAD_REQUEST.code,
                message = messageSourceAccessor.getMessage(Response.ResponseCode.BAD_REQUEST.code)
                    .takeIf { it -> it.isNotEmpty() } ?: Response.ResponseCode.BAD_REQUEST.message,
                data = responseBody
            )
            5 -> return Response.of(
                code = Response.ResponseCode.ERROR_INTERNAL.code,
                message = messageSourceAccessor.getMessage(Response.ResponseCode.ERROR_INTERNAL.code)
                    .takeIf { it -> it.isNotEmpty() } ?: Response.ResponseCode.ERROR_INTERNAL.message,
                data = responseBody
            )
            else -> return Response.of(
                code = Response.ResponseCode.ERROR_SERVLET_DESERIALIZED.code,
                message = messageSourceAccessor.getMessage(Response.ResponseCode.ERROR_SERVLET_DESERIALIZED.code,)
                    .takeIf { it -> it.isNotEmpty() } ?: Response.ResponseCode.ERROR_SERVLET_DESERIALIZED.message,
                data = responseBody,
            )
        }
    }
}
