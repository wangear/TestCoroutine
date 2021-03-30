package com.toy.loginwork.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.toy.loginwork.Constants
import com.toy.loginwork.databinding.ItemCardBinding
import com.toy.loginwork.repository.data.PopularCardData
import com.toy.loginwork.ui.activity.DetailCardActivity

class PopCardListAdapter(private var items: List<PopularCardData>) :
    RecyclerView.Adapter<PopCardListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(updatedItems : List<PopularCardData>){
        items = updatedItems
    }

    class ViewHolder(private var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var mItem : PopularCardData? = null
        init {
            itemView.setOnTouchListener { v, event ->
                binding.ivItemPicture.onTouchEvent(event)
                false
            }
            itemView.setOnClickListener {
                var intent = Intent(binding.root.context, DetailCardActivity::class.java)
                intent.putExtra(Constants.ID,mItem?.id.toString())
                binding.root.context.startActivity(intent)
                true
            }
        }

        fun bind(item: PopularCardData) {
            mItem = item
            Glide.with(binding.ivItemPicture).load(item.imgUrl).into(binding.ivItemPicture)
        }
    }
}