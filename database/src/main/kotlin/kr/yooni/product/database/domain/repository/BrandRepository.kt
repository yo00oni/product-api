package kr.yooni.product.database.domain.repository

import kr.yooni.product.database.domain.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Int> {
    fun findAllBy(): List<Brand>
}
