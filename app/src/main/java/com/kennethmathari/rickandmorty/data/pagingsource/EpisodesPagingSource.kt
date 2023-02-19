package com.kennethmathari.rickandmorty.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import com.kennethmathari.rickandmorty.domain.mappers.EpisodeMapper
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import com.kennethmathari.rickandmorty.utils.EpisodesUIModel

class EpisodesPagingSource() : PagingSource<Int, EpisodesUIModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodesUIModel> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        val prevPageNumber = if (nextPageNumber == 1) null else nextPageNumber - 1

        val response = RetrofitInstance.apiClient.getEpisodePagedList(nextPageNumber)
        // Handle errors in this block and return LoadResult.Error if it is an expected error (such as a network failure).
        response.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = response.body.results.map { EpisodeMapper.buildFrom(it) },
            prevKey = prevPageNumber,
            nextKey = getPageIndexFromNext(response.body?.info?.next)
        )

    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeDomainModel>): Int? {
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

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}