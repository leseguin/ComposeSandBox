package fr.leane.seguin.composesandbox

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.leane.seguin.data.DebugNetworkModule
import timber.log.Timber

@Module(includes = [DebugNetworkModule::class])
@InstallIn(SingletonComponent::class)
object DebugModule {
    @Provides
    fun provideTimber(): MutableList<Timber.Tree> {
        return mutableListOf(Timber.DebugTree())
    }
}