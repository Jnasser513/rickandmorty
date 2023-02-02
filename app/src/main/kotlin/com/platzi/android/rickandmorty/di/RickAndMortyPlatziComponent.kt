package com.platzi.android.rickandmorty.di

import android.app.Application
import com.platzi.android.rickandmorty.dabasemanager.DatabaseModule
import com.platzi.android.rickandmorty.data.RepositoryModule
import com.platzi.android.rickandmorty.requestmanager.APIModule
import com.platzi.android.rickandmorty.usecases.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class, DatabaseModule::class, RepositoryModule::class, UseCaseModule::class])
interface RickAndMortyPlatziComponent {

    fun inject(module: CharacterListModule): CharacterListComponent
    fun inject(module: FavoriteListModule): FavoriteListComponent
    fun inject(module: CharacterDetailModule): CharacterDetailComponent

    @Component.Factory
    interface Favorite {
        fun create(@BindsInstance app: Application): RickAndMortyPlatziComponent
    }

}