package me.melkopisi.searchbooks.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.searchbooks.data.repository.BooksRepositoryImpl
import me.melkopisi.searchbooks.domain.repository.BooksRepository

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindBooksReposRepository(booksRepositoryImpl: BooksRepositoryImpl): BooksRepository
}