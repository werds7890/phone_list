package com.example.phone_list

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactListFragmentData(
    var profileImage: Int,
    var aname: String,
    var userPhoneNum : String,
    var userEmail : String,
    var userIsLiked : Boolean,
    var isLikeFilled: Boolean = false,
    var modifyImg : Uri?=null
):Parcelable