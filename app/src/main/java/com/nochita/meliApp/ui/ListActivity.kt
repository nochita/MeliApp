package com.nochita.meliApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nochita.meliApp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    companion object {
        const val QUERY_SEARCH_EXTRA = "EXTRA_SEARCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var queryString = ""
        intent.getStringExtra(QUERY_SEARCH_EXTRA)?.let {
            queryString = it
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance(queryString))
                .commitNow()
        }
    }
}