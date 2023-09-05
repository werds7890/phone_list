package com.example.phone_list

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.phone_list.databinding.AddDialogBinding

class ShowDialogFragment:DialogFragment() {
    private var _binding: AddDialogBinding? = null
    private val binding get() = _binding!!

    interface DialogListener {
        fun onDialogPositiveClick(dialog:DialogFragment)
        fun onDialogNegativeClick(dialog:DialogFragment)
    }
    private lateinit var listener: DialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        }catch (e:ClassCastException){
            throw ClassCastException(
                (context.toString() + " 필수요소 구현안됨")
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
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
            listener.onDialogPositiveClick(this)
            val contactListFragment = ContactListFragment()
            val bundle = Bundle()
            contactListFragment.arguments = bundle
            bundle.apply {
                putInt(DataKey.ADD_USER_IMG_INT_KEY,R.drawable.dialog_user_img)
                putString(DataKey.ADD_USER_NAME_KEY,binding.dialogUserName.text.toString())
                putString(DataKey.ADD_USER_NUM_KEY,binding.dialogUserPhoneNumber.text.toString())
                putString(DataKey.ADD_USER_EMAIL_KEY,binding.dialogUserEmail.text.toString())
                putBoolean(DataKey.ADD_USER_ISLIKED_BOOLEAN_KEY,false)
            }
            dismiss()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_contact_frame_layout,contactListFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
            binding.dialogBtnCancel.setOnClickListener {
                listener.onDialogNegativeClick(this)
                dismiss()

            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}