package com.example.bankasiaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val mobile = intent.getStringExtra("mobile")
        val password = intent.getStringExtra("password")
        val imagelink = intent.getStringExtra("imagelink")
        //val balance = intent.getStringExtra("balance")

        input_id.text = id
        input_name.setText(name)
        input_mobile.setText(mobile)
        input_password.setText(password)
        input_imagelink.setText(imagelink)

        btn_update.setOnClickListener {

        }

    }
}