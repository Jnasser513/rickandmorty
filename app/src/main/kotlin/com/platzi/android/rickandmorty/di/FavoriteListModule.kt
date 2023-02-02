package com.platzi.android.rickandmorty.di

import com.platzi.android.rickandmorty.presentation.FavoriteListViewModel
import com.platzi.android.rickandmorty.usecases.GetAllFavoriteCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class FavoriteListModule {

    @Provides
    fun favoriteListViewModelProvider(
        getAllFavoriteCharacterUseCase: GetAllFavoriteCharacterUseCase
    ) = FavoriteListViewModel(getAllFavoriteCharacterUseCase)
}

@Subcomponent(modules = [(FavoriteListModule::class)])
interface FavoriteListComponent {
    val favoriteListViewModel: FavoriteListViewModel
}