package com.example.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.api.databinding.ItemBinding
import com.example.api.model.UIModel

class Adapter(val action: ((uri: String) -> Unit), val loading: () -> Unit) :
    ListAdapter<UIModel, Adapter.MyHolder>(diffUtil) {
    inner class MyHolder(private val viewBinding: ItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(uiModel: UIModel) {
            with(viewBinding) {
                tvImage.load(uiModel.uri)
                cardRoot.setOnClickListener {
                    uiModel.uri?.let{
                        action.invoke(uiModel.uri)
                    }
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UIModel>() {
            override fun areItemsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }
}