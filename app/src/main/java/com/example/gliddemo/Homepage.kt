package com.example.gliddemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Homepage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: CustomAdapter
    private var dataArrayList: List<RandomImagesItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        //Recyclerview  work is here
recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        //Retrofit work is here
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApiservice::class.java)

        val retrofitdata=retrofitBuilder.getimage()

        retrofitdata.enqueue(object : Callback<List<RandomImagesItem>> {
            override fun onResponse(call: Call<List<RandomImagesItem>>, response: Response<List<RandomImagesItem>>) {
                dataArrayList = ArrayList<RandomImagesItem>()
                if (response.isSuccessful && response.body()!=null){
                    val responsebody=response.body()
                    var imageList: ArrayList<String> = ArrayList<String>()
                    if (responsebody != null) {
                        for (image in responsebody){
                            val imageURL=image.download_url
                            imageList.add(imageURL)
                        }
                        Log.d("homepage","on success"+imageList.size)
                        customAdapter=CustomAdapter(this@Homepage,imageList)
                        recyclerView.adapter=customAdapter
                        recyclerView.layoutManager= LinearLayoutManager(this@Homepage)
                    }
                }


            }

            override fun onFailure(call: Call<List<RandomImagesItem>>, t: Throwable) {
                Log.d("homepage","on failed"+t.message)
            }
        })


    }
}