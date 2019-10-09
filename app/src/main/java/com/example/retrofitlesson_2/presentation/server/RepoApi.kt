package com.example.retrofitlesson_2.presentation.server

import com.example.retrofitlesson_2.pojo.Repo

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {

    @GET("users/comicsas/repos")
    fun getAllRepos(): Single<Response<List<Repo>>>

    @DELETE("users/comicsas/repos/{id}")
    fun deleteRepoById(@Path("id") id: Int): Single<String>
}