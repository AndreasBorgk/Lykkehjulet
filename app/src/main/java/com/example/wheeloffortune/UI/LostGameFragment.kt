package com.example.wheeloffortune.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wheeloffortune.R
import com.example.wheeloffortune.databinding.LostGameLayoutBinding

class LostGameFragment : Fragment(){
    private var _binding: LostGameLayoutBinding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LostGameLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBackToStart.setOnClickListener {
            findNavController().navigate(R.id.action_lostGameFragment_to_startGameFragment)
        }
    }
}