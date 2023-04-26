package com.vyapp.beguess.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.data.AppPreferences
import com.vyapp.beguess.databinding.FragmentRegistrationBinding
import com.vyapp.beguess.domain.model.UserRegisterDomain
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private val appPreferences: AppPreferences by lazy {
        AppPreferences(requireActivity().applicationContext)
    }

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentRegistrationBinding by lazy {
        FragmentRegistrationBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        binding.regBtn.setOnClickListener(this::registerOnClick)
        binding.sightinTv.setOnClickListener(this::toSiginFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.registerInject(this@RegistrationFragment)
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
            if (viewModel.userLiveData.value != null) {
                appPreferences.saveUser(it!!.username, binding.passwordEd.text.toString())
                findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
            }
        }

        viewModel.isRgistrable.observe(viewLifecycleOwner){
            if (viewModel.isRgistrable.value == false){
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.reg_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun registerOnClick(view: View) {
        val username = binding.loginEd.text.toString().uppercase().trim()
        val password = binding.passwordEd.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            val userRegisterDomain = UserRegisterDomain(username, password)
            viewModel.registration(userRegisterDomain)

        } else {
            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.fields_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun toSiginFragment(view: View) {
        findNavController().navigate(R.id.action_registrationFragment_to_sightInFragment)
    }


}