package com.kennethmathari.rickandmorty.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.data.repository.EpisodeListRepository
import kotlinx.coroutines.CoroutineScope

class EpisodesPagingSource(
    private val coroutineScope: CoroutineScope,
    private val episodeListRepository: EpisodeListRepository
): PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val prevPageNumber = if (nextPageNumber == 1) null else nextPageNumber - 1
            val response = episodeListRepository.getEpisodePagedList()
            return LoadResult.Page(
                data = emptyList(),
                prevKey = prevPageNumber,
                nextKey = nextPageNumber+1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}