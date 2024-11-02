package nextstep.shoppingcart.product

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.shoppingcart.data.model.Product
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_ProductScreen() {
        composeTestRule
            .setContent {
                ProductDetailScreen(
                    product = Product(id = 1L, imageUrl = "testUrl", name = "오둥이", price = 1_000),
                    onBack = {},
                    onCartAdd = {}
                )
            }
        composeTestRule
            .onNodeWithText("상품 상세")
            .assertIsDisplayed()
    }

    @Test
    fun test_display_Product_Name() {
        composeTestRule
            .setContent {
                ProductDetailScreen(
                    product = Product(id = 1L, imageUrl = "testUrl", name = "오둥이", price = 1_000),
                    onBack = {},
                    onCartAdd = {}
                )
            }
        composeTestRule
            .onNodeWithText("오둥이")
            .assertIsDisplayed()
    }

    @Test
    fun test_display_Empty_Content() {
        composeTestRule
            .setContent {
                ProductDetailScreen(
                    product = null,
                    onBack = {},
                    onCartAdd = {}
                )
            }
        composeTestRule
            .onNodeWithText("실패", substring = true)
            .assertIsDisplayed()
    }
}