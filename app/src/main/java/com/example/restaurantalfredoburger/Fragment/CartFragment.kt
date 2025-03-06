package com.example.restaurantalfredoburger.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantalfredoburger.R
import com.example.restaurantalfredoburger.adapter.CartAdapter
import com.example.restaurantalfredoburger.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        // Los precios base de cada producto (siempre se debe usar este formato para que el adapter pueda extraer correctamente el valor numérico)
        val cartFoodName = listOf("Hamburguesa Crispi", "Hamburguesa Ultra", "Tallarin Verde", "La Bestia")
        val cartItemPrice = listOf("S/.12.00", "S/.15.00", "S/.20.00", "S/.18.00")
        val cartImage = listOf(
            R.drawable.crispi_burger,
            R.drawable.ultra_burger,
            R.drawable.tallarin_verde,
            R.drawable.la_bestia,
        )

        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(cartItemPrice), ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {
    }
}