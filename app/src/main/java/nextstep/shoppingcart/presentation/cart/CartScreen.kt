package nextstep.shoppingcart.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.shoppingcart.R
import nextstep.shoppingcart.domain.model.CartProduct
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.cart.component.CartProductItem
import nextstep.shoppingcart.presentation.ui.component.ShoppingButton
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartProducts: List<CartProduct>,
    orderPrice: Int,
    onBack: () -> Unit,
    onOrder: () -> Unit,
    onCartProductPlus: (productId: Long) -> Unit,
    onCartProductMinus: (productId: Long) -> Unit,
    onCartProductRemove: (productId: Long) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val completeMessage = stringResource(id = R.string.cart_order_snackbar)
    val onOrder: () -> Unit = remember {
        {
            onOrder()
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = completeMessage
                )
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.cart_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        },
        bottomBar = {
            ShoppingButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.cart_order_button, orderPrice),
                onClick = onOrder
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            CartProductContent(
                cartProducts = cartProducts,
                onCartProductPlus = onCartProductPlus,
                onCartProductMinus = onCartProductMinus,
                onCartProductRemove = onCartProductRemove,
            )
        }
    }
}

@Composable
private fun CartProductContent(
    cartProducts: List<CartProduct>,
    onCartProductPlus: (productId: Long) -> Unit,
    onCartProductMinus: (productId: Long) -> Unit,
    onCartProductRemove: (productId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(cartProducts, key = { it.product.id }) {
            CartProductItem(
                cartProduct = it,
                onCartProductPlus = onCartProductPlus,
                onCartProductMinus = onCartProductMinus,
                onCartProductRemove = onCartProductRemove,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenPreview() {
    ShoppingCartTheme {
        CartScreen(
            cartProducts = listOf(
                CartProduct(
                    product = Product(
                        id = 1,
                        imageUrl = "1",
                        name = "상품1",
                        price = 1000
                    ),
                    count = 2
                ),
                CartProduct(
                    product = Product(
                        id = 2,
                        imageUrl = "2",
                        name = "상품2",
                        price = 10000
                    ),
                    count = 2
                ),
            ),
            orderPrice = 22000,
            onBack = {},
            onOrder = {},
            onCartProductPlus = {},
            onCartProductMinus = {},
            onCartProductRemove = {},
        )
    }
}