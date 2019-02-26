package com.iroyoraso.testprivalia.data.network

import com.iroyoraso.testprivalia.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

/**
 * Created by iroyo on 15/2/19.
 * Mail: iroyoraso@gmail.com
 */
class QueryParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url()

        val url = httpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
            .addQueryParameter("language", Locale.US.language)
            .build()

        val builder = request.newBuilder().url(url)
        return chain.proceed(builder.build())
    }

}