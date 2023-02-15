package com.example.fragmentart

import Adapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentart.data.ApiInterface
import com.example.fragmentart.databinding.FragmentListBinding
import com.example.fragmentart.interfaces.UserSelection
import com.example.fragmentart.model.DataModelItem
import retrofit2.Call
import retrofit2.Response


class Fragment_List : Fragment() {

    lateinit var adapter: Adapter
    var data = ArrayList<DataModelItem>()
    var isLoading = false
    val apiCall = ApiInterface.create()
    var currentPage = 1
    private var back_pressed: Long = 0
    private var _binding: FragmentListBinding? = null
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

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        initScrollListener()
        loadPageList()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setLayoutManager(layoutManager)
        adapter = Adapter(object : UserSelection {
            override fun onClick(id: Int) {
                (activity as MainActivity).moveToDetails(id)
            }

            override fun long() {

            }
        }, data)
        binding.recyclerView.adapter = adapter

        return view
    }

    private fun loadPageList() {
        apiCall.getData(10, "Latest", currentPage)
            .enqueue(object : retrofit2.Callback<ArrayList<DataModelItem>> {
                override fun onFailure(call: Call<ArrayList<DataModelItem>>?, t: Throwable?) {
                    t?.printStackTrace()
                    Log.e("scroll", "exception", t)
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ArrayList<DataModelItem>>,
                    response: Response<ArrayList<DataModelItem>>
                ) {
                    if (response.isSuccessful) {
                        val dataList = response.body()
                        if (dataList != null) {
                            data.addAll(dataList)
                            adapter.notifyDataSetChanged()
                        }
                    }

                }

            })
        fun onDestroy() {
            super.onDestroy()
            _binding = null

        }
    }

    private fun initScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager
                            .findLastCompletelyVisibleItemPosition() == data.size - 1
                    ) {
                        currentPage += 1
                        loadPageList()
                    }
                }
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_List().apply {
                arguments = Bundle().apply {

                }
            }
    }
}