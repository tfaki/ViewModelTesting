package com.android.viewmodeltesting.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.viewmodeltesting.data.NewsResponse
import com.android.viewmodeltesting.remote.NewsRepository
import com.android.viewmodeltesting.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import com.android.viewmodeltesting.common.util.Result
import com.android.viewmodeltesting.common.util.Status
import org.junit.Assert

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    @MockK
    private lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: NewsViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = NewsViewModel(newsRepository)
    }

    @Test
    fun `when news list called, livedata value is set`() =
        mainCoroutinesRule.runBlockingTest {
            val newsListResult: Result<NewsResponse> = Result(Status.SUCCESS, NewsResponse())

            coEvery { newsRepository.getNewsSourceList(1) } returns newsListResult

            viewModel.getNews(1)
            Assert.assertEquals(viewModel.newsList.value, newsListResult)
        }
}