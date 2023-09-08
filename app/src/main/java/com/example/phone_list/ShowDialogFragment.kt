package com.example.phone_list

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.example.phone_list.databinding.AddDialogBinding

class ShowDialogFragment : DialogFragment() {
    private var _binding: AddDialogBinding? = null
    private var loadedImageUri : Uri?=null
    private val binding get() = _binding!!

    interface DialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private lateinit var listener: DialogListener

    private lateinit var imageView: ImageView
    private fun loadImage(uri:Uri) {
        Glide.with(this)
            .load(uri)
            .into(imageView)
        loadedImageUri=uri
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment?.let {
                parentFragment as DialogListener
            } ?: context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() + " 필수요소 구현안됨")
            )
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        _binding = AddDialogBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.dialogBtnSave.setOnClickListener {
            if (binding.dialogUserName.text.isEmpty() || binding.dialogUserPhoneNumber.text.isEmpty() || binding.dialogUserEmail.text.isEmpty()) {
                Toast.makeText(requireContext(), "이름, 번호, 이메일은 필수 입력사항입니다.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                listener.onDialogPositiveClick(this)
                Log.d("TAG", "is empty? : ${binding.dialogUserName.text.isEmpty()}")
                val addName = binding.dialogUserName.text.toString()
                val addNum = binding.dialogUserPhoneNumber.text.toString()
                val addEmail = binding.dialogUserEmail.text.toString()

                setFragmentResult(
                    DataKey.ADD_USER_NAME_KEY,
                    bundleOf(DataKey.ADD_USER_NAME_KEY to addName)
                )
                setFragmentResult(
                    DataKey.ADD_USER_NUM_KEY,
                    bundleOf(DataKey.ADD_USER_NUM_KEY to addNum)
                )
                setFragmentResult(
                    DataKey.ADD_USER_EMAIL_KEY,
                    bundleOf(DataKey.ADD_USER_EMAIL_KEY to addEmail)
                )
                setFragmentResult("modify", bundleOf(
                    "modifyKey" to loadedImageUri
                ))
                dismiss()
            }
        }

        imageView=binding.dialogUserImg

        val getImage=registerForActivityResult(ActivityResultContracts.GetContent()) {
                uri: Uri? -> if(uri!=null){
            loadImage(uri)
            }
        }

        binding.dialogUserImg.setOnClickListener {
            getImage.launch("image/*")
        }
        binding.dialogBtnCancel.setOnClickListener {
            listener.onDialogNegativeClick(this)
            dismiss()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}