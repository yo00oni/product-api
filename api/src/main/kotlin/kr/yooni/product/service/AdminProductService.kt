package kr.yooni.product.service

import kr.yooni.product.common.exception.GlobalHttpException
import kr.yooni.product.common.http.Response
import kr.yooni.product.database.domain.aggregate.ProductAggregate
import kr.yooni.product.database.domain.entity.BrandCategoryDto
import kr.yooni.product.database.domain.entity.BrandDto
import kr.yooni.product.database.domain.entity.CategoryDto
import org.springframework.stereotype.Service

@Service
class AdminProductService(
    private val productAggregate: ProductAggregate,
) {
    fun findBrandList(): List<BrandDto> {
        return productAggregate.findAllBrands()
    }

    fun findCategoryList(): List<CategoryDto> {
        return productAggregate.findAllCategory()
    }

    fun findCategoryPricesByBrand(brandId: Int): List<BrandCategoryDto> {
        return productAggregate.findBrandByAllCategoryAndPrice(brandId)
    }

    fun createBrand(brandDto: BrandDto) {
        kotlin.runCatching {
            productAggregate.createBrand(brandDto)
        }.onFailure { exception ->
            throw GlobalHttpException(Response.ResponseCode.DUPLICATED_DATA)
        }
    }

    fun updateBrand(brandId: Int, brandName: String) {
        kotlin.runCatching {
            productAggregate.updateBrand(brandId, brandName)
        }.onFailure { exception ->
            throw GlobalHttpException(Response.ResponseCode.NOT_FOUND)
        }
    }

    fun deleteBrand(brandId: Int) {
        productAggregate.deleteBrand(brandId)
    }

    fun createCategory(categoryDto: CategoryDto) {
        kotlin.runCatching {
            productAggregate.createCategory(categoryDto)
        }.onFailure { exception ->
            throw GlobalHttpException(Response.ResponseCode.DUPLICATED_DATA)
        }
    }

    fun updateCategory(categoryId: Int, categoryType: String) {
        kotlin.runCatching {
            productAggregate.updateCategory(categoryId, categoryType)
        }.onFailure { exception ->
            throw GlobalHttpException(Response.ResponseCode.NOT_FOUND)
        }
    }

    fun deleteCategory(categoryId: Int) {
        productAggregate.deleteCategory(categoryId)
    }

    fun updatePrice(brandId: Int, categoryId: Int, price: Int) {
        kotlin.runCatching {
            productAggregate.updatePrice(brandId, categoryId, price)
        }.onFailure { exception ->
            throw GlobalHttpException(Response.ResponseCode.NOT_FOUND)
        }
    }
}
