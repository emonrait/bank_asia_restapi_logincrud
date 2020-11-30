package com.example.bankasiaproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.text.DecimalFormat

class DashboardActivity : AppCompatActivity() {

    lateinit var format: DecimalFormat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.setTitle("")

        val name = getIntent().getStringExtra("name")
        val balance = getIntent().getStringExtra("balance")
        username.setText(name)

        val number = balance?.toDouble()
        format = DecimalFormat("#,##,###.00")
        val formatString = format.format(number)


        baln.setOnClickListener {
            baln.setText(formatString + " \u09F3")
        }

        transfer.setOnClickListener {
            startActivity(Intent(applicationContext, BankTransferActivity::class.java))

        }


        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(
                        applicationContext,
                        "navigation_home Menu",
                        Toast.LENGTH_SHORT
                    ).show()
                    //Log.i(TAG, "Home Selected")
                    //badgeClear(R.id.nav_home)
                }
                R.id.navigation_notifications -> {
                    Toast.makeText(
                        applicationContext,
                        "navigation_notifications Menu",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                R.id.navigation_account -> {
                    startActivity(Intent(applicationContext, AccountActivity::class.java))

                    Toast.makeText(
                        applicationContext,
                        "navigation_account Menu",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                R.id.navigation_settings -> {
                    Toast.makeText(
                        applicationContext,
                        "navigation_settings Menu",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            true
        }

    }


}




