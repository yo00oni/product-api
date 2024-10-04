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
            "카테고리 정보 : ${brandCategory.categoryId}, 가격 : ${brandCategory.price}"
        }
    }

    @Transactional
    fun createBrand(brandDto: BrandDto) {
        val entity = Brand.from(brandDto)
        brandRepository.save(entity)
    }

    @Transactional
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

    @Transactional
    fun updateCategory(categoryId: Int, categoryType: String) {
        val category = categoryRepository.findById(categoryId).orElseThrow { throw Exception() }
        category.type = categoryType
        categoryRepository.save(category)
    }

    fun deleteCategory(categoryId: Int) {
        categoryRepository.deleteById(categoryId)
    }

    @Transactional
    fun updatePrice(brandId: Int, categoryId: Int, price: Int) {
        val brandCategory = brandCategoryRepository.findByBrandIdAndCategoryId(brandId, categoryId)
        brandCategory.price = price
        brandCategoryRepository.save(brandCategory)
    }

    fun findLowestPricedProduct(): LowestPriceProduct {

        val lowestPriceProduct = LowestPriceProduct(
            items = mutableListOf(),
            total = 0
        )
        val categories = categoryRepository.findAllBy()

        categories.forEach { category ->
            // 해당 카테고리에서 모든 제품 조회
            val productList = category.id?.let { categoryId ->
                brandCategoryRepository.findAllByCategoryId(categoryId)
            } ?: emptyList()

            // 카테고리 내 최저가 상품 찾기
            val minProduct = productList.minByOrNull { it.price ?: Int.MAX_VALUE }
            // 최저가 상품이 있는 경우 처리
            if (minProduct != null) {
                val brand = minProduct.brandId?.let { brandRepository.findById(it).orElse(null) }
                val categoryEntity = minProduct.categoryId?.let { categoryRepository.findById(it).orElse(null) }
                val price = minProduct.price ?: 0

                // 모든 정보가 있는 경우에만 추가
                if (brand != null && categoryEntity != null) {
                    lowestPriceProduct.items.add(
                        Product(
                            brandId = minProduct.brandId!!,
                            brandName = brand.name ?: "Unknown",
                            categoryId = minProduct.categoryId!!,
                            categoryName = categoryEntity.type ?: "Unknown",
                            price = price
                        )
                    )
                    lowestPriceProduct.total += price
                }
            }
        }

        return lowestPriceProduct
    }

    fun findLowestPricedProductByBrand(brandId: Int): LowestPriceProductByBrand {
        val brand = brandRepository.findById(brandId).orElseThrow { throw Exception() }
        val brandName = brand.name ?: "Unknown"

        val categories = categoryRepository.findAllBy()

        val lowestPriceProductByBrand = LowestPriceProductByBrand(
            brand = brandName,
            categories = mutableListOf(),
            totalPrice = 0
        )

        categories.forEach { category ->
            val brandCategory = brandCategoryRepository.findByBrandIdAndCategoryId(brandId, category.id!!)
            val price = brandCategory.price ?: 0

            lowestPriceProductByBrand.categories.add(
                CategoryPrice(
                    category = category.type ?: "Unknown",
                    price = price
                )
            )

            lowestPriceProductByBrand.totalPrice += price
        }

        return lowestPriceProductByBrand
    }

    fun findMinMaxPriceByCategory(categoryName: String): CategoryPriceInfo? {
        val category = categoryRepository.findByType(categoryName) ?: return null
        val brandCategoryList = brandCategoryRepository.findAllByCategoryId(category.id!!)

        val minProduct = brandCategoryList.minByOrNull { it.price ?: Int.MAX_VALUE } ?: return null
        val maxProduct = brandCategoryList.maxByOrNull { it.price ?: Int.MIN_VALUE } ?: return null

        return CategoryPriceInfo(
            category = categoryName,
            lowestPrice = listOf(
                BrandPrice(
                    brand = brandRepository.findById(minProduct.brandId!!).orElseThrow { throw Exception() }.name ?: "Unknown",
                    price = minProduct.price ?: 0
                )
            ),
            highestPrice = listOf(
                BrandPrice(
                    brand = brandRepository.findById(maxProduct.brandId!!).orElseThrow { throw Exception() }.name ?: "Unknown",
                    price = maxProduct.price ?: 0
                )
            )
        )
    }
}
