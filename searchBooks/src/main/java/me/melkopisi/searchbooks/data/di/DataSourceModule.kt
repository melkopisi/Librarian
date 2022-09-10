package me.melkopisi.searchbooks.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.searchbooks.data.local.datasource.BooksLocalDataSource
import me.melkopisi.searchbooks.data.local.datasource.BooksLocalDataSourceImpl
import me.melkopisi.searchbooks.data.remote.datasource.BooksRemoteDataSource
import me.melkopisi.searchbooks.data.remote.datasource.BooksRemoteDataSourceImpl

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
  @Binds
  abstract fun bindBookRemoteDataSource(booksRemoteDataSourceImpl: BooksRemoteDataSourceImpl): BooksRemoteDataSource

  @Binds
  abstract fun bindBookLocalDataSource(booksLocalDataSourceImpl: BooksLocalDataSourceImpl): BooksLocalDataSource
}