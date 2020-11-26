package com.nochita.meliApp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult (
    val id : String,
    val title : String,
    val price : String,
    val thumbnail : String
) : Parcelable