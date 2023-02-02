package com.platzi.android.rickandmorty.data

import com.platzi.android.rickandmorty.domain.Character
import com.platzi.android.rickandmorty.domain.Episode
import io.reactivex.Single

class CharacterRepository(
    private val remoteCharacterDataSource: RemoteCharacterDataSource,
    private val localCharacterDataSource: LocalCharacterDataSource
) {
    fun getAllCharacters(page: Int): Single<List<Character>> =
        remoteCharacterDataSource.getAllCharacters(page)

    fun getAllFavoriteCharacter() = localCharacterDataSource.getAllFavoriteCharacters()

    fun getFavoriteCharacterStatus(id: Int) = localCharacterDataSource.getFavoriteCharacterStatus(id)

    fun updateFavoriteCharacterStatus(character: Character) = localCharacterDataSource.updateFavoriteCharacterStatus(character)
}

class EpisodeRepository(
    private val remoteEpisodeDataSource: RemoteEpisodeDataSource
) {
    fun getEpisodesByCharacter(episodeUrlList: List<String>): Single<List<Episode>> =
        remoteEpisodeDataSource.getEpisodesByCharacter(episodeUrlList)
}