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
import kotlinx.coroutines.flow.combine
import kotlinx.serialization.Serializable
import nextstep.shoppingcart.domain.repository.CartRepository
import nextstep.shoppingcart.domain.repository.ProductRepository
import nextstep.shoppingcart.presentation.product.model.ProductUiModel

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
            val cartRepository = CartRepository.get()
            val products: List<ProductUiModel> by combine(
                productRepository.products(),
                cartRepository.cartProducts()
            ) { products, cartProducts ->
                products.map { product ->
                    val cartProduct = cartProducts.find { it.product.id == product.id }
                    ProductUiModel(
                        id = product.id,
                        imageUrl = product.imageUrl,
                        name = product.name,
                        price = product.price,
                        count = cartProduct?.count ?: 0
                    )
                }
            }.collectAsStateWithLifecycle(emptyList())

            ProductScreen(
                products = products,
                onCartClick = navigateToCart,
                onItemClick = navigateToProductDetail,
                onProductPlus = { cartRepository.addProduct(it, 1) },
                onProductMinus = { cartRepository.removeProduct(it, 1) },
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

