package me.melkopisi.searchbooks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.melkopisi.core.models.BooksUiModel
import me.melkopisi.searchbooks.domain.models.BooksDomainModel
import me.melkopisi.searchbooks.domain.usecases.SearchBooksUseCase
import me.melkopisi.searchbooks.presentation.mappers.toUiModel
import me.melkopisi.searchbooks.presentation.viewmodels.BooksListState.Init
import javax.inject.Inject

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@HiltViewModel
class BooksSearchViewModel @Inject constructor(
  private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {

  private val _screenState = MutableStateFlow<BooksListState>(Init)
  val screenState: StateFlow<BooksListState> = _screenState.asStateFlow()

  private fun setLoading() {
    _screenState.value = BooksListState.Loading
  }

  private fun setError(msg: String?) {
    _screenState.value = BooksListState.Fail(msg = msg)
  }

  private fun setSuccess(books: List<BooksUiModel.Doc>) {
    _screenState.value = BooksListState.Success(list = books)
  }

  private var currentOffset: Int = 1
  private var currentSize: Int = 15
  var query: String = ""
  private var cachedList: MutableList<BooksUiModel.Doc> = mutableListOf()

  private fun coroutinesExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    setError(throwable.message)
  }

  fun searchBooks(searchQuery: String, isFirstTime: Boolean = false) {
    setLoading()
    query = searchQuery
    viewModelScope.launch(coroutinesExceptionHandler()) {
      searchBooksUseCase(query = searchQuery, offset = currentOffset)
        .onStart { setLoading() }
        .collect { docs ->
          currentSize = docs.size
          currentOffset += currentSize
          if (isFirstTime) cachedList.clear()
          docs.addToCache()
          setSuccess(cachedList)
        }
    }
  }

  private fun List<BooksDomainModel.Doc>.addToCache() {
    cachedList.addAll(map { it.toUiModel() })
  }
}

sealed class BooksListState {
  object Init : BooksListState()
  object Loading : BooksListState()
  data class Success(val list: List<BooksUiModel.Doc>) : BooksListState()
  data class Fail(val msg: String?) : BooksListState()
}