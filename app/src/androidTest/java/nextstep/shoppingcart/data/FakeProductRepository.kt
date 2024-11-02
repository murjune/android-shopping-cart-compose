package nextstep.shoppingcart.data

import nextstep.shoppingcart.product.model.Product
import nextstep.shoppingcart.data.model.Product

class FakeProductRepository(
    private val products: List<Product> = emptyList()
) : ProductRepository {
    override fun products(): List<Product> = products

    override fun productBy(id: Long): Product? = products.find { it.id == id }
}