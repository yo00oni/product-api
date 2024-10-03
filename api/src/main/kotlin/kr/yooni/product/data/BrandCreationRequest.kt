package kr.yooni.product.data

import com.fasterxml.jackson.annotation.JsonIgnore
import kr.yooni.product.database.domain.entity.BrandDto

class BrandCreationRequest(
    name: String
) : BrandDto(
    name = name
) {
    @JsonIgnore
    override var id: Int? = null
}
