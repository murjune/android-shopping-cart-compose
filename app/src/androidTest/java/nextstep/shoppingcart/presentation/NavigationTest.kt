package nextstep.shoppingcart.presentation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.toRoute
import nextstep.shoppingcart.presentation.cart.CartScreen
import nextstep.shoppingcart.data.FakeProductRepository
import nextstep.shoppingcart.domain.repository.ProductRepository
import nextstep.shoppingcart.presentation.product.ProductRoute
import nextstep.shoppingcart.domain.model.Product
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            ProductRepository.set(
                FakeProductRepository(
                    products = listOf(
                        Product(id = 1L, imageUrl = "testUrl", name = "오둥이", price = 1_000)
                    )
                )
            )
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ShoppingNavHost(navHostController = navController)
        }
    }

    @Test
    fun test_navigation_to_cart() {
        // when
        composeTestRule
            .onNodeWithContentDescription("장바구니로 이동")
            .performClick()
        // then
        val currentDestination = navController.currentDestination?.hasRoute<CartScreen>()
        assert(currentDestination == true)
    }

    @Test
    fun test_navigation_to_product_detail() {
        // when
        composeTestRule
            .onNodeWithText("오둥이")
            .performClick()
        // then
        val currentDestination = navController.currentDestination?.hasRoute<ProductRoute.Detail>()
        val product = navController.currentBackStackEntry?.toRoute<ProductRoute.Detail>()
        assert(currentDestination == true)
        assert(product?.productId == 1L)
    }
}