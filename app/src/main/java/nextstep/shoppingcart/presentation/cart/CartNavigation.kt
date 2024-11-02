package nextstep.shoppingcart.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import nextstep.shoppingcart.domain.repository.CartRepository

fun NavController.navigateToCart(navOptions: NavOptions? = null) =
    navigate(CartScreen, navOptions)


fun NavGraphBuilder.cartScreen(
    onBack: () -> Unit
) {
    composable<CartScreen> {
        val cartRepository = CartRepository.get()
        val cartProducts by cartRepository.cartProducts()
            .collectAsStateWithLifecycle(
                initialValue = emptyList()
            )
        val orderPrice = remember(cartProducts) {
            cartProducts.sumOf { cartProduct ->
                cartProduct.totalPrice
            }
        }
        val onCartProductMinus = remember {
            { productId: Long ->
                cartRepository.removeProduct(productId, count = 1)
            }
        }
        val onCartProductPlus = remember {
            { productId: Long ->
                cartRepository.addProduct(productId, count = 1)
            }
        }
        val onOrder = remember {
            {
                cartRepository.clear()
            }
        }
        CartScreen(
            cartProducts = cartProducts,
            orderPrice = orderPrice,
            onBack = onBack,
            onOrder = onOrder,
            onCartProductPlus = onCartProductPlus,
            onCartProductMinus = onCartProductMinus,
            onCartProductRemove = cartRepository::clearProduct,
        )
    }
}

@Serializable
data object CartScreen
