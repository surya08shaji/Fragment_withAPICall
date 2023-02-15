package com.example.fragmentart

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentart.data.ApiInterface
import com.example.fragmentart.databinding.FragmentDetailsBinding
import com.example.fragmentart.model.ShowModelItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class Fragment_Details : Fragment() {

    var isLoading = false
    var currentPage = 1
    val apiCall = ApiInterface.create()
    var sub_data = ArrayList<ShowModelItem>()
    var value: Int? = null

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        value = arguments?.getInt("id", 0)


        apiCall.getDetails(value).enqueue(object : retrofit2.Callback<ShowModelItem> {
            override fun onFailure(call: Call<ShowModelItem>, t: Throwable) {
                t?.printStackTrace()
                Log.e("scroll", "exception", t)
            }

            override fun onResponse(
                call: Call<ShowModelItem>,
                response: Response<ShowModelItem>
            ) {

                if (response.isSuccessful) {
                    val dataList = response.body()
                    if (dataList != null) {

                        binding.title.text = dataList.title
                        binding.author.text = dataList.author
                        Picasso.with(binding.image.context).load(dataList.image)
                            .into(binding.image)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            binding.content.text =
                                Html.fromHtml(dataList.content, Html.FROM_HTML_MODE_COMPACT)
                        } else {
                            binding.content.text = Html.fromHtml(dataList.content)
                        }
                    }
                }
            }
        })

        return view
    }


}