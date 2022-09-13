package me.melkopisi.searchbooks.presentation.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import me.melkopisi.core.exceptions.LibrarianException
import me.melkopisi.core.models.BooksUiModel
import me.melkopisi.searchbooks.domain.models.BooksDomainModel
import me.melkopisi.searchbooks.domain.usecases.SearchBooksUseCase
import me.melkopisi.searchbooks.presentation.mappers.toUiModel
import me.melkopisi.searchbooks.presentation.utils.MainCoroutineRule
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class BooksSearchViewModelTest {
  @OptIn(ExperimentalCoroutinesApi::class)
  @get:Rule var mainCoroutineRule = MainCoroutineRule()

  private lateinit var viewModel: BooksSearchViewModel
  @Mock private lateinit var searchBooksUseCase: SearchBooksUseCase
  private val testResults: MutableList<BooksListState> = mutableListOf()

  @Before
  fun setup() {
    viewModel = BooksSearchViewModel(searchBooksUseCase)
  }

  @After
  fun teardown() {
    testResults.clear()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading state`() = runBlockingTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book)))

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)

    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Init::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading and success state`() = runTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")
    val bookUiModel: BooksUiModel.Doc = book.toUiModel()

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book)))

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Success::class.java))
    Assert.assertEquals(listOf(bookUiModel), (testResults[1] as BooksListState.Success).list)

    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading and fail state and return NetworkNotAvailable Exception`() = runTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book))
        .map {
          throw LibrarianException.NetworkNotAvailable()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    val t: BooksListState = testResults[1]
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Fail::class.java))
    Assert.assertEquals("Network is not available.", (testResults[1] as BooksListState.Fail).msg)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading and fail state and return NoData Exception`() = runTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book))
        .map {
          throw LibrarianException.NoData()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    val t: BooksListState = testResults[1]
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Fail::class.java))
    Assert.assertEquals("No data.", (testResults[1] as BooksListState.Fail).msg)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading and fail state and return DataRetrievingFail Exception`() = runTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book))
        .map {
          throw LibrarianException.DataRetrievingFail()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    val t: BooksListState = testResults[1]
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Fail::class.java))
    Assert.assertEquals("Error getting data from server.", (testResults[1] as BooksListState.Fail).msg)
    job.cancel()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `when searchBooks() then return Init then loading and fail state and return NoLocalData Exception`() = runTest {
    // Arrange
    val book: BooksDomainModel.Doc = BooksDomainModel.Doc("key", "title", listOf("1", "2", "3"), listOf("1", "2", "3"), "coverId")

    Mockito.lenient().`when`(searchBooksUseCase("test", 1, true))
      .thenReturn(flowOf(listOf(book))
        .map {
          throw LibrarianException.NoLocalData()
        })

    val job = launch {
      viewModel.screenState.toList(testResults)
    }

    // Act
    viewModel.searchBooks("test", true)
    advanceUntilIdle()
    println(testResults.size)
    // Assert
    val t: BooksListState = testResults[1]
    MatcherAssert.assertThat(testResults[0], IsInstanceOf.instanceOf(BooksListState.Loading::class.java))
    MatcherAssert.assertThat(testResults[1], IsInstanceOf.instanceOf(BooksListState.Fail::class.java))
    Assert.assertEquals("No local data.", (testResults[1] as BooksListState.Fail).msg)
    job.cancel()
  }
}