package nextstep.shoppingcart.domain.model

data class CartProduct(
    val product: Product,
    val count: Int
) {
    val totalPrice: Int
        get() = product.price * count
}