package com.example.restaurantalfredoburger.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantalfredoburger.R
import com.example.restaurantalfredoburger.adapter.MenuAdapter
import com.example.restaurantalfredoburger.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuFoodName = listOf("Hamburguesa Crispi", "Hamburguesa Ultra", "Tallarin Verde", "La Bestia" , "Sopa Wantan")
    private val originalMenuPrice = listOf("S/.12", "S/.15", "S/.20", "S/.18", "S/.10")
    private val originalMenuImage = listOf(
        R.drawable.crispi_burger,
        R.drawable.ultra_burger,
        R.drawable.tallarin_verde,
        R.drawable.la_bestia,
        R.drawable.sopa_wantan
    )

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = MenuAdapter(filteredMenuFoodName, filteredMenuPrice, filteredMenuImage)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        // Setup para search view
        setupSearchView()

        // Mostrar todos los elementos
        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {
        filteredMenuFoodName.clear()
        filteredMenuPrice.clear()
        filteredMenuImage.clear()

        // Añadir elementos uno por uno
        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuPrice.addAll(originalMenuPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItems(newText ?: "")
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuPrice.clear()
        filteredMenuImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase = true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuPrice.add(originalMenuPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }
}