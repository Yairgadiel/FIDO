package com.gy.whatsnew.presentation.article_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gy.whatsnew.R
import com.gy.whatsnew.utilities.Constants
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ArticleFragment"

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(Constants.URL_ARG_KEY)
        if (url == null) {
            Log.e(TAG, "onViewCreated: No url passed!")
            throw java.lang.IllegalStateException("No url passed to Article fragment")
        }

        val webView : WebView= view.findViewById(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onStart() {
        super.onStart()

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        super.onStop()
    }
}