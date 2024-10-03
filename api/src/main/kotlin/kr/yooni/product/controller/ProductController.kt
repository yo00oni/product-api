package kr.yooni.product.controller

import kr.yooni.product.common.http.Response
import kr.yooni.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/brands/{brandId}/categories/{categoryId}/price")
    fun findBrandByCategoryPrice(): Response<String> {
        return Response.ok()
    }

    @GetMapping("/brands/{brandId}/categories/price")
    fun findBrandByAppCategoryPrice(): Response<String> {
        return Response.ok()
    }
}
