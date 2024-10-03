package kr.yooni.product.database.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "brand_category")
open class BrandCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @Column(name = "brand_id", nullable = false)
    open var brandId: Int? = null

    @NotNull
    @Column(name = "category_id", nullable = false)
    open var categoryId: Int? = null

    @ColumnDefault("0")
    @Column(name = "price")
    open var price: Int? = null
}

open class BrandCategoryDto(
    open var id: Int? = null,
    open var brandId: Int,
    open var categoryId: Int,
    open var price: Int? = null,
) {
    companion object {
        fun fromDto(entity: BrandCategory): BrandCategoryDto {
            return BrandCategoryDto(entity.id, entity.brandId!!, entity.categoryId!!, entity.price)
        }
    }

    override fun toString(): String {
        return "브랜드 카테고리 ID : $id, 브랜드 ID : $brandId, 카테고리 ID : $categoryId, 가격 : $price"
    }
}
