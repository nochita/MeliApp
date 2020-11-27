package com.nochita.meliApp.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nochita.meliApp.R
import com.nochita.meliApp.domain.SearchResult
import com.nochita.meliApp.viewmodels.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.OnSearchResultClicked {

    private val viewModel: ListViewModel by viewModels()
    private var queryString = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerObservers()
        checkPermissions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            queryString = it.getString(QUERY_SEARCH) ?: ""
        }
    }

    // Permission handling
    private fun checkPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.INTERNET),
            PERMISSION_RC
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_RC && grantResults.first() == PermissionChecker.PERMISSION_GRANTED) {
            viewModel.getSearchresults(requireActivity().contentResolver, queryString)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.error_permission),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun registerObservers() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ListViewModel.State.Loading -> progressBar.isVisible = true
                is ListViewModel.State.Success -> {
                    progressBar.isVisible = false
                    list.adapter =
                        ListAdapter(state.searchResults, this)
                }
            }
        })
    }

    companion object {
            const val PERMISSION_RC = 22
            const val QUERY_SEARCH = "QUERY_SEARCH"

        @JvmStatic
        fun newInstance(queryString: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(QUERY_SEARCH, queryString)
                }
            }
    }

    override fun onClicked(searchResult: SearchResult) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SEARCH_RESULT_EXTRA, searchResult)
        startActivity(intent)
    }
}