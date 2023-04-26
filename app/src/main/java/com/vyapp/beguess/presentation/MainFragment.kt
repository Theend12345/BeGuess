package com.vyapp.beguess.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.ads.AdsController
import com.vyapp.beguess.data.AppPreferences
import com.vyapp.beguess.databinding.FragmentMainBinding
import com.yandex.mobile.ads.interstitial.InterstitialAd
import javax.inject.Inject
import kotlin.math.abs


class MainFragment : Fragment() {

    private var interstitialAd: InterstitialAd? = null

    private val appPreferences: AppPreferences by lazy {
        AppPreferences(requireActivity().applicationContext)
    }

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        binding.checkBtn.setOnClickListener(this::checkAnswer)
        binding.infoBtn.setOnClickListener(this::showInfoDialogOnClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        interstitialAd = InterstitialAd(requireActivity().applicationContext)

        AdsController.initialize(requireContext().applicationContext)


        (activity?.applicationContext as App).appComponent.mainInject(this@MainFragment)

        viewModel.getUsers()

        if (appPreferences.getUser().username.isEmpty()) {
            findNavController().navigate(R.id.action_mainFragment_to_sightInFragment)
        } else {
            viewModel.sightIn(appPreferences.getUser())
        }

        binding.listBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_usersListFragment)
        }

        binding.userdataBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_userFragment)
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

        AdsController.configureInterstitialAd(interstitialAd)
        AdsController.loadInterstitialAd(interstitialAd)


        viewModel.taskLiveData.observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it.res).into(binding.image)
        }

        viewModel.progress.observe(viewLifecycleOwner){

            if (viewModel.progress.value == false){
                binding.progress.visibility = View.GONE
            }else{
                binding.progress.visibility = View.VISIBLE
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        interstitialAd?.destroy()
        interstitialAd = null

    }


    private fun checkAnswer(view: View) {


        val guess = binding.wordEt.text.toString().lowercase().trim()

        if(guess.isNotEmpty()){
            val answer = viewModel.taskLiveData.value?.word?.lowercase()?.trim()

            if (answer == guess) {

                viewModel.userLiveData.observe(viewLifecycleOwner) {
                    if (it?.status == false) {
                        viewModel.userLiveData.value?.let {
                            viewModel.rightAnswer(it)
                        }
                    } else {
                        findNavController().navigate(R.id.action_mainFragment_to_successFragment)
                    }
                }

            } else {
                answer?.let { similarityPercentage(it, guess) }
                    ?.let { stringFormat(it) }?.let { showPercentDialog(it) }
            }
        }else{
            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.field_error),
                Toast.LENGTH_LONG
            ).show()
        }



    }

    private fun similarityPercentage(answer: String, guess: String): Double {

        var contain = 0
        var firstPart = 0.0
        var secondPart = 0.0

        for (letter in guess.toSet()) {
            if (answer.contains(letter)) {
                contain++
            }
        }

        firstPart = (contain * 100 / answer.length).toDouble()

        if (abs(answer.length - guess.length) != 0) {

            secondPart = (100 / abs(answer.length - guess.length)).toDouble() - 5

        } else {
            secondPart = 97.0
        }

        return (firstPart + secondPart) / 2
    }

    private fun stringFormat(percent: Double): String {
        val text: String = requireContext().resources.getString(R.string.answer_error)
        return "$text ${percent}%"
    }

    private fun showPercentDialog(message: String) {

        AlertDialog.Builder(requireActivity()).setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }.create().show()

    }

    private fun showInfoDialogOnClick(view: View){

        val message = requireContext().resources.getString(R.string.info)

        showPercentDialog(message)
    }

}