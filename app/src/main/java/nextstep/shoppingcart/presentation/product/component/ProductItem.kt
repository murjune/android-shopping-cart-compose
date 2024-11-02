package nextstep.shoppingcart.presentation.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.product.model.ProductUiModel
import nextstep.shoppingcart.presentation.ui.component.Counter
import nextstep.shoppingcart.presentation.ui.component.ProductImage
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductItem(
    product: ProductUiModel,
    count: Int,
    onProductPlus: (id: Long) -> Unit,
    onProductMinus: (id: Long) -> Unit,
    onClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable {
            onClick(product.id)
        }
    ) {
        Box {
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
            ProductItemCounter(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
                count = count,
                onProductPlus = { onProductPlus(product.id) },
                onProductMinus = { onProductMinus(product.id) }
            )
        }
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

@Composable
private fun ProductItemCounter(
    count: Int,
    onProductPlus: () -> Unit,
    onProductMinus: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.pointerInput(Unit) {
        detectTapGestures {}
    }) {
        if (count == 0) {
            PlusButton(
                modifier = Modifier
                    .padding(12.dp)
                    .size(42.dp)
                    .shadow(4.dp, shape = CircleShape)
                    .clip(CircleShape),
                onPlus = onProductPlus
            )
        } else {
            Counter(
                modifier = Modifier
                    .padding(6.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                    .background(Color.White),
                count = count,
                onPlus = onProductPlus,
                onMinus = onProductMinus
            )
        }
    }
}

@Composable
private fun PlusButton(modifier: Modifier = Modifier, onPlus: () -> Unit) {
    IconButton(
        onClick = onPlus,
        modifier = modifier.size(42.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White,
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "장바구니 추가",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductItem() {
    ShoppingCartTheme {
        Column {
            ProductItem(
                product = ProductUiModel(
                    id = 1,
                    name = "상품 이름",
                    price = 1000,
                    imageUrl = "https://www.example.com/image.jpg",
                    count = 1
                ),
                count = 0,
                onProductPlus = {},
                onProductMinus = {},
                onClick = {},
                modifier = Modifier.size(200.dp, 250.dp)
            )
            ProductItem(
                product = ProductUiModel(
                    id = 1,
                    name = "상품 이름",
                    price = 1000,
                    imageUrl = "https://www.example.com/image.jpg",
                    count = 0
                ),
                count = 1,
                onProductPlus = {},
                onProductMinus = {},
                onClick = {},
                modifier = Modifier.size(200.dp, 250.dp)
            )
        }
    }
}