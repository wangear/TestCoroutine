package com.toy.loginwork.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toy.loginwork.Constants
import com.toy.loginwork.databinding.ItemUserBinding
import com.toy.loginwork.repository.data.PopularUserData
import com.toy.loginwork.ui.activity.DetailUserActivity

class PopUserListAdapter(private val items: List<PopularUserData>) :
    RecyclerView.Adapter<PopUserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        private var mItem : PopularUserData? = null

        init {
            itemView.setOnTouchListener { v, event ->
                binding.root.onTouchEvent(event)
                false
            }
            itemView.setOnClickListener {
                var intent = Intent(binding.root.context, DetailUserActivity::class.java)
                mItem?.let{
                    intent.putExtra(Constants.ID,it.id.toString())
                    binding.root.context.startActivity(intent)
                }

                true
            }
        }

        fun bind(item: PopularUserData) {
            mItem = item
            binding.tvUser.text = item.nickname
            binding.tvIntro.text = item.introduction

        }
    }
}