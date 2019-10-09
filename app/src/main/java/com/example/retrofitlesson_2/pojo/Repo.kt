package com.example.retrofitlesson_2.pojo

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("id")
    val id:Int,
    @SerializedName("node_id")
    val nodeId:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("full_name")
    val fullName:String)