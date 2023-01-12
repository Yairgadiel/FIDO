package com.gy.whatsnew.presentation.article_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.gy.whatsnew.data.model.Article
import com.gy.whatsnew.network.Resource
import com.gy.whatsnew.presentation.article_list.composables.ArticlesScreen
import com.gy.whatsnew.utilities.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleListFragment : Fragment() {
    private val viewModel : ArticlesViewModel by activityViewModels()

    companion object {
        fun newInstance() = ArticleListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                var isLoading by remember {
                    mutableStateOf(false)
                }

                var articles by remember {
                    mutableStateOf(listOf<Article>())
                }

                viewModel.articlesRes.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            isLoading = true
                        }
                        is Resource.NetworkError -> {
                            isLoading = false
                        }
                        is Resource.Success -> {
                            isLoading = false
                            articles = it.data
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArticlesScreen(
                        articles = articles,
                        isLoading = isLoading) {
                        setFragmentResult(Constants.URL_RESULT_KEY, bundleOf(Constants.URL_ARG_KEY to it))
                    }
                }
            }
        }
    }
}