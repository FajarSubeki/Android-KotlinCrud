package com.example.kotlincrud

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.kotlincrud.confifg.ApiEndPoint
import com.example.kotlincrud.util.toast
import kotlinx.android.synthetic.main.activity_manage_student.*
import kotlinx.android.synthetic.main.student_list.*
import org.json.JSONObject

class ManageStudentActivity : AppCompatActivity() {

    lateinit var i: Intent
    private var gender = "Pria"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_student)

        supportActionBar?.title = "Kelola Siswa"

        i = intent
        if (i.hasExtra("editmode")){
            if (i.getStringExtra("editmode").equals("1")){
                onEditMode()
            }
        }

        rgGender.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.radioBoy->{
                    gender = "Pria"
                }
                R.id.radioGirl->{
                    gender = "Wanita"
                }
            }
        }

        btn_input.setOnClickListener {
            create()
        }

        btn_ubah.setOnClickListener {
            update(applicationContext)
        }

        btn_hapus.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus Data Ini ?")
                .setPositiveButton("HAPUS",DialogInterface.OnClickListener { dialogInterface, i ->
                    delete(applicationContext)
                })
                .setNegativeButton("BATAL",DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }

    }

    private fun delete(context: Context) {
        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"?nis="+et_nis.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    context.toast(response?.getString("message"))

                    if(response?.getString("message")?.contains("berhasil")!!){
                        this@ManageStudentActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    context.toast("Koneksi Gagal")
                }


            })
    }

    private fun update(context: Context) {
        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
            .addBodyParameter("nis", et_nis.text.toString())
            .addBodyParameter("name", et_nama.text.toString())
            .addBodyParameter("address", et_alamat.text.toString())
            .addBodyParameter("gender", gender)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object  : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    context.toast(response?.getString("message"))

                    if (response?.getString("message")?.contains("berhasil")!!){
                        this@ManageStudentActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    context.toast("Koneksi Gagal")
                }

            })
    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan Data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("nis", et_nis.text.toString())
            .addBodyParameter("name", et_nama.text.toString())
            .addBodyParameter("address", et_alamat.text.toString())
            .addBodyParameter("gender", gender)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()
                    if(response?.getString("message")?.contains("berhasil")!!){
                        this@ManageStudentActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.e("ERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Koneksi Gagal", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun onEditMode() {
        et_nis.isEnabled = false
        et_nis.setText(i.getStringExtra("nis"))
        et_nama.setText(i.getStringExtra("name"))
        et_alamat.setText(i.getStringExtra("address"))

        btn_input.visibility = View.GONE
        btn_ubah.visibility = View.VISIBLE
        btn_hapus.visibility = View.VISIBLE

        gender = i.getStringExtra("gender")
        if (gender.equals("Pria")){
            rgGender.check(R.id.radioBoy)
        }else{
            rgGender.check(R.id.radioGirl)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
