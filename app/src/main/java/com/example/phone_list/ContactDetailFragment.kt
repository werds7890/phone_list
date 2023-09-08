package com.example.phone_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.example.phone_list.databinding.FragmentContactDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactDetailFragment : Fragment() {
    private lateinit var binding : FragmentContactDetailBinding
    private var loadedImageUri : Uri?=null
    private var position : Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentContactDetailBinding.inflate(inflater,container,false)
        arguments?.let {
            var name=it.getString("name")
            val img=it.getInt("img")
            val number=it.getString("phoneNum")
            val email=it.getString("eMail")
            position=it.getInt("position")
            val modify : Uri?=it.getParcelable("modify")

            binding.detailName.text=name
            binding.detailImg.setImageResource(img)
            binding.detailNumber.text=number
            binding.detailEmail.text=email
            if(modify!=null){
                binding.detailImg.setImageURI(modify)
            }
        }
        return binding.root
    }


    private lateinit var imageView: ImageView
    private fun loadImage(uri:Uri) {
        Glide.with(this)
            .load(uri)
            .into(imageView)
            loadedImageUri=uri
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var name : String?=null
        var number : String?=null
        var eMail : String?=null
        var img : Uri?=null

        imageView=binding.detailImg

        val getImage=registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? -> if(uri!=null){
                loadImage(uri)
            }
        }

        binding.detailModify.setOnClickListener {

            binding.imgModify.visibility=View.VISIBLE

            binding.save.visibility=View.VISIBLE
            binding.detailModify.visibility=View.INVISIBLE

            binding.detailNumber.visibility=View.GONE
            binding.editNumber.visibility=View.VISIBLE
            binding.editNumber.setText(binding.detailNumber.text)

            binding.detailName.visibility=View.GONE
            binding.editName.visibility=View.VISIBLE
            binding.editName.setText(binding.detailName.text)

            binding.detailEmail.visibility=View.GONE
            binding.editEmail.visibility=View.VISIBLE
            binding.editEmail.setText(binding.detailEmail.text)
        }

        binding.imgModify.setOnClickListener {
            getImage.launch("image/*")
        }

        binding.save.setOnClickListener {
            Toast.makeText(this.requireContext(),"저장이 완료됐습니다.",Toast.LENGTH_SHORT).show()
            binding.imgModify.visibility=View.INVISIBLE

            binding.detailNumber.visibility=View.VISIBLE
            binding.editNumber.visibility=View.GONE
            binding.detailNumber.text=binding.editNumber.text

            binding.detailName.visibility=View.VISIBLE
            binding.editName.visibility=View.GONE
            binding.detailName.text=binding.editName.text

            binding.detailEmail.visibility=View.VISIBLE
            binding.editEmail.visibility=View.GONE
            binding.detailEmail.text=binding.editEmail.text

            binding.detailModify.visibility=View.VISIBLE
            binding.save.visibility=View.GONE

            eMail=binding.detailEmail.text.toString()
            name=binding.detailName.text.toString()
            number=binding.detailNumber.text.toString()
            img=loadedImageUri

            setFragmentResult("key",bundleOf(
                "nameKey" to name,
                "numberKey" to number,
                "eMailKey" to eMail,
                "positionKey" to position,
                "imgKey" to img
            ))
        }

        binding.detailCancel.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        binding.call.setOnClickListener {
            val intent= Intent(Intent.ACTION_DIAL)
            val phoneNumber=binding.detailNumber.text.toString()
            val num=Uri.parse("tel:${phoneNumber}")
            intent.data=num
            startActivity(intent)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}