package com.example.week4_willy
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week4_willy.data.dummy_data
import com.example.week4_willy.ui.theme.Week4_WillyTheme
import com.example.week4_willy.ui.view.LinePreview
import com.example.week4_willy.ui.view.No1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4_WillyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    No1(dummy_data().get_data_line())

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Week4_WillyTheme {
        LinePreview()
    }
}