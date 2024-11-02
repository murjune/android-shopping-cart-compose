package nextstep.shoppingcart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import nextstep.shoppingcart.domain.model.CartProduct
import nextstep.shoppingcart.domain.repository.CartRepository
import nextstep.shoppingcart.domain.repository.ProductRepository

class DefaultCartRepository(
    private val productRepository: ProductRepository,
) : CartRepository {
    private val cartProducts: MutableStateFlow<List<CartProduct>> = MutableStateFlow(emptyList())

    override fun addProduct(productId: Long, count: Int) {
        val product = productRepository.productBy(productId) ?: return
        val cartProduct: CartProduct? = cartProducts.value.find { it.product.id == productId }
        if (cartProduct != null) {
            val newCartProduct = cartProduct.copy(count = cartProduct.count + count)
            val newCartProducts = cartProducts.value.map {
                if (it.product.id == productId) newCartProduct else it
            }
            cartProducts.value = newCartProducts
            return
        }
        val newCartProduct = CartProduct(
            product = product,
            count = count
        )
        cartProducts.value += newCartProduct
    }

    override fun removeProduct(productId: Long, count: Int) {
        val cartProduct: CartProduct? = cartProducts.value.find { it.product.id == productId }
        requireNotNull(cartProduct) {
            "$productId 에 해당하는 상품이 장바구니에 없습니다."
        }
        if (cartProduct.count <= count) {
            cartProducts.value = cartProducts.value.filter { it.product.id != productId }
            return
        }
        val newCartProduct = cartProduct.copy(count = cartProduct.count - count)
        val newCartProducts = cartProducts.value.map {
            if (it.product.id == productId) newCartProduct else it
        }
        cartProducts.value = newCartProducts
    }

    override fun clearProduct(productId: Long) {
        requireNotNull(cartProducts.value.any { it.product.id == productId }) {
            "$productId 에 해당하는 상품이 장바구니에 없습니다."
        }
        cartProducts.value = cartProducts.value.filter { it.product.id != productId }
    }

    override fun clear() {
        cartProducts.value = emptyList()
    }


    override fun cartProducts(): Flow<List<CartProduct>> = cartProducts
}