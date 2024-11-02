package nextstep.shoppingcart.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.ui.component.ShoppingButton
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@Composable
fun ProductDetailScreen(
    product: Product?,
    onBack: () -> Unit,
    onCartAdd: () -> Unit
) {
    Scaffold(
        topBar = {
            ProductDetailTopBar(
                onBack = onBack
            )
        },
        bottomBar = {
            if (product != null) {
                ShoppingButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.product_detail_add_cart_button),
                    onClick = onCartAdd
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (product == null) {
                ProductDetailErrorContent(Modifier.padding(innerPadding))
            } else {
                ProductDetailContent(
                    product = product,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailTopBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.product_detail_screen_title))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.product_top_bar_navigation_icon_content_description)
                )
            }
        }
    )
}

@Composable
private fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProductImage(imageUrl = product.imageUrl)
        ProductName(name = product.name)
        HorizontalDivider(Modifier.height(1.dp))
        ProductPrice(price = product.price)
    }
}

@Composable
private fun ProductDetailErrorContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.product_detail_screen_error),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun ProductImage(
    imageUrl: String
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        model = imageUrl,
        contentDescription = stringResource(id = R.string.product_content_description)
    )
}

@Composable
private fun ProductName(
    name: String
) {
    Text(
        modifier = Modifier.padding(start = 18.dp),
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ProductPrice(
    price: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.product_detail_price_description),
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = R.string.price_format, price),
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    ShoppingCartTheme {
        ProductDetailScreen(
            product = Product(
                id = 1,
                name = "오둥이",
                price = 1000,
                imageUrl = "https://example.com/image.jpg"
            ),
            onBack = {},
            onCartAdd = {}
        )
    }
}

@Preview
@Composable
private fun ProductDetailErrorScreenPreview() {
    ShoppingCartTheme {
        ProductDetailScreen(
            product = null,
            onBack = {},
            onCartAdd = {}
        )
    }
}