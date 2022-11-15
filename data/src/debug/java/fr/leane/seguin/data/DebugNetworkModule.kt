package fr.leane.seguin.data

import android.content.Context
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.migration.DisableInstallInCheck
import fr.leane.seguin.data.rest.ImdbApi
import fr.leane.seguin.data.rest.NetworkModule
import fr.leane.seguin.data.rest.RapidApiImdb
import fr.leane.seguin.data.rest.RapidApiInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
@DisableInstallInCheck
object DebugNetworkModule {
    @Provides
    fun provideOkHttpBuilder(
        @ApplicationContext context: Context,
        @RapidApiInterceptor rapidApiInterceptor: Interceptor
    ): OkHttpClient.Builder =
        NetworkModule.standardOkHttpBuilder(context, rapidApiInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor { message ->
                Timber.tag("OkHttp")
                Timber.v(message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })


    @OptIn(ExperimentalSerializationApi::class)
    @Reusable
    @Provides
    fun provideImdbApi(
        @RapidApiImdb url: HttpUrl,
        okHttpClient: Lazy<OkHttpClient>
    ): ImdbApi =
        NetworkModule.createRetrofit(url, okHttpClient).create(ImdbApi::class.java)
}