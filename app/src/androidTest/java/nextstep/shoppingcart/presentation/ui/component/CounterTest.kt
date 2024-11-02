package nextstep.shoppingcart.presentation.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.presentation.ui.component.Counter
import org.junit.Rule
import org.junit.Test

class CounterTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun when_click_plus_button_then_increase_cart_product_count() {
        composeTestRule.setContent {
            var count by remember { mutableIntStateOf(1) }
            val onCartProductPlus: (Long) -> Unit = { count++ }
            Counter(
                count = count,
                onPlus = { onCartProductPlus(1) },
                onMinus = {},
            )
        }
        // when
        composeTestRule.onNodeWithContentDescription("수량 증가")
            .performClick()
        // then
        composeTestRule.onNodeWithText("2")
            .assertIsDisplayed()
    }

    @Test
    fun when_click_minus_button_then_decrease_cart_product_count() {
        composeTestRule.setContent {
            var count by remember { mutableIntStateOf(1) }
            val onCartProductMinus: (Long) -> Unit = { count-- }
            Counter(
                count = count,
                onPlus = {},
                onMinus = { onCartProductMinus(1) },
            )
        }
        // when
        composeTestRule.onNodeWithContentDescription("수량 감소")
            .performClick()
        // then
        composeTestRule.onNodeWithText("0")
            .assertIsDisplayed()
    }
}