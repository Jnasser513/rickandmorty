package com.platzi.android.rickandmorty.usecases

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.platzi.android.rickandmorty.data.CharacterRepository
import com.platzi.android.rickandmorty.domain.Character
import com.platzi.android.rickandmorty.domain.Location
import com.platzi.android.rickandmorty.domain.Origin
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllCharacterUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var getAllCharacterUseCase: GetAllCharacterUseCase

    @Before
    fun setUp() {
        getAllCharacterUseCase = GetAllCharacterUseCase(characterRepository)
    }

    @Test
    fun `get all character use case should return a list of characters given a page`() {
        val expectedResult = listOf(mockedCharacter.copy(id = 1))
        given(characterRepository.getAllCharacters(any())).willReturn(Single.just(expectedResult))

        getAllCharacterUseCase.invoke(1)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(expectedResult)
    }

}

@Mock
val mockedLocation = Location(
    "",
    ""
)

@Mock
val mockedOrigin = Origin(
    "",
    ""
)

@Mock
val mockedCharacter =
    Character(
        0,
        "",
        "",
        "",
        "",
        "",
        mockedOrigin,
        mockedLocation,
        listOf("")
    )