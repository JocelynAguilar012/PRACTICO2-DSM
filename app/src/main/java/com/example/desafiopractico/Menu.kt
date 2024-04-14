package com.example.desafiopractico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

class Menu : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        //Declaramos Variables
        val buttonRestaurante = findViewById<Button>(R.id.btn1)
        val buttonSalon = findViewById<Button>(R.id.btn2)
        val buttonFarmacia = findViewById<Button>(R.id.btn3)

        buttonFarmacia.setOnClickListener {
            //Cuando se hace click sobre el boton ejercicio 3
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}