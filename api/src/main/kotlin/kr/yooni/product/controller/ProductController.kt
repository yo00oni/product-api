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

/**
 * 고객에게 제공될 상품 API를 제공합니다.
 */
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

    /**
     * 1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.
     *
     */
    @GetMapping("/lowest-price")
    fun findLowestPricedProduct(): Response<LowestPriceProduct> {
        return Response.okFrom(productService.findLowestPricedProduct())
    }

    /**
     * 2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.
     */
    @GetMapping("/lowest-price/{brandId}")
    fun findLowestPricedProductByBrandId(
        @PathVariable("brandId") brandId: Int
    ): Response<LowestPriceProductByBrand> {
        return Response.okFrom(productService.findLowestPricedProductByBrand(brandId))
    }

    /**
     * 3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.
     */
    @GetMapping("/min-max-price/{categoryName}")
    fun findLowestPricedProductByCategoryName(
        @PathVariable("categoryName") categoryName: String
    ): Response<CategoryPriceInfo> {
        return Response.okFrom(productService.findMinMaxPriceByCategory(categoryName))
    }
}
