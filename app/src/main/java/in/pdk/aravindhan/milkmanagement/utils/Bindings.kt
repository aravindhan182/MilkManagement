package `in`.pdk.aravindhan.milkmanagement.utils

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("errorText")
fun EditText.setErrorText(errorMessage: String?) {
    error = errorMessage
}

@set:BindingAdapter("showIf")
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
