package com.example.guessgameapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.guessgameapp.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        binding.tvEndScore.text = arguments?.getString("END_SCORE")
        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)
        }
        return binding.root
    }
}