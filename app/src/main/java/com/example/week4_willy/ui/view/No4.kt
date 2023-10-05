package com.example.week4_willy.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week4_willy.R
import com.example.week4_willy.model.Explore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader


@Composable
fun loadJsonData(context: Context): List<Explore> {
    val jsonFileName = "explore.json"
    val inputStream = context.assets.open(jsonFileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Explore>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable
private fun getResourceIdForImage(imageFileName: String): Int {
    return LocalContext.current.resources.getIdentifier(
        imageFileName,
        "drawable",
        LocalContext.current.packageName
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreIG() {
    val context = LocalContext.current
    val jsonData = loadJsonData(context)

    var search by rememberSaveable { mutableStateOf("") }
    Column {
        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(jsonData) { item ->
                Image(
                    painter = painterResource(id = getResourceIdForImage(item.content)),
                    contentDescription = item.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            Toast
                                .makeText(context, "${item.content}", Toast.LENGTH_SHORT)
                                .show()
                        },
                    contentScale = ContentScale.FillHeight
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

        }
    }
}

@Composable
fun PreviewContentList() {
    ExploreIG()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun No4Preview() {
    PreviewContentList()
}