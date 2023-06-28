package com.sanmobi.kotlin_retrofit_template

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanmobi.kotlin_retrofit_template.adapter.PhotoAdapter
import com.sanmobi.kotlin_retrofit_template.model.Photo
import com.sanmobi.kotlin_retrofit_template.network.ApiClient
import com.sanmobi.kotlin_retrofit_template.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi();
        getData();

    }

    private fun initUi() {
        recyclerView = findViewById(R.id.recyclerview)
        progressBar = findViewById(R.id.progress)
        photoAdapter = PhotoAdapter(this)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = photoAdapter

    }

    private fun getData() {

        val apiService: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        val call: Call<List<Photo>> = apiService.getPhotos()


        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if(response.body() != null)
                    photoAdapter.setMovieListItems(response.body()!!)
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Toast.makeText(applicationContext,"Network Problem", Toast.LENGTH_SHORT).show()
            }

        })

    }
}

