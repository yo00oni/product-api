package kr.yooni.product.common.exception

import kr.yooni.product.common.http.Response
import kr.yooni.product.common.http.ResponseMessage
import org.springframework.http.HttpStatus

open class GlobalHttpException(
    code: String,
    message: String? = null,
    ex: Exception? = null,
    val httpStatus: HttpStatus,
) : GlobalException(code, message, ex) {

    constructor(
        responseCode: Response.ResponseCode,
        httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        ex: Exception? = null
    ) : this(
        code = responseCode.code,
        message = responseCode.message,
        httpStatus = httpStatus,
        ex = ex,
    )

    constructor(
        responseMessage: ResponseMessage,
        httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        ex: Exception? = null
    ) : this(
        code = responseMessage.code,
        message = responseMessage.message,
        httpStatus = httpStatus,
        ex = ex,
    )
}
