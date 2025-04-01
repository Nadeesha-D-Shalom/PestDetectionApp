package com.example.sensorhandlerapp
import com.google.gson.annotations.SerializedName


import retrofit2.Call
import retrofit2.http.GET


data class DetectionData(
    @SerializedName("detection_type") val detectionType: String?,
    @SerializedName("detection_time") val detectionTime: String?
)

interface ApiService {
    @GET("/detections")
    fun getDetection(): Call<DetectionData>  // Ensure this matches the API response
}
