package kr.yooni.product.service

import kr.yooni.product.database.domain.aggregate.CategoryPriceInfo
import kr.yooni.product.database.domain.aggregate.LowestPriceProduct
import kr.yooni.product.database.domain.aggregate.LowestPriceProductByBrand
import kr.yooni.product.database.domain.aggregate.ProductAggregate
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productAggregate: ProductAggregate
) {
    fun findLowestPricedProduct(): LowestPriceProduct {
        return productAggregate.findLowestPricedProduct()
    }

    fun findLowestPricedProductByBrand(brandId: Int): LowestPriceProductByBrand {
        return productAggregate.findLowestPricedProductByBrand(brandId)
    }

    fun findMinMaxPriceByCategory(categoryName: String): CategoryPriceInfo? {
        return productAggregate.findMinMaxPriceByCategory(categoryName)
    }
}
