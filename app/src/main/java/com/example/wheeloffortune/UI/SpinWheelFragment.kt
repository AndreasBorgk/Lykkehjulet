package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wheeloffortune.databinding.SpinWheelLayoutBinding
import com.example.wheeloffortune.viewmodel.GameViewModel

class SpinWheelFragment : Fragment() {
        private var _binding: SpinWheelLayoutBinding? = null
        private val binding get()=_binding!!
        private val viewModel: GameViewModel by viewModels()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = SpinWheelLayoutBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.apply {

            }
            binding.spinWheelButton.setOnClickListener {
                viewModel.spinWheel().toString()

                // findNavController().navigate(R.id.action_guessWordFragment_to_wonGameFragment)
            }
        }
}