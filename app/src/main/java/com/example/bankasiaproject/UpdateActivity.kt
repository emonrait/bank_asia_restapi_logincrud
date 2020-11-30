package com.example.bankasiaproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import com.example.bankasiaproject.model.ApiResponse
import com.example.bankasiaproject.model.ApiService
import kotlinx.android.synthetic.main.activity_update.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            //updateUser()
            deleteUser()


        }


    }

    fun deleteUser() {
        var apiinstance = ApiService()
        var id = input_id.text.toString().trim()

        var call: Call<String> = apiinstance.userDelete(id.toInt())
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    //startActivity(Intent(applicationContext, AccountActivity::class.java))
                    var s = response.body().toString()
                    Toast.makeText(
                        applicationContext,
                        s,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        response.message(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })


    }

    fun updateUser() {
        var apiinstance = ApiService()
        var id = input_id.text.toString().trim()
        var name = input_name.text.toString().trim()
        var mobile = input_mobile.text.toString().trim()
        var password = input_password.text.toString().trim()
        var imagelink = input_imagelink.text.toString()
        var balance = "500.00"
        if (name.isEmpty()) {
            input_name.error = "Name Required"
            input_name.requestFocus()
        }
        if (mobile.isEmpty()) {
            input_mobile.error = "Mobile No Required"
            input_mobile.requestFocus()
        }
        if (password.isEmpty()) {
            input_password.error = "Password Required"
            input_password.requestFocus()
        } else {

            val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
            //put something inside the map, could be null

            jsonParams["id"] = id
            jsonParams["name"] = name
            jsonParams["mobile"] = mobile
            jsonParams["password"] = password
            jsonParams["imagelink"] = imagelink
            jsonParams["balance"] = balance

            val body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                JSONObject(jsonParams).toString()
            )

            var call: Call<ApiResponse> = apiinstance.updateUser(body)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        startActivity(Intent(applicationContext, AccountActivity::class.java))
                        var s = response.body().toString()
                        Toast.makeText(
                            applicationContext,
                            s,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Something Went Wrong" + response.errorBody().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            })

        }


    }
}