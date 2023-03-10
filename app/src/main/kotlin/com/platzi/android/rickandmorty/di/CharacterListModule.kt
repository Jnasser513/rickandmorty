package com.platzi.android.rickandmorty.di

import com.platzi.android.rickandmorty.domain.Character
import com.platzi.android.rickandmorty.presentation.CharacterListViewModel
import com.platzi.android.rickandmorty.usecases.GetAllCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class CharacterListModule {

    @Provides
    fun characterListViewModelProvider(
        getAllCharacterUseCase: GetAllCharacterUseCase
    ) = CharacterListViewModel(getAllCharacterUseCase)

}

@Subcomponent(modules = [(CharacterListModule::class)])
interface CharacterListComponent {
    val characterListViewModel: CharacterListViewModel
}