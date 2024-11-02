package nextstep.shoppingcart.domain.repository

import kotlinx.coroutines.flow.Flow
import nextstep.shoppingcart.domain.model.CartProduct

interface CartRepository {
    fun addProduct(productId: Long, count: Int = 1)
    fun removeProduct(productId: Long, count: Int = 1)
    fun cartProducts(): Flow<List<CartProduct>>
}