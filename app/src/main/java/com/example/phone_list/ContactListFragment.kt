package com.example.phone_list

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
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
class ContactListFragment : Fragment(), ShowDialogFragment.DialogListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var modify : Uri?=null

    val dataList = mutableListOf<ContactListFragmentData>()

    init {
        dataList.add(
            ContactListFragmentData(
                R.drawable.sn_1,
                "써니",
                "01012345678",
                "abcd1234@gamil.com",
                false,
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.hy,
                "효연",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.ty,
                "태연",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.yr,
                "유리",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.js,
                "제시카",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.tp,
                "티파니",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.sh,
                "서현",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.ya,
                "윤아",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )
        dataList.add(
            ContactListFragmentData(
                R.drawable.sy,
                "수영",
                "01012345678",
                "abcd1234@gamil.com",
                false
            )
        )

    }
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Toast.makeText(dialog.context, "저장 됨", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(dialog.context, "취소 됨", Toast.LENGTH_SHORT).show()
    }

    private var _binding: ContactListViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dialogResult = mutableListOf<String?>()
        setFragmentResultListener(DataKey.ADD_USER_NAME_KEY) { requestKey, bundle ->
            if (dialogResult.size < 3) {
                dialogResult.add(bundle.getString(DataKey.ADD_USER_NAME_KEY))
            } else {
                dialogResult[0] = bundle.getString(DataKey.ADD_USER_NAME_KEY)
            }
            Log.d("TAG", "addName: $dialogResult")
        }
        setFragmentResultListener(DataKey.ADD_USER_NUM_KEY) { requestKey, bundle ->
            if (dialogResult.size < 3) {
                dialogResult.add(bundle.getString(DataKey.ADD_USER_NUM_KEY))
            } else {
                dialogResult[1] = bundle.getString(DataKey.ADD_USER_NUM_KEY)
            }
            Log.d("TAG", "addNum: $dialogResult")
        }
        setFragmentResultListener("modify") {requestKey, bundle ->
            modify=bundle.getParcelable("modifyKey")
        }
        setFragmentResultListener(DataKey.ADD_USER_EMAIL_KEY) { requestKey, bundle ->
            if (dialogResult.size < 3) {
                dialogResult.add(bundle.getString(DataKey.ADD_USER_EMAIL_KEY))
            } else {
                dialogResult[2] = bundle.getString(DataKey.ADD_USER_EMAIL_KEY)
            }
            Log.d("TAG", "addEmail: $dialogResult")
            dataList.add(
                ContactListFragmentData(
                    R.drawable.dialog_user_img,
                    dialogResult[0]!!,
                    dialogResult[1]!!,
                    dialogResult[2]!!,
                    false,
                    false,
                    modify
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ContactListViewBinding.inflate(inflater, container, false) // Binding 초기화
        binding.fabAddUser.setOnClickListener {
            val showDialogFragment = ShowDialogFragment()
            showDialogFragment.show(parentFragmentManager, "addDialog")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView1.layoutManager = if (LayoutType.layoutType==0){ LinearLayoutManager(requireContext())} else{ GridLayoutManager(requireContext(),3)}
        binding.recyclerView1.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayout.VERTICAL))


        val adapter = ContactListFragmentAdapter(dataList)
        binding.recyclerView1.adapter = adapter

        // viewtype 수정 버튼
        binding.notify.setOnClickListener {
            var convertDesc = binding.notify.text
            if (convertDesc.toString() == "Grid 형태로 보기"){binding.notify.text = "Linear형태로 보기"}else{binding.notify.text = "Grid 형태로 보기"}
            if (LayoutType.layoutType == 0){LayoutType.layoutType = 1}else{LayoutType.layoutType = 0}
            binding.recyclerView1.layoutManager = if (LayoutType.layoutType==0){ LinearLayoutManager(requireContext())} else{ GridLayoutManager(requireContext(),3)}
            adapter.notifyDataSetChanged()
        }

        //
        val detailFrag = ContactDetailFragment()
        adapter.itemClick = object : ContactListFragmentAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Log.v("test", "click!!")
                val bundle = Bundle()
                bundle.putInt("img", dataList[position].profileImage)
                bundle.putString("name", dataList[position].aname)
                bundle.putString("phoneNum", dataList[position].userPhoneNum)
                bundle.putString("eMail", dataList[position].userEmail)
                bundle.putBoolean("like", dataList[position].userIsLiked)
                bundle.putParcelable("modify",dataList[position].modifyImg)
                bundle.putInt("position",position)

                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.contact_Main, detailFrag)
                detailFrag.arguments = bundle
                transaction.commit()
            }
        }
        setFragmentResultListener("key") {requestKey, bundle ->
            val nameResult=bundle.getString("nameKey")
            val numberResult=bundle.getString("numberKey")
            val eMailResult=bundle.getString("eMailKey")
            val positionResult=bundle.getInt("positionKey")
            val imgResult : Uri?=bundle.getParcelable("imgKey")

            dataList[positionResult].aname=nameResult!!
            dataList[positionResult].userEmail=eMailResult!!
            dataList[positionResult].userPhoneNum=numberResult!!
            dataList[positionResult].modifyImg=imgResult
            adapter.notifyItemChanged(positionResult)
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