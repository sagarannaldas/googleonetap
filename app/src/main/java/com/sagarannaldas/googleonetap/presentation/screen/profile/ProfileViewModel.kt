package com.sagarannaldas.googleonetap.presentation.screen.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagarannaldas.googleonetap.domain.model.ApiRequest
import com.sagarannaldas.googleonetap.domain.model.ApiResponse
import com.sagarannaldas.googleonetap.domain.model.MessageBarState
import com.sagarannaldas.googleonetap.domain.model.User
import com.sagarannaldas.googleonetap.domain.model.UserUpdate
import com.sagarannaldas.googleonetap.domain.repository.Repository
import com.sagarannaldas.googleonetap.util.Constants.MAX_LENGTH
import com.sagarannaldas.googleonetap.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _user: MutableState<User?> = mutableStateOf(null)
    val user: State<User?> = _user

    private var _firstName: MutableState<String> = mutableStateOf("")
    val firstName: State<String> = _firstName

    private var _lastName: MutableState<String> = mutableStateOf("")
    val lastname: State<String> = _lastName

    private var _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val apiResponse: State<RequestState<ApiResponse>> = _apiResponse

    private var _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    private var _clearSessionResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val clearSessionResponse: State<RequestState<ApiResponse>> = _clearSessionResponse

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _apiResponse.value = RequestState.Loading
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getUserInfo()
                }
                _apiResponse.value = RequestState.Success(response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
                if (response.user != null) {
                    _user.value = response.user
                    _firstName.value = response.user.name.split(" ").first()
                    _lastName.value = response.user.name.split(" ").last()
                }
            } catch (e: Exception) {
                _apiResponse.value = RequestState.Error(e)
                _messageBarState.value = MessageBarState(error = e)
            }
        }
    }

    fun updateUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _apiResponse.value = RequestState.Loading
            try {
                if (user.value != null) {
                    val response = repository.getUserInfo()
                    verifyAndUpdate(currentUser = response)
                }
            } catch (e: Exception) {
                _apiResponse.value = RequestState.Error(e)
                _messageBarState.value = MessageBarState(error = e)
            }
        }
    }

    private fun verifyAndUpdate(currentUser: ApiResponse) {
        val (verified, exception) = if (firstName.value.isEmpty() || lastname.value.isEmpty()) {
            Pair(false, EmptyFieldException())
        } else {
            if (currentUser.user?.name?.split(" ")?.first() == firstName.value &&
                currentUser.user.name.split(" ").last() == lastname.value
            ) {
                Pair(false, NothingToUpdateException())
            } else {
                Pair(true, null)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            if (verified) {
                try {
                    val response = repository.updateUserInfo(
                        userUpdate = UserUpdate(
                            firstName = firstName.value,
                            lastName = lastname.value
                        )
                    )
                    _apiResponse.value = RequestState.Success(response)
                    _messageBarState.value = MessageBarState(
                        message = response.message,
                        error = response.error
                    )
                } catch (e: Exception) {
                    _apiResponse.value = RequestState.Error(e)
                    _messageBarState.value = MessageBarState(error = e)
                }
            } else {
                _apiResponse.value = RequestState.Success(
                    ApiResponse(
                        success = false,
                        error = exception
                    )
                )
                _messageBarState.value = MessageBarState(error = exception)
            }
        }
    }

    fun updateFirstName(newName: String) {
        if (newName.length < MAX_LENGTH) {
            _firstName.value = newName
        }
    }

    fun updateLastName(newName: String) {
        if (newName.length < MAX_LENGTH) {
            _lastName.value = newName
        }
    }

    fun clearSession() {
        _clearSessionResponse.value = RequestState.Loading
        _apiResponse.value = RequestState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.clearSession()
                _clearSessionResponse.value = RequestState.Success(response)
                _apiResponse.value = RequestState.Success(response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            } catch (e: Exception) {
                _clearSessionResponse.value = RequestState.Error(e)
                _apiResponse.value = RequestState.Error(e)
                _messageBarState.value = MessageBarState(error = e)
            }
        }
    }

    fun deleteUser() {
        _clearSessionResponse.value = RequestState.Loading
        _apiResponse.value = RequestState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = repository.deleteUser()
                _clearSessionResponse.value = RequestState.Success(response)
                _apiResponse.value = RequestState.Success(response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            } catch (e: Exception) {
                _clearSessionResponse.value = RequestState.Error(e)
                _apiResponse.value = RequestState.Error(e)
                _messageBarState.value = MessageBarState(error = e)
            }
        }
    }

    fun verifyTokenOnBackend(request: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.verifyTokenBackend(request = request)
                _apiResponse.value = RequestState.Success(response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            }
        } catch (e: Exception) {
            _apiResponse.value = RequestState.Error(e)
            _messageBarState.value = MessageBarState(error = e)
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveSignedInState(signedIn = signedIn)
        }
    }
}

class EmptyFieldException(
    override val message: String = "Empty Input Field"
) : Exception()

class NothingToUpdateException(
    override val message: String = "Nothing to Update"
) : Exception()
