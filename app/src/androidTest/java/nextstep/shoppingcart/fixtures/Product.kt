package nextstep.shoppingcart.fixtures

import nextstep.shoppingcart.domain.model.Product


fun product(
    id: Long = 1L,
    name: String = "상품",
    price: Int = 1000,
): Product = Product(
    id, "testURL", name, price
)