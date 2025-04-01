package com.example.sensorhandlerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.example.sensorhandlerapp.ui.theme.SensorHandlerAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorHandlerAppTheme {
                PestDetectionScreen()
            }
        }
    }
}

@Composable
fun PestDetectionScreen() {
    var detectionType by remember { mutableStateOf("Loading...") }
    var detectionTime by remember { mutableStateOf("Loading...") }

    LaunchedEffect(Unit) {
        fetchDetectionData(
            onSuccess = { data ->
                detectionType = data.detectionType ?: "Unknown"
                detectionTime = data.detectionTime ?: "Unknown"
            },
            onError = { error ->
                detectionType = "Error: $error"
                detectionTime = "Error: $error"
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFa8d5a6))
    ) {
        Text(
            text = "Pest Detection Application",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetectionRow("Detection Type", detectionType)
            Spacer(modifier = Modifier.height(24.dp))
            DetectionRow("Detection Time", detectionTime)
        }
    }
}

@Composable
fun DetectionRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

fun fetchDetectionData(onSuccess: (DetectionData) -> Unit, onError: (String) -> Unit) {
    val call = RetrofitInstance.api.getDetection()
    call.enqueue(object : Callback<DetectionData> {
        override fun onResponse(call: Call<DetectionData>, response: Response<DetectionData>) {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    onSuccess(data)
                } ?: run {
                    onError("Error: Response body is null")
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("RetrofitError", errorBody)
                onError("Error: Response not successful - $errorBody")
            }
        }

        override fun onFailure(call: Call<DetectionData>, t: Throwable) {
            Log.e("RetrofitFailure", "Failure: ${t.message}")
            onError("Failure: ${t.message}")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewPestDetectionScreen() {
    SensorHandlerAppTheme {
        PestDetectionScreen()
    }
}
