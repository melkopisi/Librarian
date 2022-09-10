package me.melkopisi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.data.network.api.BooksApi
import retrofit2.Retrofit
import javax.inject.Singleton

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
  @Singleton
  @Provides fun providesBooksApi(retrofit: Retrofit): BooksApi = retrofit.create(BooksApi::class.java)
}