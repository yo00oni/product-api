package kr.yooni.product.database.domain.repository

import kr.yooni.product.database.domain.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Int> {
    fun findAllBy(): List<Category>
}
