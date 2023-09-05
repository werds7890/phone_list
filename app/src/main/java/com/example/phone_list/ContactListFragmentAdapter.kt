package com.example.phone_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_list.databinding.ItemListBinding

class ContactListFragmentAdapter(val mItems: MutableList<ContactListFragmentData>) : RecyclerView.Adapter<ContactListFragmentAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemListBinding   .inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].profileImage)
        holder.name.text = mItems[position].aname

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.itemListImgIcon
        val name = binding.itemListTextName


    }
}