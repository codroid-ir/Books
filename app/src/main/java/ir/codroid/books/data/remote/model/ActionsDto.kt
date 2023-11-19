package ir.codroid.books.data.remote.model


import com.google.gson.annotations.SerializedName

data class ActionsDto(
    @SerializedName("message")
    val message: String
)