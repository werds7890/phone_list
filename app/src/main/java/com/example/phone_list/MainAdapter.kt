package com.example.phone_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MainAdapter(val mContext: Context, val mItems: MutableList<Maindata>) : BaseAdapter() {

    override fun getCount(): Int {
        return mItems.size
    }


    override fun getItem(position: Int): Any {
        return mItems[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)

        val item : Maindata = mItems[position]


        val iconImageView = convertView?.findViewById<View>(R.id.item_list_img_icon) as ImageView
        val tv_name = convertView.findViewById<View>(R.id.item_list_text_name) as TextView



        iconImageView.setImageResource(item.profileImage)
        tv_name.text = item.aname


        return convertView
    }
}