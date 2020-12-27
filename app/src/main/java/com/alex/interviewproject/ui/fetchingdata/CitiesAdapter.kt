package com.alex.interviewproject.ui.fetchingdata

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alex.interviewproject.R
import com.alex.interviewproject.framework.domain.City

class CitiesAdapter : ListAdapter<City, CitiesAdapter.NoteViewHolder>(ContestsDiffUtilCallBack()) {


    class NoteViewHolder(root: View): RecyclerView.ViewHolder(root){
        val textView = root.findViewById(R.id.textView) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
                inflater.inflate(R.layout.city_row, parent,false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {note ->
            holder.textView.text = note.name

        }
    }
}
 class ContestsDiffUtilCallBack : DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }


}