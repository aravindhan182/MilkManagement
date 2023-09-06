package `in`.pdk.aravindhan.milkmanagement.ui.userdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import `in`.pdk.aravindhan.milkmanagement.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserDetailsViewModel by viewModels()
    private val fAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.userDetailsView.observe(viewLifecycleOwner) {
            val db = Firebase.firestore
            val userId = fAuth.currentUser!!.uid
            val userDetails = hashMapOf(
                "userName" to it.userName,
                "address" to it.address,
                "villageName" to it.villageName,
                "pinCode" to it.pinCode.toInt()
            )
            db.collection("userDetails").document(userId).set(userDetails)
                .addOnSuccessListener {
                    val nav = UserDetailsFragmentDirections.userDetailsToMilkReading()
                    findNavController().navigate(nav)
                }
                .addOnFailureListener { e ->
                    Snackbar.make(
                        requireView(),
                        "User details not stored.Something went wrong",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        }
    }
}