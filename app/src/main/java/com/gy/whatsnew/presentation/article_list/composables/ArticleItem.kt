package com.gy.whatsnew.presentation.article_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gy.whatsnew.data.model.Article
import com.gy.whatsnew.data.model.Source

@Composable
fun ArticleItem(article: Article, onArticleClicked: (String) -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onArticleClicked(article.url) }) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = article.title,
            modifier = Modifier
                .height(64.dp)
                .aspectRatio(1.25f),
            contentScale = ContentScale.Crop,
        )
        
        Spacer(modifier = Modifier.padding(8.dp))

        Column {
            Text(text = article.title)

            article.author?.let {
                Text(text = "By $it", style = TextStyle(color = Color.Gray))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePrev() {
    ArticleItem(article) {}
}


val article = Article(
    source = Source(
        id = null,
        name = "Gizmodo.com"
    ),
    author = "Lucas Ropek",
    title = "OG Bitcoin Core Developer Claims Hack Drained Nearly All His BTC",
    description = "Even cryptocurrency’s most accomplished tech wizards apparently aren’t immune from the occasional wallet-draining hack. Luke Dashjr, one of the original core developers for Bitcoin, claims that someone swiped hundreds of BTC from his accounts late last year—l…",
    url = "https://gizmodo.com/bitcoin-price-hack-217-btc-og-developer-luke-dashjr-1849944799",
    urlToImage = "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/c8e3b3fe0595dfbab3656a5ba0e06e2c.jpg",
    publishedAt = "2023-01-03T20:48:00Z",
    content = "Even cryptocurrencys most accomplished tech wizards apparently arent immune from the occasional wallet-draining hack. Luke Dashjr, one of the original core developers for Bitcoin, claims that someone… [+2723 chars]"
)