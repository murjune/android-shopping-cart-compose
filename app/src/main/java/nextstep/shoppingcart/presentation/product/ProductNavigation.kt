package nextstep.shoppingcart.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nextstep.shoppingcart.domain.repository.CartRepository
import nextstep.shoppingcart.domain.repository.ProductRepository

fun NavController.navigateToProductDetail(
    productId: Long,
    navOptions: NavOptions? = null
) = navigate(ProductRoute.Detail(productId), navOptions)

fun NavController.navigateToProductList(navOptions: NavOptions? = null) =
    navigate(ProductRoute.Home, navOptions)

fun NavGraphBuilder.productGraph(
    navigateToCart: () -> Unit,
    navigateToProductDetail: (Long) -> Unit,
    onBack: () -> Unit
) {
    navigation<ProductRoute>(
        startDestination = ProductRoute.Home::class
    ) {
        composable<ProductRoute.Home> {
            val productRepository = ProductRepository.get()
            val products by productRepository.products()
                .collectAsStateWithLifecycle(initialValue = emptyList())
            ProductScreen(
                products = products,
                onCartClick = navigateToCart,
                onItemClick = navigateToProductDetail
            )
        }
        composable<ProductRoute.Detail> { backStackEntry ->
            val profile: ProductRoute.Detail = backStackEntry.toRoute()
            val product by remember(profile.productId) {
                mutableStateOf(
                    ProductRepository.get().productBy(
                        profile.productId
                    )
                )
            }
            val cartRepository = CartRepository.get()
            val onCartAdd = remember(product) {
                {
                    product?.let { cartRepository.addProduct(it.id, count = 1) }
                    navigateToCart()
                }
            }
            ProductDetailScreen(
                product = product,
                onBack = onBack,
                onCartAdd = onCartAdd
            )
        }
    }
}

@Serializable
data object ProductRoute {
    @Serializable
    data class Detail(val productId: Long)

    @Serializable
    data object Home
}

