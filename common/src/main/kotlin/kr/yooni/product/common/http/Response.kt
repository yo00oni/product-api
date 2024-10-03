package kr.yooni.product.common.http

import com.fasterxml.jackson.annotation.JsonInclude
import kr.yooni.product.common.exception.GlobalException
import kr.yooni.product.common.exception.GlobalHttpException
import kr.yooni.product.common.validation.ValidationsDescriber
import org.springframework.context.support.MessageSourceAccessor

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T> constructor(var code: String, var message: String, var data: T? = null) {

    companion object {
        // 응답코드/ 메시지/ payload용 응답
        fun <T> of(code: String, message: String, data: T? = null): Response<T> =
            Response(code = code, message = message, data = data)

        fun <T> from(responseMessage: ResponseMessage, data: T? = null): Response<T> =
            Response(code = responseMessage.code, message = responseMessage.message, data = data)

        fun <T> okFrom(data: T? = null): Response<T> =
            Response(code = ResponseCode.OK.code, message = ResponseCode.OK.message, data = data)

        fun <T> ok(): Response<T> =
            Response(code = ResponseCode.OK.code, message = ResponseCode.OK.message)

        fun errorFrom(exception: Exception): Response<String> =
            Response(
                code = ResponseCode.ERROR_INTERNAL.code,
                message = exception.message
                    ?: ResponseCode.ERROR_INTERNAL.message
            )

        fun errorFrom(responseMessage: ResponseMessage): Response<String> =
            Response(code = responseMessage.code, message = responseMessage.message)

        fun errorFrom(responseMessage: ValidationMessage): Response<List<ValidationsDescriber>> =
            Response(code = responseMessage.code, message = responseMessage.message, data = responseMessage.data)

        fun errorFrom(exception: GlobalException): Response<String> =
            Response(
                code = exception.code,
                message = exception.message
                    ?: ResponseCode.ERROR_INTERNAL.message
            )

        fun errorFrom(exception: GlobalHttpException): Response<String> =
            Response(
                code = exception.code,
                message = exception.message
                    ?: ResponseCode.ERROR_INTERNAL.message
            )

        fun <T> of(
            code: String,
            message: String,
            data: T? = null,
            messageSourceAccessor: MessageSourceAccessor
        ): Response<T> =
            Response(
                code = code,
                message = messageSourceAccessor.getMessage(code).takeIf { it -> it.isNotEmpty() } ?: message,
                data = data
            )

        fun from(message: Map<*, *>, messageSourceAccessor: MessageSourceAccessor): Response<Any> {
            if (message.containsKey("code") && message.containsKey("message")) {
                return Response(
                    code = message["code"] as String,
                    message = messageSourceAccessor.getMessage(message["code"] as String).takeIf { it -> it.isNotEmpty() } ?: message["message"] as String,
                    data = message["data"]
                )
            } else {
                throw IllegalArgumentException(ResponseCode.ERROR_SERVLET_DESERIALIZED.code)
            }
        }
    }

    enum class ResponseCode(override val code: String, override val message: String) : ResponseMessage {
        OK("code.ok.200", "성공"),
        ERROR_INTERNAL("error.internal.500", "예기치 못한 오류가 발생 했습니다."),
        ERROR_SERVLET_DESERIALIZED("error.servlet-deserialized.500", "응답 생성중 오류가 발생했습니다."),

        ERROR_INTERNAL_FROM_SERVER("error.internal.501", "연계 서버로 부터 오류 응답을 수신 했습니다."),
        ERROR_SERVICE_NOT_AVAILABLE("error.internal.502", "서비스가 원활 하지 않습니다."),
        BAD_REQUEST("validation.bad-request.400", "요청 정보를 확인 하세요."),
        NOT_FOUND("error.not-found.404", "존재하지 않는 경로의 요청 입니다."),
        UNAUTHORIZED_ACCESS_DENIED("error.not.authorized.401", "접근 권한이 없습니다."),
        DUPLICATED_DATA("error.duplicated-data.401", "중복된 데이터가 존재합니다."),
        ;

        companion object {
            fun findByCode(code: String?): ResponseCode? {
                return ResponseCode.values().firstOrNull {
                    code == it.code
                }
            }
        }
    }
}
