package kr.yooni.product.database.domain.repository

import kr.yooni.product.database.domain.entity.BrandCategory
import org.springframework.data.jpa.repository.JpaRepository

interface BrandCategoryRepository : JpaRepository<BrandCategory, Int> {
    fun findAllByBrandId(brandId: Int): List<BrandCategory>
    fun findByBrandIdAndCategoryId(brandId: Int, categoryId: Int): BrandCategory
}
