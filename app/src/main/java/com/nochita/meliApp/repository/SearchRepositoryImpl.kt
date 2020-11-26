package com.nochita.meliApp.repository

import android.content.ContentResolver
import com.nochita.meliApp.domain.SearchResult
import com.nochita.meliApp.domain.SearchResultResponse
import com.nochita.meliApp.network.MeliInterface
import com.nochita.meliApp.network.MeliService

class SearchRepositoryImpl : SearchRepository {

    private val client = MeliService.getRetrofit().create(MeliInterface::class.java)

    override suspend fun getSearchResults(
        contentResolver: ContentResolver,
        queryString: String
    ): List<SearchResult> {
        return client.getSearchResults(queryString).results
    }
}
