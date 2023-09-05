package com.example.phone_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.phone_list.databinding.ContactListViewBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ContactListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val dataList = mutableListOf<ContactListFragmentData>()

    init {
        dataList.add(ContactListFragmentData(R.drawable.sn_1, "써니","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.hy,  "효연","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.ty, "태연","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.yr,  "유리","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.js,  "제시카","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.tp, "티파니","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.sh, "서현","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.ya, "윤아","01012345678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.sy,"수영","01012345678","abcd1234@gamil.com",false))

    }

    private var _binding : ContactListViewBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ContactListViewBinding.inflate(inflater, container, false) // Binding 초기화

        val bundle = arguments
        if (bundle != null) {
            val addName = bundle.getString(DataKey.ADD_USER_NAME_KEY)
            val addNum = bundle.getString(DataKey.ADD_USER_NUM_KEY)
            val addEmail = bundle.getString(DataKey.ADD_USER_EMAIL_KEY)
            val addImg = bundle.getInt(DataKey.ADD_USER_IMG_INT_KEY,R.drawable.dialog_user_img)
            val addIsLiked = bundle.getBoolean(DataKey.ADD_USER_ISLIKED_BOOLEAN_KEY,false)
            dataList.add(ContactListFragmentData(addImg,addName!!,addNum!!,addEmail!!,addIsLiked))
        }



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView1.adapter = ContactListFragmentAdapter(dataList)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

            }
    }
}