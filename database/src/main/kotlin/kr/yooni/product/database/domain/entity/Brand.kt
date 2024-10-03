package kr.yooni.product.database.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "brand")
open class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    open var name: String? = null

    companion object {
        fun from(dto: BrandDto): Brand {
            return Brand().apply {
                name = dto.name
            }
        }
    }
}

open class BrandDto(
    open var id: Int? = null,
    open var name: String,
) {
    companion object {
        fun fromDto(entity: Brand): BrandDto {
            return BrandDto(entity.id, entity.name!!)
        }
    }

    override fun toString(): String {
        return "브랜드 ID : $id, 브랜드 이름 : $name"
    }
}
