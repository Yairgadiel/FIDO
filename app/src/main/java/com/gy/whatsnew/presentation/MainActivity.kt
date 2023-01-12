package com.gy.whatsnew.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener
import com.gy.whatsnew.AppBackFromBackgroundListener
import com.gy.whatsnew.R
import com.gy.whatsnew.WNApplication
import com.gy.whatsnew.presentation.article_list.ArticleListFragment
import com.gy.whatsnew.presentation.article_list.ArticlesViewModel
import com.gy.whatsnew.presentation.article_screen.ArticleFragment
import com.gy.whatsnew.utilities.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnBackStackChangedListener, AppBackFromBackgroundListener {

    private val articlesViewModel : ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val articlesFrag = ArticleListFragment.newInstance()
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, articlesFrag, null)
                commit()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener(this)

        supportFragmentManager
            .setFragmentResultListener(Constants.URL_RESULT_KEY, this) { _, bundle ->
                navigateToArticle(bundle)
            }

        (application as WNApplication).addBackFromBackgroundListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(this)
        (application as WNApplication).removeBackFromBackgroundListener(this)
    }

    private fun navigateToArticle(urlBundle: Bundle) {
        val fragment = ArticleFragment.newInstance()
        fragment.arguments = urlBundle
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment, null)
            addToBackStack(null)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackStackChanged() {
        // A change caused no fragments in the backstack - only the article list fragment is visible
        // Fetch articles again
        if (supportFragmentManager.backStackEntryCount == 0) {
            articlesViewModel.fetchArticles()
        }
    }

    override fun onBackFromBackground() {
        articlesViewModel.fetchArticles()
    }
}