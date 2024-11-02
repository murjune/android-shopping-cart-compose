package nextstep.shoppingcart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.shoppingcart.data.model.Product

class FakeProductRepository(
    private val products: List<Product> = emptyList()
) : ProductRepository {
    override fun products(): Flow<List<Product>> = flow {
        emit(products)
    }

    override fun productBy(id: Long): Product? = products.find { it.id == id }
}