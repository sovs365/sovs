package com.university.voting.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.university.voting.api.*
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class ElectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VotingRepository()
    private val prefs = application.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val _elections = MutableLiveData<List<ElectionResponse>>()
    val elections: LiveData<List<ElectionResponse>> = _elections

    private val _openElections = MutableLiveData<List<ElectionResponse>>()
    val openElections: LiveData<List<ElectionResponse>> = _openElections

    private val _positions = MutableLiveData<List<PositionResponse>>()
    val positions: LiveData<List<PositionResponse>> = _positions

    private val _candidates = MutableLiveData<List<CandidateResponse>>()
    val candidates: LiveData<List<CandidateResponse>> = _candidates

    private val _results = MutableLiveData<ElectionResultsResponse>()
    val results: LiveData<ElectionResultsResponse> = _results

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private fun getToken() = prefs.getString("token", "") ?: ""

    fun loadAllElections() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.getAllElections().onSuccess {
                _elections.postValue(it)
            }.onFailure {
                _message.postValue("Failed to load elections: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun loadOpenElections() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.getOpenElections().onSuccess {
                _openElections.postValue(it)
            }.onFailure {
                _message.postValue("Failed to load elections: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun loadAllPositions() {
        viewModelScope.launch {
            repository.getAllPositions().onSuccess {
                _positions.postValue(it)
            }
        }
    }

    fun loadCandidatesForPosition(positionId: String) {
        viewModelScope.launch {
            repository.getCandidatesForPosition(positionId).onSuccess {
                _candidates.postValue(it)
            }
        }
    }

    fun loadAllCandidates() {
        viewModelScope.launch {
            repository.getAllCandidates().onSuccess {
                _candidates.postValue(it)
            }
        }
    }

    fun createElection(title: String, description: String, positionId: String, startDate: Long, endDate: Long) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val request = CreateElectionRequest(title, description, positionId, startDate, endDate)
            repository.createElection(getToken(), request).onSuccess {
                _message.postValue("Election posted successfully!")
                loadAllElections()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun updateElectionStatus(electionId: String, status: String) {
        viewModelScope.launch {
            repository.updateElectionStatus(getToken(), electionId, status).onSuccess {
                _message.postValue("Election status updated")
                loadAllElections()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun createPosition(
        name: String,
        type: String,
        facultyId: String?,
        courseId: String?,
        subjectCombinationId: String? = null,
        yearOfStudy: String? = null
    ) {
        viewModelScope.launch {
            val request = CreatePositionRequest(name, type, facultyId, courseId, subjectCombinationId, yearOfStudy)
            repository.createPosition(getToken(), request).onSuccess {
                _message.postValue("Position saved")
                loadAllPositions()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun loadElectionResults(electionId: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.getElectionResults(electionId).onSuccess {
                _results.postValue(it)
            }.onFailure {
                _message.postValue("Failed to load results: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun castVote(electionId: String, votes: List<PositionVote>) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val request = CastVoteRequest(electionId, votes)
            repository.castVote(getToken(), request).onSuccess {
                _message.postValue("Votes cast successfully!")
            }.onFailure {
                _message.postValue("Voting failed: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun verifyCandidate(candidateId: String) {
        viewModelScope.launch {
            repository.verifyCandidate(getToken(), candidateId, true).onSuccess {
                _message.postValue("Candidate verified")
                loadAllCandidates()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }
}
