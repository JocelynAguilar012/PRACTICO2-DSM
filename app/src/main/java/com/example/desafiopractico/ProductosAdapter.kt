// ProductosAdapter.kt

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafiopractico.R
import com.google.firebase.database.FirebaseDatabase

class ProductosAdapter(private val productos: List<Producto>, private val productosTemp: MutableList<Producto>) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_products, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.item_title)
        private val precioTextView: TextView = itemView.findViewById(R.id.item_Price)
        private val imagenImageView: ImageView = itemView.findViewById(R.id.item_image)
        private val masButton: Button = itemView.findViewById(R.id.button)

        fun bind(producto: Producto) {
            nombreTextView.text = producto.nombre
            precioTextView.text = "$"+producto.precio.toString()
            // Cargar la imagen desde la URL utilizando Coil
            imagenImageView.load(producto.imagenUrl)
            // Obtener referencia a la base de datos
            //val database = FirebaseDatabase.getInstance()
            //val reference = database.getReference("productos")

            masButton.setOnClickListener {
                //AGREGAMOS EL PRODUCTO SELECCIONADO AL ARREGLO TEMPORAL
                productosTemp.add(producto)
                Toast.makeText(itemView.context, "Producto Agregado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
