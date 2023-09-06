package `in`.pdk.aravindhan.milkmanagement.ui.authentication.registration

import `in`.pdk.aravindhan.milkmanagement.ui.authentication.model.RegisterErrorView
import `in`.pdk.aravindhan.milkmanagement.ui.authentication.model.RegisterView
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val _phoneNumber = MutableLiveData(RegisterView())
    val phoneNumber: LiveData<RegisterView> = _phoneNumber

    private val _sendVerificationCode: MutableLiveData<String> = MutableLiveData()
    val sendVerificationCode: LiveData<String> = _sendVerificationCode


    private val _phoneNumberError  = MutableLiveData(RegisterErrorView())
    val phoneNumberError: LiveData<RegisterErrorView> = _phoneNumberError

    fun logIn() {
        val phoneNumber = _phoneNumber.value?.phoneNumber
        when {
            phoneNumber.isNullOrEmpty() -> {
                _phoneNumberError.value = RegisterErrorView("Phone number cannot be blank")
            }
            phoneNumber.length != 10 -> {
                _phoneNumberError.value = RegisterErrorView("Phone number must be 10 digit")
            }
            else -> {
          //      _phoneNumber.value = RegisterView("+91$phoneNumber")
                _sendVerificationCode.value = "+91$phoneNumber"
            }
        }
    }
}