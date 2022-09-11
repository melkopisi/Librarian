package me.melkopisi.searchbooks.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.melkopisi.core.NavigationKeys
import me.melkopisi.core.Navigator
import me.melkopisi.core.extensions.hideKeyboard
import me.melkopisi.core.extensions.isInternetAvailable
import me.melkopisi.core.extensions.makeSnackBar
import me.melkopisi.core.extensions.navigateTo
import me.melkopisi.searchBooks.R
import me.melkopisi.searchBooks.databinding.FragmentBooksSearchBinding
import me.melkopisi.searchbooks.general.EndlessRecyclerViewScrollListener
import me.melkopisi.searchbooks.presentation.adapter.BooksSearchAdapter
import me.melkopisi.searchbooks.presentation.viewmodels.BooksListState
import me.melkopisi.searchbooks.presentation.viewmodels.BooksSearchViewModel

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@AndroidEntryPoint
class BooksSearchFragment : Fragment() {

  private var _binding: FragmentBooksSearchBinding? = null
  private val binding get() = _binding!!

  private val searchLibraryViewModel: BooksSearchViewModel by viewModels()

  private val booksSearchAdapter by lazy { BooksSearchAdapter() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentBooksSearchBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args = arguments?.getString(NavigationKeys.SEARCH_QUERY)
    args?.let { setupSearchFromDetails(it) }
    setupEmptyState(true)
    collectBooks()
    setupSearchView()
    setupRecyclerView()
    loadOfflineList()
    onBookClick()
  }

  private fun setupSearchFromDetails(query: String) {
    binding.appBarLayout.isVisible = false
    searchLibraryViewModel.searchBooks(query, isFirstTime = true)
  }

  private fun onBookClick() {
    booksSearchAdapter.onItemClick {
      navigateTo(Navigator.BookDetails, bundleOf(NavigationKeys.BOOK_DETAILS to it))
    }
  }

  private fun setupSearchView() {
    binding.search.apply {
      isSubmitButtonEnabled = true
      setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
          if (requireContext().isInternetAvailable()) {
            query?.let { searchLibraryViewModel.searchBooks(it, isFirstTime = true) } ?: kotlin.run {
              makeSnackBar(getString(R.string.search_error))
            }
          } else {
            makeSnackBar(getString(R.string.network_error, query))
          }
          hideKeyboard()
          return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
          if (newText.equals("")) this.onQueryTextSubmit(null)
          return true
        }
      })

    }
  }

  private fun setupRecyclerView() = binding.booksRecyclerview.apply {
    itemAnimator = null
    addOnScrollListener(object : EndlessRecyclerViewScrollListener(
      layoutManager as LinearLayoutManager
    ) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
        if (requireContext().isInternetAvailable()) this@BooksSearchFragment.searchLibraryViewModel.searchBooks(searchLibraryViewModel.query)
      }
    })
    adapter = this@BooksSearchFragment.booksSearchAdapter
  }

  private fun loadOfflineList() {
    if (requireContext().isInternetAvailable().not()) searchLibraryViewModel.searchBooks("", true)
  }

  private fun initLoading(isLoading: Boolean) {
    binding.progressBar.isVisible = isLoading
  }

  private fun setupEmptyState(isEmpty: Boolean) {
    binding.inclEmptyView.llEmptyList.isVisible = isEmpty
  }

  private fun collectBooks() {
    collectLifecycleFlow(searchLibraryViewModel.screenState) { state ->
      initLoading(state is BooksListState.Loading)
      when (state) {
        is BooksListState.Success -> {
          booksSearchAdapter.setData(state.list)
          setupEmptyState(false)
        }
        is BooksListState.Fail -> {
          binding.root.makeSnackBar(state.msg ?: getString(R.string.general_error))
          setupEmptyState(true)
        }
        else -> Unit
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

fun <T> Fragment.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
  lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
      flow.collectLatest { collect(it) }
    }
  }
}