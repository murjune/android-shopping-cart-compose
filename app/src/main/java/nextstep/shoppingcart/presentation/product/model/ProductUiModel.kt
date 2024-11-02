package nextstep.shoppingcart.presentation.product.model

data class ProductUiModel(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val count: Int
)