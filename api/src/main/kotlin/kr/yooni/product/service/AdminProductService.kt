package kr.yooni.product.service

import kr.yooni.product.common.exception.GlobalHttpException
import kr.yooni.product.common.http.Response
import kr.yooni.product.database.domain.aggregate.ProductAggregate
import kr.yooni.product.database.domain.entity.BrandDto
import kr.yooni.product.database.domain.entity.CategoryDto
import org.springframework.stereotype.Service

@Service
class AdminProductService(
    private val productAggregate: ProductAggregate,
) {
    fun findBrandList(): List<String> {
        return productAggregate.findAllBrands()
    }

    fun findCategoryList(): List<String> {
        return productAggregate.findAllCategory()
    }

    fun findCategoryPricesByBrand(brandId: Int): List<String> {
        return productAggregate.findBrandByAllCategoryAndPrice(brandId)
    }

    fun createBrand(brandDto: BrandDto) {
        try {
            productAggregate.createBrand(brandDto)
        } catch (e: Exception) {
            throw GlobalHttpException(Response.ResponseCode.DUPLICATED_DATA)
        }
    }

    fun updateBrand(brandId: Int, brandName: String) {
        productAggregate.updateBrand(brandId, brandName)
    }

    fun deleteBrand(brandId: Int) {
        productAggregate.deleteBrand(brandId)
    }

    fun createCategory(categoryDto: CategoryDto) {
        productAggregate.createCategory(categoryDto)
    }

    fun updateCategory(categoryId: Int, categoryType: String) {
        productAggregate.updateCategory(categoryId, categoryType)
    }

    fun deleteCategory(categoryId: Int) {
        productAggregate.deleteCategory(categoryId)
    }

    fun updatePrice(brandId: Int, categoryId: Int, price: Int) {
        productAggregate.updatePrice(brandId, categoryId, price)
    }
}
