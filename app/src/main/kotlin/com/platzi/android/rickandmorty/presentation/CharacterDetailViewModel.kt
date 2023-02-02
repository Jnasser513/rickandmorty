package com.platzi.android.rickandmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.android.rickandmorty.domain.Character
import com.platzi.android.rickandmorty.domain.Episode
import com.platzi.android.rickandmorty.presentation.utils.Event
import com.platzi.android.rickandmorty.usecases.GetEpisodeFromCharacterUseCase
import com.platzi.android.rickandmorty.usecases.UpdateFavoriteCharacterStatusUseCase
import com.platzi.android.rickandmorty.usecases.ValidateFavoriteCharacterUseCase
import io.reactivex.disposables.CompositeDisposable

class CharacterDetailViewModel(
    private val character: Character? = null,
    private val getEpisodeFromCharacterUseCase: GetEpisodeFromCharacterUseCase,
    private val validateFavoriteCharacterUseCase: ValidateFavoriteCharacterUseCase,
    private val updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase
): ViewModel() {

    private val disposable = CompositeDisposable()

    private val _characterValues = MutableLiveData<Character>()
    val characterValues: LiveData<Character> get() = _characterValues

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _events = MutableLiveData<Event<CharacterDetailNavigation>>()
    val events: LiveData<Event<CharacterDetailNavigation>> get() = _events

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onCharacterValidation() {
        if(character == null) {
            _events.value = Event(CharacterDetailNavigation.CloseActivity)
            return
        }

        _characterValues.value = character
        validateFavoriteCharacterStatus(character.id)
        requestShowEpidoseList(character.episodeList)
    }

    fun onUpdateFavoriteCharacterStatus() {
        disposable.add(
            updateFavoriteCharacterStatusUseCase
                .invoke(character!!)
                .subscribe { isFavorite ->
                    _isFavorite.value = isFavorite
                }
        )
    }

    private fun validateFavoriteCharacterStatus(characterId: Int) {
        disposable.add(
            validateFavoriteCharacterUseCase
                .invoke(characterId)
                .subscribe { isFavorite ->
                    _isFavorite.value = isFavorite
                }
        )
    }

    private fun requestShowEpidoseList(episodeUrlList: List<String>) {
        disposable.add(
            getEpisodeFromCharacterUseCase
                .invoke(episodeUrlList)
                .doOnSubscribe {
                    _events.value = Event(CharacterDetailNavigation.ShowEpisodeListLoading)
                }
                .subscribe(
                    { episodeList ->
                        _events.value = Event(CharacterDetailNavigation.HideEpisodeListLoading)
                        _events.value = Event(CharacterDetailNavigation.ShowEpisodeList(episodeList))
                    },
                    { error ->
                        _events.value = Event(CharacterDetailNavigation.HideEpisodeListLoading)
                        _events.value = Event(CharacterDetailNavigation.ShowEpisodeError(error))
                    })
        )
    }

    sealed class CharacterDetailNavigation {
        data class ShowEpisodeList(val episodeList: List<Episode>): CharacterDetailNavigation()
        data class ShowEpisodeError(val error: Throwable): CharacterDetailNavigation()
        object CloseActivity: CharacterDetailNavigation()
        object ShowEpisodeListLoading: CharacterDetailNavigation()
        object HideEpisodeListLoading: CharacterDetailNavigation()
    }

    /*disposable.add(
    characterDao.getCharacterById(character!!.id)
    .isEmpty
    .flatMapMaybe { isEmpty ->
        Maybe.just(!isEmpty)
    }
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribe { isFavorite ->
        updateFavoriteIcon(isFavorite)
    }
    )*/

}