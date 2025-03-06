package com.example.restaurantalfredoburger.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantalfredoburger.databinding.CartItemBinding

class CartAdapter(
    private val CartItems: MutableList<String>,
    private val CartItemPrice: MutableList<String>,
    private val CartImage: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    // Array para almacenar las cantidades de cada item
    private val itemQuantities = IntArray(CartItems.size) { 1 }

    // Array para almacenar los precios base de cada item (sin el "S/.")
    private val basePrices = FloatArray(CartItems.size) { position ->
        // Extraer el valor numérico del precio, quitando "S/."
        CartItemPrice[position].replace("S/.", "").toFloat()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = CartItems.size

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = CartItems[position]

                // Actualizar el precio según la cantidad
                updatePriceForPosition(position)

                cartImage.setImageResource(CartImage[position])
                cartItemquantity.text = quantity.toString()

                minusbutton.setOnClickListener {
                    decreaseQuantity(position)
                }

                plusbutton.setOnClickListener {
                    increaseQuantity(position)
                }

                deletebutton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteItem(itemPosition)
                    }
                }
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemquantity.text = itemQuantities[position].toString()
                // Actualizar el precio cuando aumenta la cantidad
                updatePriceForPosition(position)
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.cartItemquantity.text = itemQuantities[position].toString()
                // Actualizar el precio cuando disminuye la cantidad
                updatePriceForPosition(position)
            }
        }

        // Método para actualizar el precio basado en la cantidad
        private fun updatePriceForPosition(position: Int) {
            val basePrice = basePrices[position]
            val quantity = itemQuantities[position]
            val totalPrice = basePrice * quantity

            // Actualizar el texto del precio con el nuevo cálculo
            binding.cartItemPrice.text = "S/.${String.format("%.2f", totalPrice)}"

            // También actualizar el array de precios para mantener la consistencia
            CartItemPrice[position] = "S/.${String.format("%.2f", totalPrice)}"
        }

        private fun deleteItem(position: Int) {
            CartItems.removeAt(position)
            CartItemPrice.removeAt(position)
            CartImage.removeAt(position)

            // También necesitamos actualizar nuestros arrays de cantidades y precios base
            val newItemQuantities = IntArray(CartItems.size)
            val newBasePrices = FloatArray(CartItems.size)

            for (i in 0 until position) {
                newItemQuantities[i] = itemQuantities[i]
                newBasePrices[i] = basePrices[i]
            }

            for (i in position until CartItems.size) {
                newItemQuantities[i] = itemQuantities[i + 1]
                newBasePrices[i] = basePrices[i + 1]
            }

            // Actualizar las referencias
            for (i in newItemQuantities.indices) {
                itemQuantities[i] = newItemQuantities[i]
                basePrices[i] = newBasePrices[i]
            }

            notifyItemRemoved(position)
            notifyItemRangeChanged(position, CartItems.size)
        }
    }
}