package com.example.kotlincrud

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.kotlincrud.adapter.RVAdapterStudent
import com.example.kotlincrud.confifg.ApiEndPoint
import com.example.kotlincrud.model.Students
import com.example.kotlincrud.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var arrayList = ArrayList<Students>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Data Siswa"
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, ManageStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        load(applicationContext)
    }

    private fun load(context: Context){

        val loading = ProgressDialog(this)
        loading.setMessage("Memuat Data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.READ)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {

                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0){
                        loading.dismiss()
                        context.toast("Tidak ada data siswa")
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add(Students(jsonObject.getString("nis"),
                            jsonObject.getString("name"),
                            jsonObject.getString("address"),
                            jsonObject.getString("gender")))

                        if(jsonArray?.length() - 1 == i){
                            loading.dismiss()
                            val adapter = RVAdapterStudent(applicationContext, arrayList)
                            adapter.notifyDataSetChanged()
                            mRecyclerView.adapter = adapter
                        }

                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.e("ERROR", anError?.errorDetail?.toString())
                    context.toast("Koneksi Gagal")
                }

            })

    }

}
