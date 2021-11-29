package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wheeloffortune.R
import com.example.wheeloffortune.databinding.StartGameLayoutBinding

class StartGameFragment : Fragment(){
   private var _binding: StartGameLayoutBinding? = null
   private val binding get()=_binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = StartGameLayoutBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.startGameButton.setOnClickListener {
         findNavController().navigate(R.id.action_startGameFragment_to_guessWordFragment)
      }
   }
}