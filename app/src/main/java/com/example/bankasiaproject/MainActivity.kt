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
import com.example.bankasiaproject.model.ApiService
import com.example.bankasiaproject.model.UserResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navControler: NavController
    val drawerToggle by lazy {
        ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(drawerToggle)

        //drawerToggle.syncState()
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("")


        btn_login.setOnClickListener {
            loginUser()
            //startActivity(Intent(this, DashboardActivity::class.java))

        }
        link_register.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Toast.makeText(this, "Home Menu", Toast.LENGTH_SHORT).show()
                R.id.login -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Login Page", Toast.LENGTH_SHORT).show()
                }
                R.id.registration -> Toast.makeText(this, "registration Menu", Toast.LENGTH_SHORT)
                    .show()
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

    fun loginUser() {
        var apiinstance = ApiService()
        var mobile = input_userid.text.toString().trim()
        var password = input_password.text.toString().trim()

        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
        //put something inside the map, could be null

        jsonParams["mobile"] = mobile
        jsonParams["password"] = password

        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            JSONObject(jsonParams).toString()
        )

        if (mobile.isEmpty()) {
            input_userid.error = "Mobile No Required"
            input_userid.requestFocus()
        }
        if (password.isEmpty()) {
            input_password.error = "Password Required"
            input_password.requestFocus()
        } else {

            var call: Call<UserResponse> = apiinstance.login(body)
            call.enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {

                        val intent = Intent(
                            applicationContext,
                            DashboardActivity::class.java
                        ) //not application context
                        intent.putExtra("name", response.body()?.name.toString())
                        intent.putExtra("balance", response.body()?.balance.toString())
                        startActivity(intent)

                        var s = response.body().toString()
                        Toast.makeText(
                            applicationContext,
                            s,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Userid & Password is Wrong!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

            })

        }


    }
}


