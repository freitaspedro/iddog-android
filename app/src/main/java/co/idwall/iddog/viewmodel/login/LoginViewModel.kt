package co.idwall.iddog.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.idwall.iddog.domain.LoginResponse
import co.idwall.iddog.repository.IddogRepository
import co.idwall.iddog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


const val GENERIC_ERROR = "Ops, ocorreu um erro!\nInsira outro email ou tente mais tarde."

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IddogRepository
): ViewModel() {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val user: LiveData<Resource<LoginResponse>>
        get() = _user


    fun login(email: String) {
        viewModelScope.launch {
            _user.postValue(Resource.Loading())
            val response = repository.login(email)
//            saveUser()
            _user.postValue(handleResponse(response))
        }
    }

    private inline fun <reified T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        val errorMessage = GENERIC_ERROR
        return Resource.Error(errorMessage)
    }
}