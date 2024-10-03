package kr.yooni.product.common.validation

import kr.yooni.product.common.exception.GlobalHttpException
import kr.yooni.product.common.http.Response
import org.springframework.http.HttpStatus

class ConstraintViolationWrappedException(
    override val code: String,
    override val message: String,
    httpStatus: HttpStatus
) : GlobalHttpException(code = code, message = message, httpStatus = httpStatus) {
    constructor() : this(
        Response.ResponseCode.ERROR_INTERNAL.code,
        Response.ResponseCode.ERROR_INTERNAL.message,
        HttpStatus.INTERNAL_SERVER_ERROR
    )

    constructor(validationsDescriber: ValidationsDescriber, httpStatus: HttpStatus) : this(
        validationsDescriber.message,
        validationsDescriber.fieldName,
        httpStatus
    )
}
