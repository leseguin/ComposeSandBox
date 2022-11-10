package fr.leane.seguin.data.rest

import android.content.Context
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.leane.seguin.data.Constants.gson
import fr.leane.seguin.data.rest.ApiKeys.rapidAPIKey
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun getOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
        okHttpClientBuilder.build()


    fun standardOkHttpBuilder(
        context: Context,
        rapidApiInterceptor: Interceptor
    ): OkHttpClient.Builder {
        val cacheSize = (20 * 1024 * 1024).toLong() // 20 MiB
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(rapidApiInterceptor)
    }

    @ExperimentalSerializationApi
    fun createRetrofit(url: HttpUrl, okHttpClient: Lazy<OkHttpClient>): Retrofit =
        Retrofit.Builder().callFactory { request ->
            okHttpClient.get().newCall(request)
        }
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url.newBuilder().build()).build()


    @RapidApiInterceptor
    @Provides
    fun versionInterceptor(): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(
                        "X-RapidAPI-Key",
                        rapidAPIKey
                    )
                    .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .build()
            )
        }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RapidApiInterceptor