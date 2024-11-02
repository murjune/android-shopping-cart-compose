package nextstep.shoppingcart.presentation.product.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.ui.component.ProductImage
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductItem(
    product: Product,
    onClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable {
            onClick(product.id)
        }
    ) {
        ProductImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                ),
            imageUrl = product.imageUrl,
            contentDescription = "상품 이미지",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(Modifier.padding(horizontal = 4.dp)) {
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(id = R.string.price_format, product.price),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductItem() {
    ShoppingCartTheme {
        ProductItem(
            product = Product(
                id = 1,
                name = "상품 이름",
                price = 1000,
                imageUrl = "https://www.example.com/image.jpg"

            ),
            onClick = {},
            modifier = Modifier.size(100.dp, 150.dp)
        )
    }
}