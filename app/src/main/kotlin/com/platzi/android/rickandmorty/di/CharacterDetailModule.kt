package com.platzi.android.rickandmorty.di

import com.platzi.android.rickandmorty.domain.Character
import com.platzi.android.rickandmorty.presentation.CharacterDetailViewModel
import com.platzi.android.rickandmorty.usecases.GetAllFavoriteCharacterUseCase
import com.platzi.android.rickandmorty.usecases.GetEpisodeFromCharacterUseCase
import com.platzi.android.rickandmorty.usecases.UpdateFavoriteCharacterStatusUseCase
import com.platzi.android.rickandmorty.usecases.ValidateFavoriteCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class CharacterDetailModule(
    private val character: Character?
) {

    @Provides
    fun characterDetailViewModelProvider(
        getEpisodeFromCharacterUseCase: GetEpisodeFromCharacterUseCase,
        getFavoriteCharacterStatusUseCase: ValidateFavoriteCharacterUseCase,
        updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase
    ) = CharacterDetailViewModel(character, getEpisodeFromCharacterUseCase, getFavoriteCharacterStatusUseCase, updateFavoriteCharacterStatusUseCase)

}

@Subcomponent(modules = [(CharacterDetailModule::class)])
interface CharacterDetailComponent {
    val characterDetailViewModel: CharacterDetailViewModel
}
