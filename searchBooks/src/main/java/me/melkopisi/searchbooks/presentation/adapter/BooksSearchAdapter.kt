package me.melkopisi.searchbooks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
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
  private var items: List<BooksUiModel.Doc> = listOf()
  private lateinit var itemCallback: (BooksUiModel.Doc) -> Unit

  inner class BookViewHolder(private val binding: ItemBookBinding, private val itemCallback: (BooksUiModel.Doc) -> Unit) :
    ViewHolder(binding.root) {

    fun bind(item: BooksUiModel.Doc) {
      with(binding) {
        tvBookTitle.text = item.title
        item.authorName?.let {
          tvBookAuthor.text = it.joinToString(", ")
        } ?: kotlin.run { tvBookAuthor.isVisible = false }
        ivBookCover.loadImage(item.coverId)

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
    holder.bind(items[position])
  }

  override fun getItemCount(): Int = items.size

  fun setData(booksList: List<BooksUiModel.Doc>) {
    items = booksList
    notifyItemRangeChanged(0, booksList.size)
  }

  fun onItemClick(callback: (BooksUiModel.Doc) -> Unit) {
    this.itemCallback = callback
  }
}