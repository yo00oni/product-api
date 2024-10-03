package kr.yooni.product.common.exception

import kr.yooni.product.common.http.Response

open class GlobalException(
    open val code: String,
    message: String?,
    ex: Exception? = null,
) : RuntimeException(message, ex?.cause) {

    constructor(
        responseCode: Response.ResponseCode = Response.ResponseCode.ERROR_INTERNAL,
        ex: Exception? = null,
    ) : this(
        code = responseCode.code,
        message = responseCode.message,
        ex = ex,
    )
}
