package me.melkopisi.bookdetails.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.bookdetails.R
import me.melkopisi.bookdetails.databinding.FragmentBookDetailsBinding
import me.melkopisi.core.BaseActivity
import me.melkopisi.core.NavigationKeys
import me.melkopisi.core.extensions.loadImage
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
    args?.let { doc ->
      with(binding) {
        doc.coverId?.let { coverId -> ivBookCover.loadImage(coverId) }
        tvBookName.text = doc.title
        tvBookAuthor.text = doc.authorName?.firstOrNull().orEmpty()
        tvIsbn.text = getString(R.string.isbn_title, doc.isbn?.joinToString(", ").orEmpty())
      }
    }
  }

  private fun setupToolbar() {

    (requireActivity() as BaseActivity).apply {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}