package `in`.pdk.aravindhan.milkmanagement.ui.userdetails.model

data class MutableUserDetailsViews(
    var userName: String? = null,
    var address: String? = null,
    var villageName: String? = null,
    var pinCode: String? = null
)

data class UserDetailsErrorView(
    var userNameError: String? = null,
    var addressError: String? = null,
    var villageNameError: String? = null,
    var pinCOdeError:String? = null
)

data class UserDetailsView(
    var userName:String,
    var address:String,
    var villageName:String,
    var pinCode:String
)