package com.example.desafiopractico

import Producto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ProductosAgregadosAdapter (private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductosAgregadosAdapter.ProductoRegistradoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoRegistradoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_products_register, parent, false)
        return ProductoRegistradoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoRegistradoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    inner class ProductoRegistradoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.item_title_register)
        private val imagenImageView: ImageView = itemView.findViewById(R.id.item_image_register)
        private val precioTextView: TextView = itemView.findViewById(R.id.item_Price_register)

        fun bind(producto: Producto) {
            nombreTextView.text = producto.nombre
            precioTextView.text = "$"+producto.precio.toString()
            // Cargar la imagen desde la URL utilizando Coil
            imagenImageView.load(producto.imagenUrl)
        }
    }
}