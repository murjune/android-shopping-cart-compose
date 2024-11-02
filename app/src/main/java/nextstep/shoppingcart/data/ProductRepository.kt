package nextstep.shoppingcart.data

import androidx.annotation.VisibleForTesting
import nextstep.shoppingcart.product.model.Product
import nextstep.shoppingcart.data.model.Product

interface ProductRepository {
    fun products(): List<Product>
    fun productBy(id: Long): Product?

    companion object {
        var instance: ProductRepository? = null

        fun get(): ProductRepository {
            return instance ?: DefaultProductRepository().also {
                instance = it
            }
        }

        @VisibleForTesting
        fun set(repository: ProductRepository) {
            instance = repository
        }
    }
}