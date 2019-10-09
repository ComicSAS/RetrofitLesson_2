package com.example.retrofitlesson_2.pojo

data class RepoResponse(

    private val records: List<Repo>
) {
    fun getRecords(): List<Repo> = records
}