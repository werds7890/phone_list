package com.example.phone_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].profileImage)
        holder.name.text = mItems[position].aname

        // LikeImageView에 대한 클릭 이벤트 처리
        holder.likeImageView.setOnClickListener {
            val clickedItem = mItems[position]
            clickedItem.isLikeFilled = !clickedItem.isLikeFilled

            if (clickedItem.isLikeFilled) {
                holder.likeImageView.setImageResource(R.drawable.fill_heart_120)
            } else {
                holder.likeImageView.setImageResource(R.drawable.heart_120)
            }

            // 애니메이션 적용
            val likePulseAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.like_pulse)
            holder.likeImageView.startAnimation(likePulseAnimation)
        }
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
        val likeImageView = binding.LikeImageView


    }


}