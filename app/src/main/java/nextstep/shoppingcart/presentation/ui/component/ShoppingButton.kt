package nextstep.shoppingcart.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme


@Composable
fun ShoppingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = PaddingValues(15.dp),
) {
    Box(
        modifier = modifier
            .semantics { role = Role.Button }
            .then(
                if (enabled) Modifier.background(
                    color = buttonColors.containerColor,
                ) else Modifier
                    .background(
                        color = buttonColors.disabledContainerColor,
                    )
            )
            .clickable(onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (enabled) buttonColors.contentColor else buttonColors.disabledContentColor,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )
    }
}

@Preview
@Composable
private fun ShoppingButtonPreview() {
    ShoppingCartTheme {
        Column {
            ShoppingButton(
                text = "장바구니 담기",
                onClick = { /*TODO*/ },
            )
            ShoppingButton(
                text = "장바구니 담기",
                enabled = false,
                onClick = { /*TODO*/ },
            )
        }
    }
}