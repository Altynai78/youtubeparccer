package com.example.youtubeparccer.ui.adapters

import android.annotation.SuppressLint
import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PlayListAdapter(private val onClick: (ClipData.Item) -> Unit) : RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    private var list = ArrayList<ClipData.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<ClipData.Item>) {
        this.list = list as ArrayList<ClipData.Item>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        return PlayListViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            with(binding) {
                tvTitle.text = item.snippet?.title
                tvCountVideos.text = item.contentDetails?.itemCount.toString() + " video series"
                imgPreview.loadImage(item.snippet?.thumbnails?.default?.url!!)
                itemView.setOnClickListener {
                    onClick.invoke(item)
                }

            }

        }
    }
}
