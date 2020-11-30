package com.example.bankasiaproject

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import androidx.navigation.NavController
import com.example.bankasiaproject.model.ApiResponse
import com.example.bankasiaproject.model.ApiService
import kotlinx.android.synthetic.main.activity_main.drawerlayout
import kotlinx.android.synthetic.main.activity_main.input_password
import kotlinx.android.synthetic.main.activity_main.navView
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_registration.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navControler: NavController
    val drawerToggle by lazy {
        ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setSupportActionBar(toolbar)
        drawerlayout.addDrawerListener(drawerToggle)
        supportActionBar?.setTitle("")


        btn_register.setOnClickListener {
            saveUser()

        }
        link_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Toast.makeText(this, "Home Menu", Toast.LENGTH_SHORT).show()
                R.id.login -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Login Page", Toast.LENGTH_SHORT).show()
                }
                R.id.registration -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    Toast.makeText(this, "Login Page", Toast.LENGTH_SHORT).show()
                }
                R.id.notifications -> Toast.makeText(this, "notifications Menu", Toast.LENGTH_SHORT)
                    .show()
                R.id.aboutus -> Toast.makeText(this, "aboutus Menu", Toast.LENGTH_SHORT).show()
                R.id.atmLocations -> Toast.makeText(this, "atmLocations Menu", Toast.LENGTH_SHORT)
                    .show()
                R.id.terms -> Toast.makeText(this, "terms Menu", Toast.LENGTH_SHORT).show()
                R.id.logout -> Toast.makeText(this, "logout Menu", Toast.LENGTH_SHORT).show()

            }
            true
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    /*override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        drawerToggle.syncState()
    }*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.right_side_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.menuBtn) {
            if (drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerlayout.closeDrawer(Gravity.RIGHT)
            } else {
                drawerlayout.openDrawer(Gravity.RIGHT)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveUser() {
        var apiinstance = ApiService()
        var name = input_name.text.toString().trim()
        var mobile = input_mobile.text.toString().trim()
        var password = input_password.text.toString().trim()
        var imagelink =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Bank_Asia_Limited.svg/1200px-Bank_Asia_Limited.svg.png"
        var balance="500.00"
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

            jsonParams["name"] = name
            jsonParams["mobile"] = mobile
            jsonParams["password"] = password
            jsonParams["imagelink"] = imagelink
            jsonParams["balance"] = balance

            val body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                JSONObject(jsonParams).toString()
            )

            var call: Call<ApiResponse> = apiinstance.createUser(body)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        var s = response.body().toString()
                        Toast.makeText(
                            applicationContext,
                            s,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "This user is Already Exist",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            })

        }


    }
}