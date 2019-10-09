package com.example.retrofitlesson_2.presentation.item

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson_2.pojo.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var repo: Repo? = null

    fun bind(repo: Repo) {
        this.repo = repo
        initItem()
    }

    @SuppressLint("SetTextI18n")
    private fun initItem() {
        view.tvRvId.text = "id: " + repo!!.id.toString()
        view.tvRvNodeId.text = "node_id: " + repo!!.nodeId
        view.tvRvName.text = "name: " + repo!!.name
        view.tvRvFullName.text = "full_name:" + repo!!.fullName
    }
}