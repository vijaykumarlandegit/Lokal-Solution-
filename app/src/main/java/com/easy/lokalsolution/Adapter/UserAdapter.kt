package com.easy.lokalsolution.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easy.lokalsolution.Model.User
import com.easy.lokalsolution.R
import com.easy.lokalsolution.databinding.UserItemBinding

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        Log.d("UserAdapter", "Binding user: $user")
        holder.binding.userName.text = user.name
        holder.binding.userAge.text = "Age: ${user.age}"
       // Picasso.get().load(user.imageUrl).placeholder(R.drawable.empty).into(holder.userImage)
    }


    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding:UserItemBinding
        init {
            binding= UserItemBinding.bind(itemView)
        }
    }
}
