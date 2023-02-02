package com.platzi.android.rickandmorty.usecases

import com.platzi.android.rickandmorty.data.CharacterRepository
import com.platzi.android.rickandmorty.data.EpisodeRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getAllCharacterUseCaseProvider(
        characterRepository: CharacterRepository
    ) = GetAllCharacterUseCase(characterRepository)

    @Provides
    fun getAllFavoriteCharacterUseCaseProvider(
        characterRepository: CharacterRepository
    ) = GetAllFavoriteCharacterUseCase(characterRepository)

    @Provides
    fun getFavoriteCharacterStatusUseCase(
        characterRepository: CharacterRepository
    ) = ValidateFavoriteCharacterUseCase(characterRepository)

    @Provides
    fun updateFavoriteCharacterStatusUseCase(
        characterRepository: CharacterRepository
    ) = UpdateFavoriteCharacterStatusUseCase(characterRepository)

    @Provides
    fun getEpisodeFromCharacterUseCase(
        episodeRepository: EpisodeRepository
    ) = GetEpisodeFromCharacterUseCase(episodeRepository)

}