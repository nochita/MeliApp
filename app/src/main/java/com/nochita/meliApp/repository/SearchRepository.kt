package com.nochita.meliApp.repository

import android.content.ContentResolver
import com.nochita.meliApp.domain.SearchResult

interface SearchRepository {

    suspend fun getSearchResults(contentResolver: ContentResolver, queryString: String): List<SearchResult>
}