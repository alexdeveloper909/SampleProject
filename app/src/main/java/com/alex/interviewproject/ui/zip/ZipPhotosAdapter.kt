package com.alex.interviewproject.ui.zip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alex.interviewproject.R
import com.alex.interviewproject.framework.domain.City
import kotlinx.android.synthetic.main.staggered_grid_photo_item.view.*

class ZipPhotosAdapter
    : ListAdapter<Photo, ZipPhotosAdapter.NoteViewHolder>(PhotosDiffUtilCallBack()) {


    class NoteViewHolder(root: View): RecyclerView.ViewHolder(root){
        var photo = root.profileImage!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
            inflater.inflate(R.layout.staggered_grid_photo_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {note ->
            holder.photo.setImageBitmap(note.photo)
        }
    }
}

class PhotosDiffUtilCallBack : DiffUtil.ItemCallback<Photo>(){
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }


}