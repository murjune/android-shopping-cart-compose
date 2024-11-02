package nextstep.shoppingcart.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nextstep.shoppingcart.presentation.cart.cartScreen
import nextstep.shoppingcart.presentation.cart.navigateToCart
import nextstep.shoppingcart.presentation.product.ProductRoute
import nextstep.shoppingcart.presentation.product.navigateToProductDetail
import nextstep.shoppingcart.presentation.product.productGraph


@Composable
fun ShoppingNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = ProductRoute::class
    ) {
        with(navHostController) {
            productGraph(
                navigateToProductDetail = { productId ->
                    navigateToProductDetail(
                        productId,
                    )
                },
                navigateToCart = ::navigateToCart,
                onBack = { popBackStack() },
            )
            cartScreen(
                onBack = { popBackStack() }
            )
        }
    }
}