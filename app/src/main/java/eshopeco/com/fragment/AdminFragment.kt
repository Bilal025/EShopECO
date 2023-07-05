package eshopeco.com.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eshopeco.com.AddCategoryActivity
import eshopeco.com.AdminActivity
import eshopeco.com.databinding.FragmentAdminBinding


class AdminFragment : Fragment() {

    private lateinit var binding: FragmentAdminBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAdminBinding.inflate(layoutInflater)


        binding.addallorder.setOnClickListener {
            startActivity(Intent(requireContext(), AdminActivity::class.java))
        }
        binding.addcategory.setOnClickListener {
            startActivity(Intent(requireContext(), AddCategoryActivity::class.java))
        }



        return binding.root
    }

    }
