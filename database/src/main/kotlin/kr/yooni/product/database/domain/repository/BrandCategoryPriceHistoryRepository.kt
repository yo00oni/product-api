package kr.yooni.product.database.domain.repository

import kr.yooni.product.database.domain.entity.BrandCategoryPriceHistory
import org.springframework.data.jpa.repository.JpaRepository

interface BrandCategoryPriceHistoryRepository : JpaRepository<BrandCategoryPriceHistory, Int> {
    fun findAllBy(): List<BrandCategoryPriceHistory>
}
