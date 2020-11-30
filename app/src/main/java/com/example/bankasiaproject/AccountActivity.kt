package com.example.bankasiaproject

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankasiaproject.model.ApiResponse
import com.example.bankasiaproject.model.ApiService
import kotlinx.android.synthetic.main.activity_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<ApiResponse>()

    lateinit var recyclerView: RecyclerView

    private var myAdapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        recyclerView = findViewById(R.id.userList)
        myAdapter = UserListAdapter(dataList)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getDat1a()

        refreshlayout.setOnRefreshListener {
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            getDat1a()
            refreshlayout.isRefreshing = false
        }

    }

    private fun getDat1a() {
        var apiinstance = ApiService()
        val call: Call<List<ApiResponse>> = apiinstance.getUser()
        call.enqueue(object : Callback<List<ApiResponse>> {
            override fun onResponse(
                call: Call<List<ApiResponse>>?,
                response: Response<List<ApiResponse>>?
            ) {
                loadingView.visibility = View.GONE
                listError.visibility = View.GONE
                dataList.addAll(response!!.body()!!)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<ApiResponse>>?, t: Throwable?) {
                //progerssProgressDialog.dismiss()
                userList.visibility = View.GONE
                listError.visibility = View.GONE
            }
        })
    }


}
