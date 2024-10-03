package kr.yooni.product.database.domain.aggregate

data class Product(
    val brandId: Int,
    val brandName: String,
    val categoryId: Int,
    val categoryName: String,
    val price: Int
)

data class LowestPriceProduct(
    var items: MutableList<Product?>,
    var total: Int
)

data class LowestPriceProductByBrand(
    val brand: String,
    val categories: MutableList<CategoryPrice>,
    var totalPrice: Int
)

data class CategoryPrice(
    val category: String,
    val price: Int
)

data class CategoryPriceInfo(
    val category: String,
    val lowestPrice: List<BrandPrice>,
    val highestPrice: List<BrandPrice>
)

data class BrandPrice(
    val brand: String,
    val price: Int
)
