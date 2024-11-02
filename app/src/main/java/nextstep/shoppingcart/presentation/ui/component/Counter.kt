package nextstep.shoppingcart.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@Composable
fun Counter(
    count: Int,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = onMinus,
            modifier = Modifier
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = R.drawable.remove_24),
                contentDescription = "수량 감소"
            )
        }
        Text(
            text = count.toString(),
            style = textStyle,
        )
        IconButton(
            onClick = onPlus,
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                imageVector = Icons.Default.Add, contentDescription = "수량 증가"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    ShoppingCartTheme {
        Counter(
            count = 1,
            onPlus = {},
            onMinus = {},
        )
    }
}