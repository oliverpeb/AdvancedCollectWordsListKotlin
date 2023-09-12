package com.example.advancedcollectwordslistkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.advancedcollectwordslistkotlin.databinding.FragmentFirstBinding
import android.widget.Toast
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val words = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveWord.setOnClickListener {
            val word = binding.editTextName.text.trim().toString()
            if (word == "") {
                binding.editTextName.error = "No name"
                return@setOnClickListener
            }
            if (words.contains(word)) {
                Toast.makeText(
                    requireContext(),
                    "$word already exists in the list!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            words.add(word)
            Toast.makeText(requireContext(), "$word has been added to the list", Toast.LENGTH_LONG)
                .show()
            binding.editTextName.setText("")
        }
        binding.buttonClearWords.setOnClickListener {
            if (words.isEmpty()) {
                Toast.makeText(requireContext(), "List is empty!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            words.clear()
            binding.textViewMessage.text = "$words"
            Toast.makeText(requireContext(), "List has been cleared!", Toast.LENGTH_LONG).show()
        }

        binding.buttonShowWords.setOnClickListener {
            // Convert the ArrayList to an Array before passing
            val wordsArray = words.toTypedArray()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(wordsArray)
            findNavController().navigate(action)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}