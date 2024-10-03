package kr.yooni.product.common.http

import kr.yooni.product.common.validation.ValidationsDescriber

class ValidationMessage(override val code: String, override val message: String, val data: List<ValidationsDescriber>) :
    ResponseMessage
