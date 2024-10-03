package kr.yooni.product.data

import com.fasterxml.jackson.annotation.JsonIgnore
import kr.yooni.product.database.domain.entity.CategoryDto

class CategoryCreationRequest(
    type: String
) : CategoryDto(
    type = type
) {
    @JsonIgnore
    override var id: Int? = null
}
