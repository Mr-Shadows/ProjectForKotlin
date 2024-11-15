package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val order001: TextView = findViewById(R.id.order001)
        order001.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java)
            startActivity(intent)
        }

        val order002: TextView = findViewById(R.id.order002)
        order002.setOnClickListener {
            val intent = Intent(this, OrderDetailActivityMir::class.java)
            startActivity(intent)
        }

        val backToMainButton: Button = findViewById(R.id.backToMainFromOrdersButton)
        backToMainButton.setOnClickListener {
            finish()
        }
    }
}
