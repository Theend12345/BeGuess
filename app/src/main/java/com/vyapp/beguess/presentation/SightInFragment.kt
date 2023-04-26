package com.vyapp.beguess.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.data.AppPreferences
import com.vyapp.beguess.databinding.FragmentSihgtInBinding
import com.vyapp.beguess.domain.model.UserRegisterDomain
import javax.inject.Inject

class SightInFragment : Fragment() {

    private val appPreferences: AppPreferences by lazy {
        AppPreferences(requireActivity().applicationContext)
    }

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentSihgtInBinding by lazy {
        FragmentSihgtInBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        binding.regBtn.setOnClickListener(this::sightInOnClick)
        binding.sightinTv.setOnClickListener(this::toRegisterFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.sightInInject(this@SightInFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            if(viewModel.userLiveData.value != null) {
                appPreferences.saveUser(it!!.username, binding.passwordEd.text.toString())
                findNavController().navigate(R.id.action_sightInFragment_to_mainFragment)
            }
        }

        viewModel.isSiginable.observe(viewLifecycleOwner){
            if (viewModel.isSiginable.value == false){
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.sign_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun sightInOnClick(view: View) {
        val username = binding.loginEd.text.toString().uppercase().trim()
        val password = binding.passwordEd.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            val userRegisterDomain = UserRegisterDomain(username, password)
            viewModel.sightIn(userRegisterDomain)


        } else {
            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.fields_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun toRegisterFragment(view: View) {
        findNavController().navigate(R.id.action_sightInFragment_to_registrationFragment)
    }
}