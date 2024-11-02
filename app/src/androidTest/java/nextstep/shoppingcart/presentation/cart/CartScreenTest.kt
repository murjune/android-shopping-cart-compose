package nextstep.shoppingcart.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.fixtures.cartProduct
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var cartProducts by remember {
                mutableStateOf(
                    listOf(
                        cartProduct(
                            id = 1L, name = "휴지", price = 1000, count = 2
                        ),
                    )
                )
            }
            val orderPrice = cartProducts.sumOf { it.product.price * it.count }
            val onAddProduct = { productId: Long ->
                cartProducts = cartProducts.map {
                    if (it.product.id == productId) {
                        it.copy(count = it.count + 1)
                    } else {
                        it
                    }
                }
            }

            val onRemoveProduct = { productId: Long ->
                cartProducts = cartProducts.mapNotNull {
                    if (it.product.id == productId) {
                        if (it.count == 1) {
                            return@mapNotNull null
                        }
                        it.copy(count = it.count - 1)
                    } else {
                        it
                    }
                }
            }

            val onRemoveAllProduct = { productId: Long ->
                cartProducts = cartProducts.filter { it.product.id != productId }
            }

            CartScreen(
                cartProducts = cartProducts,
                orderPrice = orderPrice,
                onBack = {},
                onOrder = {
                    cartProducts = emptyList()
                },
                onCartProductPlus = onAddProduct,
                onCartProductMinus = onRemoveProduct,
                onCartProductRemove = onRemoveAllProduct,
            )
        }
    }

    @Test
    fun 담긴_상품_가격의_총합이_노출된다() {
        composeTestRule.onAllNodesWithText("2,000", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun 담긴_상품을_제거할_수_있다() {
        // when
        composeTestRule.onAllNodesWithContentDescription("상품 제거")
            .onFirst()
            .performClick()
        // then
        composeTestRule.onNodeWithText("휴지")
            .assertDoesNotExist()
    }

    @Test
    fun 담긴_상품의_수량을_증가시키면_상품_가격에_반영된다() {
        // when
        composeTestRule.onAllNodesWithContentDescription("수량 증가")
            .onFirst()
            .performClick()
        // then
        composeTestRule
            .onAllNodesWithText("3,000", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun 담긴_상품의_수량을_감소시키면_상품_가격에_반영된다() {
        // when
        composeTestRule.onAllNodesWithContentDescription("수량 감소")
            .onLast()
            .performClick()
        // then
        composeTestRule.onAllNodesWithText("1,000", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun 담긴_상품의_수량을_1보다_적게_하면_상품이_삭제된다() {
        // when
        composeTestRule.onAllNodesWithContentDescription("수량 감소")
            .onFirst()
            .performClick()
            .performClick()
        // then
        composeTestRule.onAllNodesWithText("휴지")
            .onFirst()
            .assertDoesNotExist()
    }

    @Test
    fun 주문이_완료되면_스낵바가_노출된다() {
        // given
        composeTestRule.onNodeWithText("주문하기", substring = true)
            .performClick()
        // when
        composeTestRule.onNodeWithText("주문 완료", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun 주문이_완료되면_장바구니가_비워진다() {
        // given
        composeTestRule.onNodeWithText("주문하기", substring = true)
            .performClick()
        // when
        composeTestRule.onNodeWithContentDescription("수량 증가")
            .assertDoesNotExist()
    }
}