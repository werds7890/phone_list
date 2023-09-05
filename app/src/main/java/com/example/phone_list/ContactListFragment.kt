package com.example.phone_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    private lateinit var binding: ContactListViewBinding

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
        binding = ContactListViewBinding.inflate(inflater, container, false) // Binding 초기화
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = mutableListOf<ContactListFragmentData>()
        dataList.add(ContactListFragmentData(profileImage = R.drawable.sn_1, aname = "써니"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.hy, aname = "효연"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.ty, aname = "태연"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.yr, aname = "유리"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.js, aname = "제시카"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.tp, aname = "티파니"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.sh, aname = "서현"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.ya, aname = "윤아"))
        dataList.add(ContactListFragmentData(profileImage = R.drawable.sy, aname = "수영"))

        binding.recyclerView1.adapter = ContactListFragmentAdapter(dataList)
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