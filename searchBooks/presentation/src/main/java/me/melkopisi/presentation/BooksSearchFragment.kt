package me.melkopisi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.melkopisi.presentation.databinding.FragmentBooksSearchBinding

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksSearchFragment : Fragment() {

  private var _binding: FragmentBooksSearchBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentBooksSearchBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}