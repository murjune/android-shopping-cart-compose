package nextstep.shoppingcart.product

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nextstep.shoppingcart.R
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.product.preview.ProductPreviewParameterProvider
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    products: List<Product>,
    onItemClick: (id: Long) -> Unit,
    onCartClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.product_screen_title))
                },
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = stringResource(id = R.string.product_top_bar_actions_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (products.isEmpty()) {
            ProductEmptyContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
        ProductContent(
            producs = products,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            onItemClick = onItemClick
        )
    }
}


@Composable
private fun ProductContent(
    producs: List<Product>,
    modifier: Modifier = Modifier,
    onItemClick: (id: Long) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(producs, key = { it.id }) { product ->
            ProductItem(
                product,
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun ProductEmptyContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.product_screen_empty_content),
            style = MaterialTheme.typography.displayMedium
        )
    }

}

@Composable
private fun ProductItem(
    product: Product,
    onClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable {
            onClick(product.id)
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                ),
            model = product.imageUrl,
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

@Preview(showBackground = true)
@Composable
private fun PreviewProductScreen(
    @PreviewParameter(ProductPreviewParameterProvider::class) products: List<Product>
) {
    ShoppingCartTheme {
        ProductScreen(products, {}, {})
    }
}