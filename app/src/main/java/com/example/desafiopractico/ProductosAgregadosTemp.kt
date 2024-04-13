package com.example.desafiopractico

import Producto
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

lateinit var enviar:Button
class ProductosAgregadosTemp : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productos_agregados_temp)
        // Recuperar la lista de productos temporales
        val listaProductosTemp: MutableList<Producto> = TemporalProductsHolder.listaProductosTemp
        recyclerView = findViewById(R.id.recyclerViewTemp)

        // Actualizar la interfaz de usuario con los datos obtenidos
        val adapter = ProductosAgregadosAdapter(listaProductosTemp)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@ProductosAgregadosTemp)

        var total = 0.0
        for (producto in listaProductosTemp) {
            total += producto.precio
        }

        val textViewTotal: TextView = findViewById(R.id.textViewTotal)
        textViewTotal.text = "Total: $total"

        enviar=findViewById(R.id.botonTemp)
        enviar.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("productos")
            for (producto in listaProductosTemp) {

                // Generar una clave única para el producto
                val productoId = reference.push().key

                // Guardar el producto en la base de datos usando la clave única generada
                if (productoId != null) {
                    reference.child(productoId).setValue(producto)
                        .addOnSuccessListener {
                            // El producto se guardó exitosamente
                            Toast.makeText(this@ProductosAgregadosTemp, "Productos Guardados Con Exito", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            // Ocurrió un error al intentar guardar el producto
                            Toast.makeText(this@ProductosAgregadosTemp, "Ocurrio Un Error", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }

    }
}