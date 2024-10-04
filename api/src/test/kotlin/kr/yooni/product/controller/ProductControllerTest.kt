package kr.yooni.product.controller

import kr.yooni.product.database.domain.aggregate.BrandPrice
import kr.yooni.product.database.domain.aggregate.CategoryPrice
import kr.yooni.product.database.domain.aggregate.CategoryPriceInfo
import kr.yooni.product.database.domain.aggregate.LowestPriceProduct
import kr.yooni.product.database.domain.aggregate.LowestPriceProductByBrand
import kr.yooni.product.database.domain.aggregate.Product
import kr.yooni.product.service.ProductService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ProductController::class)
class ProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var productService: ProductService

    @Test
    fun `브랜드 목록 조회 테스트`() {
        val brandList = "BrandA, BrandB"
        given(productService.findBrandList()).willReturn(brandList)

        mockMvc.perform(get("/v1/products/brands"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data").value(brandList))
    }

    @Test
    fun `카테고리 목록 조회 테스트`() {
        val categoryList = "CategoryA, CategoryB"
        given(productService.findCategoryList()).willReturn(categoryList)

        mockMvc.perform(get("/v1/products/cateogries"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data").value(categoryList))
    }

    @Test
    fun `최저가 상품 정보 조회 테스트`() {

        val product1 = Product(
            brandId = 1,
            brandName = "BrandA",
            categoryId = 101,
            categoryName = "CategoryA",
            price = 10000
        )

        val product2 = Product(
            brandId = 2,
            brandName = "BrandB",
            categoryId = 102,
            categoryName = "CategoryB",
            price = 15000
        )

        val lowestPriceProduct = LowestPriceProduct(
            items = mutableListOf(product1, product2),
            total = 25000
        )
        given(productService.findLowestPricedProduct()).willReturn(lowestPriceProduct)

        mockMvc.perform(get("/v1/products/lowest-price"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.items[0].brandName").value("BrandA"))
            .andExpect(jsonPath("$.data.items[0].price").value(10000))
            .andExpect(jsonPath("$.data.total").value(25000))
    }

    @Test
    fun `단일 브랜드 최저가 상품 정보 조회 테스트`() {

        val categoryPrice1 = CategoryPrice(
            category = "CategoryA",
            price = 10000
        )

        val categoryPrice2 = CategoryPrice(
            category = "CategoryB",
            price = 15000
        )

        val lowestPriceProductByBrand = LowestPriceProductByBrand(
            brand = "BrandA",
            categories = mutableListOf(categoryPrice1, categoryPrice2),
            totalPrice = 25000
        )

        given(productService.findLowestPricedProductByBrand(1)).willReturn(lowestPriceProductByBrand)

        mockMvc.perform(get("/v1/products/lowest-price/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.brand").value("BrandA"))
            .andExpect(jsonPath("$.data.totalPrice").value(25000))
    }

    @Test
    fun `카테고리별 최저가 및 최고가 브랜드 정보 조회 테스트`() {

        val lowestBrandPrice1 = BrandPrice(
            brand = "BrandA",
            price = 10000
        )
        val highestBrandPrice1 = BrandPrice(
            brand = "BrandB",
            price = 20000
        )

        val categoryPriceInfo = CategoryPriceInfo(
            category = "Electronics",
            lowestPrice = listOf(lowestBrandPrice1),
            highestPrice = listOf(highestBrandPrice1)
        )

        given(productService.findMinMaxPriceByCategory("Electronics")).willReturn(categoryPriceInfo)

        mockMvc.perform(get("/v1/products/min-max-price/Electronics"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.lowestPrice[0].brand").value("BrandA"))
            .andExpect(jsonPath("$.data.lowestPrice[0].price").value(10000))
            .andExpect(jsonPath("$.data.highestPrice[0].brand").value("BrandB"))
            .andExpect(jsonPath("$.data.highestPrice[0].price").value(20000))
    }
}
