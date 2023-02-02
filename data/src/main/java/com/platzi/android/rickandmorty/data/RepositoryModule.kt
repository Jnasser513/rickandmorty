package com.platzi.android.rickandmorty.data

import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun characterRepository(
        remoteCharacterDataSource: RemoteCharacterDataSource,
        localCharacterDataSource: LocalCharacterDataSource
    ) = CharacterRepository(
        remoteCharacterDataSource, localCharacterDataSource
    )

    @Provides
    fun episodeRepositoryProvider(
        remoteEpisodeDataSource: RemoteEpisodeDataSource
    ) = EpisodeRepository(remoteEpisodeDataSource)

}