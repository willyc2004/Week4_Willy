package com.example.week4_willy.ui.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_willy.data.dummy_data
import com.example.week4_willy.model.line_chat

@Composable
fun No2(lineList: List<line_chat>) {
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Chats",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White, // Set text color to white
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = Icons.Default.MoreVert, // Replace with your actual icon
                contentDescription = null, // Provide a description for accessibility
                tint = Color.White // Set the icon color to white
            )
        }
        LazyColumn() {
            items(lineList) {
                LineCard(
                    it,
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                )
            }
        }
    }
}

@Composable
fun Categories(lineList: line_chat, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        TextButton(
            onClick = {
                Toast.makeText(context, "${lineList.name} Clicked", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left icon (Assuming you have an Image or Icon component here)
                Icon(
                    imageVector = Icons.Default.Face, // Replace with your actual icon
                    contentDescription = null, // Provide a description for accessibility
                    modifier = Modifier.padding(16.dp).size(50.dp),
                    tint = Color.White // Set the icon color to white
                )

                // Middle column (Name and Message)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = lineList.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White, // Set text color to white
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = lineList.message,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White, // Set text color to white
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )
                }

                // Right date
                Text(
                    text = lineList.date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White, // Set text color to white
                    modifier = Modifier.padding(start = 4.dp, end = 8.dp),
                    textAlign = TextAlign.End
                )
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun No2Preview() {
    No2(dummy_data().get_data_line())
}