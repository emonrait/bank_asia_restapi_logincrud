package com.example.bankasiaproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankasiaproject.model.ApiResponse
import com.example.bankasiaproject.model.getProgressDrawable
import com.example.bankasiaproject.model.loadImage
import kotlinx.android.synthetic.main.item_user.view.*


class UserListAdapter(val usersList: ArrayList<ApiResponse>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    fun updateUserList(newUserList: List<ApiResponse>) {
        usersList.clear()
        usersList.addAll(newUserList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.name.text = usersList[position].name
        holder.view.mobile.text = usersList[position].mobile
        holder.view.passworduser.text = usersList[position].password
        holder.view.iduser.text = usersList[position].id.toString()
        holder.view.edit.setOnClickListener {
            val context: Context = it.context
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", usersList[position].id.toString())
            intent.putExtra("name", usersList[position].name)
            intent.putExtra("mobile", usersList[position].mobile)
            intent.putExtra("password", usersList[position].password)
            intent.putExtra("imagelink", usersList[position].imagelink)
            context.startActivity(intent)

        }

        holder.view.delete.setOnClickListener {
            usersList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.view.imageView.loadImage(
            usersList[position].imagelink,
            getProgressDrawable(holder.view.imageView.context)
        )
    }

    override fun getItemCount() = usersList.size

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}