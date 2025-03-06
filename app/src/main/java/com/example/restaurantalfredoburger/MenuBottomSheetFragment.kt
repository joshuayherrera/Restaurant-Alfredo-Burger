package com.example.restaurantalfredoburger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantalfredoburger.adapter.MenuAdapter
import com.example.restaurantalfredoburger.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }

        val menuFoodName = listOf("Hamburguesa Crispi", "Hamburguesa Ultra", "Tallarin Verde", "La Bestia", "Sopa Wantan","Hamburguesa Ultra", "Tallarin Verde", "La Bestia", "Sopa Wantan")
        val menuItemPrice = listOf("S/.12", "S/.15", "S/.20", "S/.18", "S/.7", "S/.15", "S/.20", "S/.18", "S/.7")
        val menuImage = listOf(
            R.drawable.crispi_burger,
            R.drawable.ultra_burger,
            R.drawable.tallarin_verde,
            R.drawable.la_bestia,
            R.drawable.sopa_wantan,
            R.drawable.ultra_burger,
            R.drawable.tallarin_verde,
            R.drawable.la_bestia,
            R.drawable.sopa_wantan
        )
        val adapter = MenuAdapter(ArrayList(menuFoodName), ArrayList(menuItemPrice), ArrayList(menuImage))

        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {

    }
}