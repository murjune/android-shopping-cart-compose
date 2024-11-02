package nextstep.shoppingcart.presentation.product.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.shoppingcart.domain.model.Product

class ProductPreviewParameterProvider : PreviewParameterProvider<List<Product>> {
    override val values: Sequence<List<Product>>
        get() = sequenceOf(
            listOf(
                Product(
                    id = 5,
                    "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/005401.png",
                    "고라파덕은 오둥이가 아니다람쥐",
                    10
                ),
                Product(
                    id = 1,
                    "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/000401.png",
                    "파이리",
                    500,
                ),
                Product(
                    id = 2,
                    "https://data1.pokemonkorea.co.kr/newdata/pokedex/mid/013002.png",
                    "메가갸라도스",
                    2000
                )
            ),
            emptyList(),
        )
}
