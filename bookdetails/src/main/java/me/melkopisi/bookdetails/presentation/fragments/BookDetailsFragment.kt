package me.melkopisi.bookdetails.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.bookdetails.R
import me.melkopisi.bookdetails.databinding.FragmentBookDetailsBinding
import me.melkopisi.core.BaseActivity
import me.melkopisi.core.NavigationKeys
import me.melkopisi.core.Navigator
import me.melkopisi.core.extensions.isInternetAvailable
import me.melkopisi.core.extensions.loadImage
import me.melkopisi.core.extensions.makeSnackBar
import me.melkopisi.core.extensions.navigateTo
import me.melkopisi.core.models.BooksUiModel

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@AndroidEntryPoint
class BookDetailsFragment : Fragment() {

  private var _binding: FragmentBookDetailsBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupToolbar()
    val args = arguments?.getParcelable<BooksUiModel.Doc>(NavigationKeys.BOOK_DETAILS)
    setupViews(args)
  }

  private fun setupToolbar() {

    (requireActivity() as BaseActivity).apply {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
  }

  private fun setupViews(args: BooksUiModel.Doc?) {
    args?.let { doc ->
      with(binding) {
        doc.coverId?.let { coverId -> ivBookCover.loadImage(coverId) }
        tvBookName.text = doc.title
        tvBookAuthor.text = doc.authorName?.firstOrNull().orEmpty()
        tvIsbn.text = getString(R.string.isbn_title, doc.isbn?.joinToString(", ").orEmpty())
        setupClickListener(args)
      }
    }
  }

  private fun setupClickListener(doc: BooksUiModel.Doc) {
    with(binding) {
      tvBookName.setOnClickListener {
        if (requireContext().isInternetAvailable()) {
          navigateTo(Navigator.SearchBooks, bundleOf(NavigationKeys.SEARCH_QUERY to doc.title))
        } else {
          root.makeSnackBar(getString(R.string.network_error_msg, doc.title))
        }
      }

      tvBookAuthor.setOnClickListener {
        if (requireContext().isInternetAvailable()) {
          navigateTo(Navigator.SearchBooks, bundleOf(NavigationKeys.SEARCH_QUERY to doc.authorName?.get(0)))
        } else {
          root.makeSnackBar(getString(R.string.network_error_msg, doc.title))
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}