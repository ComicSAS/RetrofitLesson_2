package com.example.retrofitlesson_2.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.retrofitlesson_2.R
import com.example.retrofitlesson_2.pojo.Repo
import com.example.retrofitlesson_2.presentation.base.BaseAdapter
import com.example.retrofitlesson_2.presentation.item.RepoViewHolder

class RepoAdapter(
    private val context: Context,
    private val listRepo: List<Repo>
) : BaseAdapter<RepoViewHolder, Repo>(listRepo as MutableList<Repo>) {

    override fun getItemCount(): Int = listRepo.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(listRepo[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false))
}