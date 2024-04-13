package com.example.desafiopractico

import Producto
import ProductosAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductosAdapter
    //VARIABLE QUE CONTENDRA EL ARREGLO DE LOS PRODUCTOS AGREGADOS TEMPORALMENTE
    val listaProductosTemp: MutableList<Producto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener la lista de productos desde tu fuente de datos (por ejemplo, una base de datos o una API)
        val listaProductos = obtenerListaDeProductos()

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crear y configurar el adaptador
        adapter = ProductosAdapter(listaProductos, listaProductosTemp)
        recyclerView.adapter = adapter
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val fabShopping: FloatingActionButton = findViewById(R.id.second_fab)

        fab.setOnClickListener {
            val intent = Intent(this, ProductosAgregados::class.java)
            startActivity(intent)
        }

        fabShopping.setOnClickListener {
            TemporalProductsHolder.listaProductosTemp = listaProductosTemp
            val intent = Intent(this, ProductosAgregadosTemp::class.java)
            startActivity(intent)
        }
    }

    // Método ficticio para obtener la lista de productos desde una fuente de datos
    private fun obtenerListaDeProductos(): List<Producto> {
        return listOf(
            Producto("Acetaminofen", 10.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmOUTrtklCwxEz-QauNSDUi5fXa7gGuRgHYjd_RV-C4w&s"),
            Producto("Palagrip", 15.0, "https://farmaciafurtuna7.com/cdn/shop/products/PALAGRIP.jpg?v=1660280674"),
            Producto("Dolofin", 15.0, "https://walmartsv.vtexassets.com/arquivos/ids/293309/Sv-Dolofin-Ra-20-Tab-1-8445.jpg?v=638097180822100000"),
            Producto("Tramadol", 15.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKj_ISG9beIXjSwgdNp9Ez6EQGAHFkq7KoH41cGx1eaw&s"),
            Producto("Vick", 15.0, "https://walmartsv.vtexassets.com/arquivos/ids/326385/Vick-Vaporub-170-gr-1-36259.jpg?v=638270302257300000"),
            Producto("Ibuprofeno", 15.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6bNzrI3-bJ_0qHn-iohxygc4CcvcMim_J7WPCan-b6Q&s"),
            Producto("NerviFLora", 15.0, "https://siman.vtexassets.com/arquivos/ids/4816265/image-cf63ca17e81f4d24bfec3873f8af3153.jpg?v=638332666984300000"),
            Producto("Foskrol", 15.0, "https://biokemical.com.sv/Biokemical/media/biokemical/Vademecum/Foskrol-con-Vitaminas-Desestresante-solucion-oral-Cx10-VB-Preforma-ES-HN.jpg"),
            Producto("Curitas", 15.0, "https://walmartsv.vtexassets.com/arquivos/ids/172934/Curita-Impermeable-Hansaplast-20-Unidades-1-11115.jpg?v=637641693269970000"),
            Producto("Nazil", 15.0, "https://m.media-amazon.com/images/I/61sN-5lUK9S.jpg"),
            // Agrega más productos según sea necesario
        )
    }
}