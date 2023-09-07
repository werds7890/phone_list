package com.example.phone_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_list.databinding.ItemListBinding
import com.example.phone_list.databinding.ItemListGridBinding
import com.example.phone_list.databinding.ItemListLeftBinding

class ContactListFragmentAdapter(val mItems: MutableList<ContactListFragmentData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun getItemViewType(position: Int): Int {
        return if (LayoutType.layoutType == 1){
            ItemViewType.GRID
        } else if (position % 2 == 0) {
            ItemViewType.REGULAR
        } else {
            ItemViewType.LEFT
        } // 그리드 뷰타입.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemViewType.REGULAR -> {
                Holder(
                    ItemListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ItemViewType.LEFT -> {
                LeftHolder(
                    ItemListLeftBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                GridViewHolder(
                    ItemListGridBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        if (LayoutType.layoutType == 1) {
            (holder as GridViewHolder)
            holder.bind(mItems[position])
        } else when (position % 2) {
            0 -> {
                (holder as Holder)
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
                    val likePulseAnimation =
                        AnimationUtils.loadAnimation(holder.itemView.context, R.anim.like_pulse)
                    holder.likeImageView.startAnimation(likePulseAnimation)
                }
            }

            1 -> {
                (holder as LeftHolder).bind(mItems[position])
                holder.likeImageView.setOnClickListener {
                    val clickedItem = mItems[position]
                    clickedItem.isLikeFilled = !clickedItem.isLikeFilled

                    if (clickedItem.isLikeFilled) {
                        holder.likeImageView.setImageResource(R.drawable.fill_heart_120)
                    } else {
                        holder.likeImageView.setImageResource(R.drawable.heart_120)
                    }

                    // 애니메이션 적용
                    val likePulseAnimation =
                        AnimationUtils.loadAnimation(holder.itemView.context, R.anim.like_pulse)
                    holder.likeImageView.startAnimation(likePulseAnimation)
                }
            }
        }


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.itemListImgIcon
        val name = binding.itemListTextName
        val likeImageView = binding.LikeImageView


    }

    inner class LeftHolder(binding: ItemListLeftBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.leftItemListImgIcon
        val name = binding.leftItemListTextName
        val likeImageView = binding.leftLikeImageView
        fun bind(item: ContactListFragmentData) {
            iconImageView.setImageResource(item.profileImage)
            name.text = item.aname
        }
    }

    inner class GridViewHolder(binding: ItemListGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.gridItemListImgIcon
        val name = binding.gridItemListTextName
        fun bind(item: ContactListFragmentData) {
            iconImageView.setImageResource(item.profileImage)
            name.text = item.aname
        }
    }


}