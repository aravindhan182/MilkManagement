package `in`.pdk.aravindhan.milkmanagement.ui.userdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import `in`.pdk.aravindhan.milkmanagement.ui.userdetails.model.MutableUserDetailsViews
import `in`.pdk.aravindhan.milkmanagement.ui.userdetails.model.UserDetailsErrorView
import `in`.pdk.aravindhan.milkmanagement.ui.userdetails.model.UserDetailsView

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val _mutableUserDetailsView = MutableLiveData(MutableUserDetailsViews())
    val mutableUserDetailsViews: LiveData<MutableUserDetailsViews> = _mutableUserDetailsView

    private val _userDetailsErrorView = MutableLiveData(UserDetailsErrorView())
    val userDetailsErrorView: LiveData<UserDetailsErrorView> = _userDetailsErrorView

    private val _userDetailsView: MutableLiveData<UserDetailsView> = MutableLiveData()
    val userDetailsView: LiveData<UserDetailsView> = _userDetailsView

    fun saveUserDetails() {
        val userDetails = _mutableUserDetailsView.value
        when {
            userDetails?.userName.isNullOrEmpty() -> {
                _userDetailsErrorView.value =
                    UserDetailsErrorView(userNameError = "user name cannot be empty")
            }

            userDetails?.address.isNullOrEmpty() -> {
                _userDetailsErrorView.value =
                    UserDetailsErrorView(addressError = "address cannot be empty")
            }

            userDetails?.pinCode.isNullOrEmpty() -> {
                _userDetailsErrorView.value =
                    UserDetailsErrorView(pinCOdeError = "pincode cannot be empty")
            }

            userDetails?.villageName.isNullOrEmpty() -> {
                _userDetailsErrorView.value =
                    UserDetailsErrorView(villageNameError = "village name cannot be empty")
            }

            else -> {
                val userName = userDetails?.userName!!
                val address = userDetails.address!!
                val villageName = userDetails.villageName!!
                val pincode = userDetails.pinCode!!
                _userDetailsView.value = UserDetailsView(
                    userName = userName,
                    address = address,
                    villageName = villageName,
                    pinCode = pincode
                )
            }
        }
    }

}