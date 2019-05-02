package com.example.kotlincrud.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlincrud.ManageStudentActivity
import com.example.kotlincrud.model.Students
import com.example.kotlincrud.R
import kotlinx.android.synthetic.main.student_list.view.*

class RVAdapterStudent(private val context: Context, private val arrayList: ArrayList<Students>) : RecyclerView.Adapter<RVAdapterStudent.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.student_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.tv_nis.text = arrayList?.get(position)?.nis
        holder.view.tv_nama.text = arrayList?.get(position)?.name
        holder.view.tv_alamat.text = arrayList?.get(position)?.address
        holder.view.tv_gender.text = arrayList?.get(position)?.gender

        val gender = arrayList?.get(position)?.gender
        if (gender == "Pria"){
            holder.view.iv_avatar.setImageResource(R.drawable.ic_boss)
        }else if(gender == "Wanita"){
            holder.view.iv_avatar.setImageResource(R.drawable.ic_girl)
        }

        holder.view.cv_list.setOnClickListener {
            val i = Intent(context, ManageStudentActivity::class.java)
            i.putExtra("editmode", "1")
            i.putExtra("nis", arrayList?.get(position)?.nis)
            i.putExtra("name", arrayList?.get(position)?.name)
            i.putExtra("address", arrayList?.get(position)?.address)
            i.putExtra("gender", arrayList?.get(position)?.gender)
            context.startActivity(i)
        }

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}