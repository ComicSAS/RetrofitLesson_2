package com.example.retrofitlesson_2.presentation.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, M>
    (protected var list: MutableList<M>) : RecyclerView.Adapter<VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = list[position]
    }

    fun getItem(position: Int): M = list[position]

    override fun getItemCount(): Int = list.size

    fun add(item: M) {
        list.add(item)
    }

    fun addAll(list: MutableList<M>) {
        for (item in list)
            add(item)
    }

    fun remove(item: M) {
        val position = list.indexOf(item)
        if (position > -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }
}