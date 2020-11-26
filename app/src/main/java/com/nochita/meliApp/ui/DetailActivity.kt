package com.nochita.meliApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nochita.meliApp.R
import com.nochita.meliApp.domain.SearchResult

class DetailActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_RESULT_EXTRA = "EXTRA_SEARCH_RESULT"
    }

    private lateinit var searchResult : SearchResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent.getParcelableExtra<SearchResult>(SEARCH_RESULT_EXTRA)?.let { it ->
            searchResult = it
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(searchResult))
                .commitNow()
        }
    }
}