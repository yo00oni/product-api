package kr.yooni.product.database.domain.aggregate

import kr.yooni.product.database.domain.entity.Brand
import kr.yooni.product.database.domain.entity.BrandDto
import kr.yooni.product.database.domain.entity.Category
import kr.yooni.product.database.domain.entity.CategoryDto
import kr.yooni.product.database.domain.repository.BrandCategoryRepository
import kr.yooni.product.database.domain.repository.BrandRepository
import kr.yooni.product.database.domain.repository.CategoryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductAggregate(
    private val brandRepository: BrandRepository,
    private val categoryRepository: CategoryRepository,
    private val brandCategoryRepository: BrandCategoryRepository,
) {
    fun findAllBrands(): List<String> {
        return brandRepository.findAllBy().map {
            brand ->
            BrandDto.fromDto(brand).toString()
        }
    }

    fun findAllCategory(): List<String> {
        return categoryRepository.findAllBy().map {
            category ->
            CategoryDto.fromDto(category).toString()
        }
    }

    fun findBrandByAllCategoryAndPrice(brandId: Int): List<String> {
        return brandCategoryRepository.findAllByBrandId(brandId).map {
            brandCategory ->
            "브랜드+카테고리ID : ${brandCategory.categoryId} , 가격 : ${brandCategory.price}"
        }
    }

    @Transactional
    fun createBrand(brandDto: BrandDto) {
        val entity = Brand.from(brandDto)
        brandRepository.save(entity)
    }

    fun updateBrand(brandId: Int, brandName: String) {
        val brand = brandRepository.findById(brandId).orElseThrow { throw Exception() }
        brand.name = brandName
        brandRepository.save(brand)
    }

    fun deleteBrand(brandId: Int) {
        brandRepository.deleteById(brandId)
    }

    @Transactional
    fun createCategory(categoryDto: CategoryDto) {
        val entity = Category.from(categoryDto)
        categoryRepository.save(entity)
    }

    fun updateCategory(categoryId: Int, categoryType: String) {
        val category = categoryRepository.findById(categoryId).orElseThrow { throw Exception() }
        category.type = categoryType
        categoryRepository.save(category)
    }

    fun deleteCategory(categoryId: Int) {
        categoryRepository.deleteById(categoryId)
    }

    fun updatePrice(brandId: Int, categoryId: Int, price: Int) {
        val brandCategory = brandCategoryRepository.findByBrandIdAndCategoryId(brandId, categoryId)
        brandCategory.price = price
        brandCategoryRepository.save(brandCategory)
    }
}
