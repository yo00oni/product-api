package kr.yooni.product.controller

import kr.yooni.product.common.http.Response
import kr.yooni.product.data.BrandCreationRequest
import kr.yooni.product.data.CategoryCreationRequest
import kr.yooni.product.database.domain.entity.BrandCategoryDto
import kr.yooni.product.database.domain.entity.BrandDto
import kr.yooni.product.database.domain.entity.CategoryDto
import kr.yooni.product.service.AdminProductService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 운영자에게 브랜드, 카테고리, 상품의 가격을 관리 할수 있는 API 제공합니다.
 *1. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다.
 *
 */
@RestController
@RequestMapping("/v1/admin/products")
class AdminProductController(
    private val adminProductService: AdminProductService
) {

    @GetMapping("/brands")
    fun findBrandList(): Response<List<BrandDto>> {
        return Response.okFrom(adminProductService.findBrandList())
    }

    @PostMapping("/brands")
    fun createBrand(
        @RequestBody @Validated brandDto: BrandCreationRequest,
    ): Response<String> {
        adminProductService.createBrand(brandDto)
        return Response.ok()
    }

    @PutMapping("/brands/{brandId}")
    fun updateBrand(
        @PathVariable("brandId") brandId: Int,
        @RequestBody brandName: String,
    ): Response<String> {
        adminProductService.updateBrand(brandId, brandName)
        return Response.ok()
    }

    @DeleteMapping("/brands/{brandId}")
    fun deleteBrand(
        @PathVariable("brandId") brandId: Int,
    ): Response<String> {
        adminProductService.deleteBrand(brandId)
        return Response.ok()
    }

    @GetMapping("/categories")
    fun findCategoryList(): Response<List<CategoryDto>> {
        return Response.okFrom(adminProductService.findCategoryList())
    }

    @PostMapping("/categories")
    fun createCategory(
        @RequestBody @Validated categoryDto: CategoryCreationRequest,
    ): Response<List<String>> {
        adminProductService.createCategory(categoryDto)
        return Response.ok()
    }

    @PutMapping("/categories/{categoryId}")
    fun updateCategory(
        @PathVariable("categoryId") categoryId: Int,
        @RequestBody categoryType: String,
    ): Response<String> {
        adminProductService.updateCategory(categoryId, categoryType)
        return Response.ok()
    }

    @DeleteMapping("/categories/{categoryId}")
    fun deleteCategory(
        @PathVariable("categoryId") categoryId: Int,
    ): Response<String> {
        adminProductService.deleteCategory(categoryId)
        return Response.ok()
    }

    @GetMapping("/{brandId}/category-prices")
    fun findCategoryPricesByBrand(
        @PathVariable("brandId") brandId: Int,
    ): Response<List<BrandCategoryDto>> {
        return Response.okFrom(adminProductService.findCategoryPricesByBrand(brandId))
    }

    @PutMapping("/{brandId}/{categoryId}")
    fun updatePrice(
        @PathVariable("brandId") brandId: Int,
        @PathVariable("categoryId") categoryId: Int,
        @RequestBody price: Int,
    ): Response<String> {
        adminProductService.updatePrice(brandId, categoryId, price)
        return Response.ok()
    }
}
