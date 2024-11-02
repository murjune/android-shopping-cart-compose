package nextstep.shoppingcart.domain.repository

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.Flow
import nextstep.shoppingcart.data.DefaultCartRepository
import nextstep.shoppingcart.domain.model.CartProduct

interface CartRepository {
    fun cartProducts(): Flow<List<CartProduct>>
    fun addProduct(productId: Long, count: Int = 1)
    fun removeProduct(productId: Long, count: Int = 1)
    fun clearProduct(productId: Long)
    fun clear()

    companion object {
        private var cartRepository: CartRepository? = null

        fun get(): CartRepository {
            return cartRepository ?: DefaultCartRepository(ProductRepository.get()).also {
                cartRepository = it
            }
        }

        @VisibleForTesting
        fun set(repository: CartRepository) {
            cartRepository = repository
        }
    }
}