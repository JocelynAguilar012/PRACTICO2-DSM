package com.example.desafiopractico

import Producto
import ProductosAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductosAgregados : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos_agregados)

        recyclerView = findViewById(R.id.recyclerView)

        // Obtener referencia a la base de datos
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("productos")

        // Configurar oyente para escuchar cambios en los datos
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aquí se manejan los cambios en los datos
                val listaProductos = mutableListOf<Producto>()
                for (snapshot in dataSnapshot.children) {
                    val nombre = snapshot.child("nombre").getValue(String::class.java)
                    val precio = snapshot.child("precio").getValue(Double::class.java)
                    val imagenUrl = snapshot.child("imagenUrl").getValue(String::class.java)

                    if (nombre != null && precio != null && imagenUrl != null) {
                        val producto = Producto(nombre, precio, imagenUrl)
                        listaProductos.add(producto)
                    }
                }
                // Actualizar la interfaz de usuario con los datos obtenidos
                val adapter = ProductosAdapter(listaProductos)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@ProductosAgregados)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de lectura de la base de datos
                Log.d(
                    "ProductosActivity",
                    "Error al leer los datos de Firebase")
            }
        })
    }
}