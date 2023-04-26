package com.vyapp.beguess.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.data.AppPreferences
import com.vyapp.beguess.databinding.FragmentUserBinding
import com.vyapp.beguess.domain.model.UserDomain
import java.util.*
import javax.inject.Inject

class UserFragment : Fragment() {

    private val appPreferences: AppPreferences by lazy {
        AppPreferences(requireActivity().applicationContext)
    }

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.userInject(this@UserFragment)
        binding.exit.setOnClickListener{

            appPreferences.leaveUser()
            viewModel.clearUser()

            findNavController().navigate(R.id.action_userFragment_to_sightInFragment)
        }

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
            with(binding) {
                usernameTv.text = it?.username?.uppercase(Locale.ROOT)
                scoreTv.text = "${it?.score}"
                placeTv.text = "${it?.let { it1 -> getUserIndex(it1) }}"
            }
        }
    }


    private fun getUserIndex(userDomain: UserDomain): Long {

        val users = viewModel.usersLiveData.value

        Log.d("asdasd",users.toString())

        var index: Long = 0

        if (users != null) {
            for (user in users) {
                if (user.username == userDomain.username) {
                    index = user.index
                }
            }
        }
        return index
    }
}

