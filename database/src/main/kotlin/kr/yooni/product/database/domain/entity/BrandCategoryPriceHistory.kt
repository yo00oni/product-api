package kr.yooni.product.database.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "brand_category_price_history")
open class BrandCategoryPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @Column(name = "brand_category_id", nullable = false)
    open var brandCategoryId: Int? = null

    @NotNull
    @Column(name = "price", nullable = false)
    open var price: Int? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_at")
    open var createAt: Instant? = null
}
