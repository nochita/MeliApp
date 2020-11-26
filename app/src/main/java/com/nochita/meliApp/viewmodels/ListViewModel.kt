package com.nochita.meliApp.viewmodels

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nochita.meliApp.domain.SearchResult
import com.nochita.meliApp.repository.SearchRepository
import com.nochita.meliApp.repository.SearchRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel (
    private val searchRepository: SearchRepository = SearchRepositoryImpl(),
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        _state.value = State.Loading()
    }

    fun getSearchresults(contentResolver: ContentResolver, queryString : String) {
        viewModelScope.launch(coroutineDispatcher) {
            _state.postValue(
                State.Success(
                    searchRepository.getSearchResults(contentResolver, queryString)
                )
            )
        }
    }

    sealed class State {
        class Loading : State()
        class Success(val searchResults: List<SearchResult>) : State()
    }
}