package nextstep.shoppingcart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ProductRepository

class FakeProductRepository(
    private val products: List<Product> = emptyList()
) : ProductRepository {

    constructor(vararg products: Product) : this(products.toList())

    override fun products(): Flow<List<Product>> = flow {
        emit(products)
    }

    override fun productBy(id: Long): Product? = products.find { it.id == id }
}