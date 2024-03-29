package com.iroyoraso.testprivalia.dagger.application

import com.iroyoraso.testprivalia.BuildConfig
import com.iroyoraso.testprivalia.TIMEOUT
import com.iroyoraso.testprivalia.data.network.SearchApi
import com.iroyoraso.testprivalia.data.network.MovieApi
import com.iroyoraso.testprivalia.data.network.QueryParamsInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

@Module
class NetworkModule {

    private val level = if (BuildConfig.DEBUG) BODY else NONE

    @Singleton
    @Provides
    fun queryInterceptor() : QueryParamsInterceptor = QueryParamsInterceptor()

    @Singleton
    @Provides
    fun logInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(level)

    @Singleton
    @Provides
    fun okHttpClient(queryInterceptor: QueryParamsInterceptor, logInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(logInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun getMovieApi(retrofit: Retrofit) : MovieApi = retrofit.create(MovieApi::class.java)


    @Singleton
    @Provides
    fun getKeywordApi(retrofit: Retrofit) : SearchApi = retrofit.create(SearchApi::class.java)

}