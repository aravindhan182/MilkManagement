package `in`.pdk.aravindhan.milkmanagement.ui.authentication.opt

import `in`.pdk.aravindhan.milkmanagement.databinding.FragmentOtpBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
    private val args: OtpFragmentArgs by navArgs()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        subscribeUi()
    }

    private fun subscribeUi() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
        binding.submitBtn.setOnClickListener {
            binding.isLoading = true
            when {
                binding.pinview.value.isNullOrEmpty() -> {
                    Snackbar.make(
                        requireView(),
                        "Please enter the OTP",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                binding.pinview.value.length != 6 -> {
                    Snackbar.make(
                        requireView(),
                        "Otp length shouldn't be less than 6",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        args.storedVerificationId, binding.pinview.value
                    )
                    Log.d("cccc", args.storedVerificationId)
                    signInWithPhoneAuthCredential(credential)
                }
            }
            /*     binding.pinview.setPinViewEventListener(object : Pinview.PinViewEventListener {
                     override fun onDataEntered(pinview: Pinview?, fromUser: Boolean) {
                         Log.d("ccccc","${pinview?.value}")
                         when {
                             pinview?.value.isNullOrEmpty() -> {
                                 Snackbar.make(
                                     requireView(),
                                     "Please enter the OTP",
                                     Snackbar.LENGTH_SHORT
                                 ).show()
                             }
                             pinview?.value?.length != 6 -> {
                                 Snackbar.make(
                                     requireView(),
                                     "Otp length shouldn't be less than 6",
                                     Snackbar.LENGTH_SHORT
                                 ).show()
                             }
                             else -> {
                                 val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                                     args.storedVerificationId, pinview.value
                                 )
                                 Log.d("cccc", args.storedVerificationId)
                                 signInWithPhoneAuthCredential(credential)
                             }
                         }


                     }

                 })*/
        }


    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.isLoading = false
                    val action = OtpFragmentDirections.actionOtpFragmentToUserDetailsFragment()
                    findNavController().navigate(action)
                } else {
                    Snackbar.make(requireView(), "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
    }
}