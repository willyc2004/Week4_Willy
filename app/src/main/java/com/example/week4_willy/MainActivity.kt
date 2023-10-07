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
import com.example.week4_willy.ui.theme.Week4_WillyTheme
import com.example.week4_willy.ui.view.No3
import com.example.week4_willy.ui.view.No3Preview


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

//                    No2(dummy_data().get_data_tokopedia_category(), dummy_data().get_data_tokopedia_product())
                    //matikan dark mode no 2

//                    No4()

                    No3()


                    //kurang tau kenapa tp harus import dulu (alt + enter)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    Week4_WillyTheme {
        No3Preview()
    }
}