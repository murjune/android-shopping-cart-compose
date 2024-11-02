package nextstep.shoppingcart

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.shoppingcart.product.ProductScreen
import nextstep.shoppingcart.product.model.Product
import org.junit.Rule
import org.junit.Test

class ProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_ProductScreen() {
        composeTestRule
            .setContent {
                ProductScreen(
                    products = emptyList(),
                    onItemClick = {},
                    onCartClick = {}
                )
            }

        composeTestRule
            .onNodeWithText("상품 목록")
            .assertIsDisplayed()
    }

    @Test
    fun test_display_Product() {
        composeTestRule
            .setContent {
                ProductScreen(
                    products = listOf(
                        Product(id = 1L, imageUrl = "testUrl", name = "오둥이", price = 1_000)
                    ),
                    onItemClick = {},
                    onCartClick = {}
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
                ProductScreen(
                    products = emptyList(),
                    onItemClick = {},
                    onCartClick = {}
                )
            }
        composeTestRule
            .onNodeWithText("상품이 없습니다.")
            .assertIsDisplayed()
    }
}