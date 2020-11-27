package com.nochita.meliApp.viewmodels

import android.content.ContentResolver
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nochita.meliApp.domain.SearchResult
import com.nochita.meliApp.repository.SearchRepository
import com.nochita.meliApp.repository.SearchRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
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