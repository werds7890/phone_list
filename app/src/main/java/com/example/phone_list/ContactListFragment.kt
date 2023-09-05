package com.example.phone_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var _binding : ContactListViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ContactListViewBinding.inflate(inflater, container, false) // Binding 초기화
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataList.add(ContactListFragmentData(R.drawable.sn_1, "써니","010-1212-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.hy,  "효연","010-2323-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.ty, "태연","010-3434-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.yr,  "유리","010-4545-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.js,  "제시카","010-5656-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.tp, "티파니","010-6767-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.sh, "서현","010-7878-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.ya, "윤아","010-8989-5678","abcd1234@gamil.com",false))
        dataList.add(ContactListFragmentData(R.drawable.sy,"수영","010-9090-5678","abcd1234@gamil.com",false))

        val adapter = ContactListFragmentAdapter(dataList)
        binding.recyclerView1.adapter=adapter
        binding.recyclerView1.layoutManager= LinearLayoutManager(requireContext())

        val detailFrag=ContactDetailFragment()

        adapter.itemClick=object : ContactListFragmentAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Log.v("test","click!!")
                val bundle=Bundle()
                bundle.putInt("img",dataList[position].profileImage)
                bundle.putString("name",dataList[position].aname)
                bundle.putString("phoneNum",dataList[position].userPhoneNum)
                bundle.putString("eMail",dataList[position].userEmail)
                bundle.putBoolean("like",dataList[position].userIsLiked)

                val fragmentManager=requireActivity().supportFragmentManager
                val transaction=fragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.contact_Main,detailFrag)
                detailFrag.arguments=bundle
                transaction.commit()
            }
        }

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