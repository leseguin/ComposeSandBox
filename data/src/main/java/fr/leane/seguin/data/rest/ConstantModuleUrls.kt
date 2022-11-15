package fr.leane.seguin.data.rest

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.leane.seguin.data.R
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@InstallIn(SingletonComponent::class)
object ConstantModule {
    @RapidApiImdb
    @Provides
    fun url(@ApplicationContext context: Context): HttpUrl =
        context.getString(R.string.url_imdb_api).toHttpUrl()
}