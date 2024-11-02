package nextstep.shoppingcart.presentation.cart.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.ui.component.Counter
import nextstep.shoppingcart.presentation.ui.component.ProductImage
import nextstep.shoppingcart.domain.model.CartProduct
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@Composable
internal fun CartProductItem(
    cartProduct: CartProduct,
    onCartProductPlus: (productId: Long) -> Unit,
    onCartProductMinus: (productId: Long) -> Unit,
    onCartProductRemove: (productId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProductName(name = cartProduct.product.name)
            IconButton(onClick = {
                onCartProductRemove(cartProduct.product.id)
            }) {
                Icon(
                    imageVector = Icons.Default.Clear, contentDescription = "상품 제거"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProductImage(
                modifier = Modifier.size(136.dp, 84.dp),
                imageUrl = cartProduct.product.imageUrl,
                contentDescription = "상품 이미지"
            )
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    modifier = Modifier.padding(end = 10.dp),
                    text = stringResource(
                        id = R.string.price_format,
                        cartProduct.totalPrice
                    ),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Counter(
                    count = cartProduct.count,
                    onPlus = { onCartProductPlus(cartProduct.product.id) },
                    onMinus = { onCartProductMinus(cartProduct.product.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductName(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCartProductItem() {
    ShoppingCartTheme {
        CartProductItem(
            cartProduct = CartProduct(
                product = Product(
                    id = 1,
                    name = "상품 이름",
                    price = 1000,
                    imageUrl = "https://example.com/image.jpg"
                ),
                count = 1
            ),
            onCartProductPlus = {},
            onCartProductMinus = {},
            onCartProductRemove = {}
        )
    }
}
