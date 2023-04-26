package com.vyapp.beguess.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.databinding.FragmentMainBinding
import com.vyapp.beguess.databinding.FragmentSuccessBinding
import javax.inject.Inject


class SuccessFragment : Fragment() {

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentSuccessBinding by lazy {
        FragmentSuccessBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.successInject(this@SuccessFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.taskLiveData.observe(viewLifecycleOwner){
            binding.answerTxt.text = it.word.uppercase()
            binding.descTxt.text = it.description
        }
    }

}