package nextstep.shoppingcart.fixtures

import nextstep.shoppingcart.domain.model.CartProduct
import nextstep.shoppingcart.domain.model.Product

fun cartProduct(
    id: Long = 1L,
    name: String = "상품",
    count: Int = 1,
    price: Int = 1000,
): CartProduct = CartProduct(
    product = Product(id, "testURL", name, price),
    count = count
)