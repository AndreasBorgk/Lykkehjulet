package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.wheeloffortune.R
import com.example.wheeloffortune.databinding.GuessWordLayoutBinding
import com.example.wheeloffortune.viewmodel.GameViewModel


class GuessWordFragment  : Fragment(){
    private var _binding: GuessWordLayoutBinding? = null
    private val binding get()=_binding!!
    private val viewModel: GameViewModel by viewModels()
    


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GuessWordLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun updateHealthAndPoints() {
        binding.health.text = viewModel.health.value.toString()
        binding.points.text = viewModel.points.value.toString()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomTopic()
        viewModel.showHealthAndPoints()

        binding.apply {
            topic.text = viewModel.topic.value.toString()
            word.text = viewModel.word.value.toString()
            health.text = viewModel.health.value.toString()
            points.text = viewModel.points.value.toString()




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
            updateHealthAndPoints()



        }
        binding.guessButton.setOnClickListener {
            viewModel.guessWord(input = binding.takeInput.text.toString())
            binding.spinWheel.isEnabled = true
            binding.guessButton.isEnabled = false

            updateHealthAndPoints()
        }
    }
}