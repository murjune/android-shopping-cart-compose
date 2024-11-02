package nextstep.shoppingcart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ProductRepository

class DefaultProductRepository : ProductRepository {
    private var products: List<Product> = listOf(
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
        ),
        Product(
            id = 3,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/mid/015003.png", "뮤", 500000
        ),
        Product(
            id = 4,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/082001.png",
            "요씽리스",
            1000000
        ),
        Product(
            id = 6,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/044802.png", "메가루카리오", 3333
        ),
        Product(
            id = 7,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/048301.png", "디아루가", 7777
        ),
        Product(
            id = 8,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/064603.png", "블랙큐레무", 99999
        ),
        Product(
            id = 9,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/088802.png", "자시안", 1000000
        ),
        Product(
            id = 10,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/089001.png",
            "무한다이노",
            99999999
        ),
        Product(
            id = 11,
            "https://data1.pokemonkorea.co.kr/newdata/pokedex/full/064401.png",
            "제크로무",
            33333333
        )
    )

    override fun products(): Flow<List<Product>> = flow { emit(products) }

    override fun productBy(id: Long): Product? = products.find { it.id == id }
}