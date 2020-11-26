package com.nochita.meliApp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nochita.meliApp.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_edittext.requestFocus()
        search_button.setOnClickListener {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(ListActivity.QUERY_SEARCH_EXTRA, search_edittext.text.toString().trim())
            startActivity(intent)
        }
    }
}