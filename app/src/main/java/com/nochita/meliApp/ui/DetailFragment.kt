package com.nochita.meliApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nochita.meliApp.R
import com.nochita.meliApp.domain.SearchResult
import kotlinx.android.synthetic.main.fragment_detail.*

private const val SEARCH_RESULT = "search_result"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var searchResult : SearchResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchResult = it.getParcelable(SEARCH_RESULT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResult?.let {
            detail_title.text = it.title
            detail_price.text = "$ " + it.price
            Glide.with(view).load(it.thumbnail).into(detail_image)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchResult SearchResult.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(searchResult: SearchResult) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(SEARCH_RESULT, searchResult)
                }
            }
    }
}