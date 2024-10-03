package kr.yooni.product.service

import kr.yooni.product.database.domain.aggregate.ProductAggregate
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productAggregate: ProductAggregate
) {

    fun findBrandList(): String {
        return productAggregate.findAllBrands().joinToString()
    }

    fun findCategoryList(): String {
        return productAggregate.findAllCategory().joinToString()
    }

    fun findBrandByCategoryPrice(): String {
        return "Brand By Category Price"
    }

    fun findBrandByAppCategoryPrice(): String {
        return "Brand By App Category Price"
    }
}
