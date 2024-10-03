package kr.yooni.product.controller

import kr.yooni.product.common.http.Response
import kr.yooni.product.database.domain.aggregate.CategoryPriceInfo
import kr.yooni.product.database.domain.aggregate.LowestPriceProduct
import kr.yooni.product.database.domain.aggregate.LowestPriceProductByBrand
import kr.yooni.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/brands")
    fun findBrandList(): Response<String> {
        return Response.okFrom(productService.findBrandList())
    }

    @GetMapping("/cateogries")
    fun findCategoryList(): Response<String> {
        return Response.okFrom(productService.findCategoryList())
    }

    @GetMapping("/lowest-price")
    fun findLowestPricedProduct(): Response<LowestPriceProduct> {
        return Response.okFrom(productService.findLowestPricedProduct())
    }

    @GetMapping("/lowest-price/{brandId}")
    fun findLowestPricedProductByBrandId(
        @PathVariable("brandId") brandId: Int
    ): Response<LowestPriceProductByBrand> {
        return Response.okFrom(productService.findLowestPricedProductByBrand(brandId))
    }

    @GetMapping("/min-max-price/{categoryName}")
    fun findLowestPricedProductByCategoryName(
        @PathVariable("categoryName") categoryName: String
    ): Response<CategoryPriceInfo> {
        return Response.okFrom(productService.findMinMaxPriceByCategory(categoryName))
    }
}
