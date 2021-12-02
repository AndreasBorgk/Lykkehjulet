package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheeloffortune.R
import com.example.wheeloffortune.databinding.GuessWordLayoutBinding
import com.example.wheeloffortune.viewmodel.GameViewModel


class GuessWordFragment  : Fragment(){
    private var _binding: GuessWordLayoutBinding? = null
    private val binding get()=_binding!!
    private val viewModel: GameViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: com.example.wheeloffortune.Adapter.Adapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GuessWordLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun update() {
        binding.health.text = "du har " + viewModel.health.value.toString() + " liv"
        binding.points.text = "du har " + viewModel.points.value.toString() + " points"
        binding.testWord.text = viewModel.generateUnderScores()
        showRecyclerView()


    }

    fun gameDone() {
        if(viewModel.health.value == 0) {
            findNavController().navigate(R.id.action_guessWordFragment_to_lostGameFragment)
        }
            else {

        }
    }

    fun gameWon() {
        if(viewModel.isWinner == true) {
            findNavController().navigate(R.id.action_guessWordFragment_to_wonGameFragment)
        }
    }

    fun showRecyclerView() {
        adapter = com.example.wheeloffortune.Adapter.Adapter(requireContext(), viewModel.guessedInputList)
        binding.recyclerView.adapter = adapter
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomTopic()
        viewModel.showHealthAndPoints()
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = com.example.wheeloffortune.Adapter.Adapter(requireContext(), viewModel.guessedInputList)
        binding.recyclerView.adapter = adapter



        binding.apply {
            topic.text = viewModel.topic.value.toString()
            health.text = viewModel.health.value.toString()
            points.text = viewModel.points.value.toString()
            binding.guessButton.isEnabled = false
            testWord.text = viewModel.generateUnderScores()






        }
        binding.spinWheel.setOnClickListener {
            var resultat = viewModel.spinWheel()
            if(resultat){
                binding.spinWheel.isEnabled = true
                binding.guessButton.isEnabled = false
            } else {
                binding.guessButton.isEnabled = true
                binding.spinWheel.isEnabled = false
            }
            update()



        }
        binding.guessButton.setOnClickListener {

            if(viewModel.inputOK(binding.takeInput.text.toString())) {
                viewModel.guessWord(input = binding.takeInput.text.toString())
                binding.spinWheel.isEnabled = true
                binding.guessButton.isEnabled = false


                update()
                gameDone()
                gameWon()
            }
        }
    }
}