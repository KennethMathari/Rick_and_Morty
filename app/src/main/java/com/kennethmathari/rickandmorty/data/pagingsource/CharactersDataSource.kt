package com.kennethmathari.rickandmorty.data.pagingsource


import androidx.paging.PageKeyedDataSource
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val charactersListRepository: CharactersListRepository,
) : PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>,
    ) {
        coroutineScope.launch {
            val charactersListPage = charactersListRepository.getCharactersListPage(1)

            if (charactersListPage == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }
            callback.onResult(
                charactersListPage.results,
                null,
                getPageIndexFromNext(charactersListPage.info.next)
            )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutineScope.launch {
            val charactersListPage = charactersListRepository.getCharactersListPage(1)

            if (charactersListPage == null) {
                callback.onResult(emptyList(), null)
                return@launch
            }
            callback.onResult(
                charactersListPage.results,
                getPageIndexFromNext(charactersListPage.info.next)
            )
        }
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}