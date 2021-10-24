package com.android.viewmodeltesting.ui.news

import android.util.Log
import androidx.fragment.app.viewModels
import com.android.viewmodeltesting.R
import com.android.viewmodeltesting.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private val viewModel: NewsViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun bindScreen() {

        arrangeObserver()
        sendRequest()
    }

    private fun arrangeObserver() {
        viewModel.newsList.observe(this) {
            Log.d("Result", it.status.toString())
        }
    }

    private fun sendRequest() {
        viewModel.getNews(1)
    }

}