///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.abpweddingsouptik.R
import com.example.abpweddingsouptik.data.model.Visit
import com.example.abpweddingsouptik.databinding.SingleRowBinding
import kotlinx.android.synthetic.main.single_row.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class VisitHistoryAdapter(private val historyList:  List<Visit>): RecyclerView.Adapter<VisitHistoryAdapter.VisitHistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitHistoryHolder {
        return VisitHistoryHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context)
            , R.layout.single_row,parent,false))
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: VisitHistoryHolder, position: Int) {
        val dateFormat: DateFormat = SimpleDateFormat("MMMM dd,yyyy", Locale.US)
        val timeFormat: DateFormat = SimpleDateFormat("hh.mm aa", Locale.US)
        val visit = historyList[position]
        visit.dateString = dateFormat.format(Date(visit.createdOn)).toString()
        visit.timeString = timeFormat.format(Date(visit.createdOn)).toString()
        if(position==0){
            holder.itemView.tv_date.visibility = VISIBLE
        }else if(position>0 && visit.dateString==historyList[position - 1].dateString){
            holder.itemView.tv_date.visibility = GONE
        }else if(position>0 && visit.dateString!=historyList[position - 1].dateString){
            holder.itemView.tv_date.visibility = VISIBLE
        }
        holder.singleRowBinding.visit = visit
    }

    inner class VisitHistoryHolder(val singleRowBinding: SingleRowBinding): RecyclerView.ViewHolder(singleRowBinding.root){

    }
}