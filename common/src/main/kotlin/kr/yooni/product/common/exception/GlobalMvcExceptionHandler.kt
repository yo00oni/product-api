package kr.yooni.product.common.exception

import jakarta.validation.ConstraintViolationException
import kr.yooni.product.common.http.Response
import kr.yooni.product.common.http.ValidationMessage
import kr.yooni.product.common.validation.ConstraintViolationWrappedException
import kr.yooni.product.common.validation.ValidationWrapper
import kr.yooni.product.common.validation.ValidationsDescriber
import org.slf4j.LoggerFactory
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalMvcExceptionHandler {
    val log = LoggerFactory.getLogger(this::class.java)

//     @ExceptionHandler(UnexpectedTypeException::class)
//     fun handleException(ex: UnexpectedTypeException): ResponseEntity<Response<String>> {
//         ex.printStackTrace()
//         ex.
//         val e = ValidationWrapper.from(ex) ?: ConstraintViolationWrappedException()
//
//         return handleWrappedConstraintViolationException(e)
//     }

    /**
     * Spring Validator에 의해서 발생되는 Validation 오류를 catch
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        val e = ValidationWrapper.from(ex) ?: ConstraintViolationWrappedException()

        return handleWrappedConstraintViolationException(e)
    }

    /**
     * ConstraintViolationWrappedException 처리 핸들러
     * Custom Exception을 처리.
     */
    @ExceptionHandler(ConstraintViolationWrappedException::class)
    fun handleWrappedConstraintViolationException(ex: ConstraintViolationWrappedException): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        return ResponseEntity(Response.errorFrom(ex), HttpStatus.BAD_REQUEST)
    }

    /**
     * Presentation Layer로 부터 필수 입력 정보가 Validation Check가 실패했을 경우 Exception Catch
     */
    @ExceptionHandler(org.springframework.validation.BindException::class)
    fun handleValidationException(ex: org.springframework.validation.BindException): ResponseEntity<Response<List<ValidationsDescriber>>> {
        ex.printStackTrace()
        val validationsDescribes = ex.bindingResult.fieldErrors.map { error ->
            ValidationsDescriber(
                fieldName = error.field,
                message = error.code ?: error.defaultMessage ?: Response.ResponseCode.BAD_REQUEST.code
            )
        }.toList()

        val responseMessage = ValidationMessage(
            code = Response.ResponseCode.BAD_REQUEST.code,
            message = Response.ResponseCode.BAD_REQUEST.message,
            data = validationsDescribes
        )
        return ResponseEntity(Response.errorFrom(responseMessage), HttpStatus.BAD_REQUEST)
    }

    /**
     * GlobalHttpException 처리
     */
    @ExceptionHandler(GlobalHttpException::class)
    fun handleGlobalHttpException(ex: GlobalHttpException): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        return ResponseEntity(Response.errorFrom(ex), ex.httpStatus)
    }

    /**
     * GlobalException 처리
     */
    @ExceptionHandler(GlobalException::class)
    fun handleGlobalException(ex: GlobalException): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        return ResponseEntity(Response.errorFrom(ex), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        return ResponseEntity(Response.errorFrom(ex), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(
        HttpRequestMethodNotSupportedException::class,
        HttpMediaTypeNotSupportedException::class,
        HttpMediaTypeNotAcceptableException::class,
        MissingPathVariableException::class,
        HttpMessageNotWritableException::class,
        MissingServletRequestParameterException::class,
        ServletRequestBindingException::class,
        TypeMismatchException::class,
        HttpMessageNotReadableException::class,
        MissingServletRequestPartException::class,
        NoHandlerFoundException::class,
        AsyncRequestTimeoutException::class,
        NoResourceFoundException::class
    )
    fun handleMvcException(ex: Exception): ResponseEntity<Response<String>> {
        ex.printStackTrace()
        var status = HttpStatus.INTERNAL_SERVER_ERROR
        var responseMessage = Response.ResponseCode.ERROR_INTERNAL
        if (ex is HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED
        } else if (ex is HttpMediaTypeNotSupportedException) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE
        } else if (ex is HttpMediaTypeNotAcceptableException) {
            status = HttpStatus.NOT_ACCEPTABLE
        } else if (ex is MissingPathVariableException || ex is ConversionNotSupportedException ||
            ex is HttpMessageNotWritableException
        ) {
            status = HttpStatus.INTERNAL_SERVER_ERROR
        } else if (ex is MissingServletRequestParameterException ||
            ex is ServletRequestBindingException ||
            ex is TypeMismatchException ||
            ex is HttpMessageNotReadableException ||
            ex is MissingServletRequestPartException
        ) {
            responseMessage = Response.ResponseCode.BAD_REQUEST
            status = HttpStatus.BAD_REQUEST
        } else if (ex is NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND
        } else if (ex is AsyncRequestTimeoutException) {
            status = HttpStatus.SERVICE_UNAVAILABLE
        } else if (ex is NoResourceFoundException) {
            responseMessage = Response.ResponseCode.NOT_FOUND
            status = HttpStatus.NOT_FOUND
        }
        val e = GlobalHttpException(
            code = responseMessage.code,
            message = responseMessage.message ?: "페이지 를 찾을수 없어요",
            httpStatus = status,
        )

        return ResponseEntity(Response.errorFrom(e), status)
    }
}
