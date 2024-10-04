package kr.yooni.product.controller

import com.fasterxml.jackson.databind.ObjectMapper
import kr.yooni.product.data.BrandCreationRequest
import kr.yooni.product.data.CategoryCreationRequest
import kr.yooni.product.service.AdminProductService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(AdminProductController::class)
class AdminProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var adminProductService: AdminProductService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `브랜드 목록 조회 테스트`() {
        val brandList = listOf("BrandA", "BrandB")
        given(adminProductService.findBrandList()).willReturn(brandList)

        mockMvc.perform(get("/v1/admin/products/brands"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").isArray)
            .andExpect(jsonPath("$.data[0]").value("BrandA"))
            .andExpect(jsonPath("$.data[1]").value("BrandB"))
    }

    @Test
    fun `브랜드 생성 테스트`() {
        val brandRequest = BrandCreationRequest(name = "NewBrand")
        doNothing().`when`(adminProductService).createBrand(brandRequest)

        mockMvc.perform(
            post("/v1/admin/products/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandRequest))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `브랜드 수정 테스트`() {
        doNothing().`when`(adminProductService).updateBrand(1, "UpdatedBrand")

        mockMvc.perform(
            put("/v1/admin/products/brands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("UpdatedBrand")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `브랜드 삭제 테스트`() {
        doNothing().`when`(adminProductService).deleteBrand(1)

        mockMvc.perform(delete("/v1/admin/products/brands/1"))
            .andExpect(status().isOk)
    }

    @Test
    fun `카테고리 목록 조회 테스트`() {
        val categoryList = listOf("CategoryA", "CategoryB")
        given(adminProductService.findCategoryList()).willReturn(categoryList)

        mockMvc.perform(get("/v1/admin/products/categories"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").isArray)
            .andExpect(jsonPath("$.data[0]").value("CategoryA"))
            .andExpect(jsonPath("$.data[1]").value("CategoryB"))
    }

    @Test
    fun `카테고리 생성 테스트`() {
        val categoryRequest = CategoryCreationRequest(type = "NewCategory")
        doNothing().`when`(adminProductService).createCategory(categoryRequest)

        mockMvc.perform(
            post("/v1/admin/products/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `카테고리 수정 테스트`() {
        doNothing().`when`(adminProductService).updateCategory(1, "UpdatedCategory")

        mockMvc.perform(
            put("/v1/admin/products/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("UpdatedCategory")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `카테고리 삭제 테스트`() {
        doNothing().`when`(adminProductService).deleteCategory(1)

        mockMvc.perform(delete("/v1/admin/products/categories/1"))
            .andExpect(status().isOk)
    }

    @Test
    fun `브랜드별 카테고리 가격 조회 테스트`() {
        val categoryPrices = listOf("CategoryA: 1000", "CategoryB: 2000")
        given(adminProductService.findCategoryPricesByBrand(1)).willReturn(categoryPrices)

        mockMvc.perform(get("/v1/admin/products/1/category-prices"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data").isArray)
            .andExpect(jsonPath("$.data[0]").value("CategoryA: 1000"))
            .andExpect(jsonPath("$.data[1]").value("CategoryB: 2000"))
    }

    @Test
    fun `가격 수정 테스트`() {
        doNothing().`when`(adminProductService).updatePrice(1, 1, 1500)

        mockMvc.perform(
            put("/v1/admin/products/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1500")
        )
            .andExpect(status().isOk)
    }
}
