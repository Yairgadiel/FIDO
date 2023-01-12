package com.gy.whatsnew.presentation.article_list.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gy.whatsnew.data.model.Article

@Composable
fun ArticlesScreen(
    articles: List<Article>,
    isLoading: Boolean,
    onArticleClickedListener: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (isLoading) {
            item {
                CircularProgressIndicator()
            }
        }
        items(articles) {
            ArticleItem(article = it, onArticleClicked = onArticleClickedListener)
        }
    }
}