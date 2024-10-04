package kr.yooni.product.database.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "category")
@DynamicUpdate
open class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 100)
    @NotNull
    @Column(name = "type", nullable = false, length = 100)
    open var type: String? = null

    companion object {
        fun from(dto: CategoryDto): Category {
            return Category().apply {
                type = dto.type
            }
        }
    }
}

open class CategoryDto(
    open var id: Int? = null,
    open var type: String,
) {
    companion object {
        fun fromDto(entity: Category): CategoryDto {
            return CategoryDto(entity.id, entity.type!!)
        }
    }

    override fun toString(): String {
        return "카테고리 ID : $id, 카테고리 타입 : $type"
    }
}
