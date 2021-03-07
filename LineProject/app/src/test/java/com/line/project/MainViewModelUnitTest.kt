package com.line.project

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.line.project.presentation.MainViewModel
import com.line.project.repository.ImageRepository
import com.line.project.repository.ImageRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelUnitTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private lateinit var viewModel: MainViewModel
    private lateinit var repository: ImageRepository

    @Before
    fun setup() {
        viewModel = MainViewModel()
        repository = ImageRepositoryImpl()
    }

    @Test
    fun parsingDataTest() {
        try {
            viewModel.parsingData(Constants.DATA_API)
        } catch (e: Exception) {
            assert(false)
        }
        assert(true)
    }

    @Test
    fun getDownloadImgTest() = coroutineTestRule.testDispatcher.runBlockingTest {
        viewModel.parsingData(Constants.DATA_API)

        runBlocking {
            delay(1000)
            var bitmap = repository.downloadImg(
                "http://movie.phinf.naver.net/20151127_272/1448585271749MCMVs_JPEG/movie_image.jpg",
                "test.jpg"
            )
            Assert.assertTrue(repository.getContentSize() == 748760L)
        }
    }
}