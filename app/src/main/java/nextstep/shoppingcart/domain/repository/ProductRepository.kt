package nextstep.shoppingcart.domain.repository

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.Flow
import nextstep.shoppingcart.data.DefaultProductRepository
import nextstep.shoppingcart.domain.model.Product

interface ProductRepository {
    fun products(): Flow<List<Product>>
    fun productBy(id: Long): Product?

    companion object {
        private var instance: ProductRepository? = null

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