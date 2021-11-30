package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomTopic()
        binding.apply {
            topic.text = viewModel.topic.value.toString()
            word.text = viewModel.word.value.toString()


        }
        binding.guessButton.setOnClickListener {
            viewModel.guessWord(binding.takeInput.text.toString())

           // findNavController().navigate(R.id.action_guessWordFragment_to_wonGameFragment)
        }
    }
}