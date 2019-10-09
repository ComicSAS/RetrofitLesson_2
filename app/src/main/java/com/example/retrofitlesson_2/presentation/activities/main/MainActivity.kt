package com.example.retrofitlesson_2.presentation.activities.main


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlesson_2.R
import com.example.retrofitlesson_2.pojo.Repo
import com.example.retrofitlesson_2.presentation.adapter.RepoAdapter
import com.example.retrofitlesson_2.presentation.server.RepoApi
import com.example.retrofitlesson_2.presentation.server.RepoApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_repo.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var api: RepoApi
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buildApi()
        getRepos()
    }

    @SuppressLint("CheckResult")
    private fun getRepos() {
        api.getAllRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Response<List<Repo>>>() {
                override fun onSuccess(t: Response<List<Repo>>) {
                    initRecyclerView(t.body())
                    initDeleteButton()
                }

                override fun onError(e: Throwable) {}
            })
    }

    @SuppressLint("CheckResult")
    private fun deleteRepo(id: Int) {
        api.deleteRepoById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<String>() {
                override fun onSuccess(response: String) {
                    showMessage(response, null)
                }

                override fun onError(e: Throwable) {
                    showMessage(null, e.message)
                }
            })
        getRepos()
    }

    private fun initDeleteButton() {
        btnDelete.setOnClickListener { deleteRepo(Integer.valueOf(tvRvId.text.toString())) }
    }

    private fun showMessage(response: String?, e: String?) {
        if (e == null && response != null)
            Toast.makeText(this, "$response: Repository successfully deleted", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "$response: error: $e", Toast.LENGTH_LONG).show()
    }


    fun initRecyclerView(records: List<Repo>?) {
        rvRepos.removeAllViews()
        if (records!!.isNotEmpty()) {
            rvRepos.visibility = VISIBLE
            tvMessage.visibility = GONE

            val manager = LinearLayoutManager(this)
            adapter = RepoAdapter(this, records)
            rvRepos.layoutManager = manager
            rvRepos.adapter = adapter
        } else {
            rvRepos.visibility = GONE
            tvMessage.visibility = VISIBLE
        }

    }

    private fun buildApi() {
        api = RepoApiClient.getClient()
    }
}