package me.melkopisi.searchbooks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.melkopisi.core.extensions.loadImage
import me.melkopisi.core.models.BooksUiModel
import me.melkopisi.searchBooks.databinding.ItemBookBinding
import me.melkopisi.searchbooks.presentation.adapter.BooksSearchAdapter.BookViewHolder

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksSearchAdapter : RecyclerView.Adapter<BookViewHolder>() {
  private lateinit var itemCallback: (BooksUiModel.Doc) -> Unit

  private val diffCallback = object : DiffUtil.ItemCallback<BooksUiModel.Doc>() {
    override fun areItemsTheSame(oldItem: BooksUiModel.Doc, newItem: BooksUiModel.Doc): Boolean {
      return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: BooksUiModel.Doc, newItem: BooksUiModel.Doc): Boolean {
      return oldItem == newItem
    }
  }
  private val differ = AsyncListDiffer(this, diffCallback)

  inner class BookViewHolder(private val binding: ItemBookBinding, private val itemCallback: (BooksUiModel.Doc) -> Unit) :
    ViewHolder(binding.root) {

    fun bind(item: BooksUiModel.Doc) {
      with(binding) {
        tvBookTitle.text = item.title
        item.authorName?.let {
          tvBookAuthor.text = it.joinToString(", ")
        } ?: kotlin.run { tvBookAuthor.isVisible = false }
        item.coverId?.let { ivBookCover.loadImage(it) }

        root.setOnClickListener { itemCallback(item) }
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
    return BookViewHolder(
      ItemBookBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      ), itemCallback
    )
  }

  override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
    holder.bind(differ.currentList[position])
  }

  override fun getItemCount(): Int = differ.currentList.size

  fun setData(booksList: List<BooksUiModel.Doc>) {
    differ.submitList(booksList)
  }

  fun onItemClick(callback: (BooksUiModel.Doc) -> Unit) {
    this.itemCallback = callback
  }
}