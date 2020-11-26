package com.nochita.meliApp.network

import com.nochita.meliApp.domain.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliInterface {

    @GET("/sites/MLA/search")
    suspend fun getSearchResults(@Query("q") queryString : String
    ) : SearchResultResponse

}