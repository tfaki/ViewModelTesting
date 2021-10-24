package com.android.viewmodeltesting.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.viewmodeltesting.common.extension.toLiveData
import com.android.viewmodeltesting.data.NewsResponse
import com.android.viewmodeltesting.remote.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.android.viewmodeltesting.common.util.Result
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<Result<NewsResponse>>()
    val newsList = _newsList.toLiveData()

    fun getNews(page: Int) = viewModelScope.launch {
        _newsList.postValue(repository.getNewsSourceList(page))
    }
}