package me.melkopisi.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.data.remote.repository.BooksRepositoryImpl
import me.melkopisi.domain.repository.BooksRepository

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