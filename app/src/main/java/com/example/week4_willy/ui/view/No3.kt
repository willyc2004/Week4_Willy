package com.example.week4_willy.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_willy.R
import com.example.week4_willy.model.Feed
import com.example.week4_willy.model.Story
import com.example.week4_willy.model.Suggestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

@Composable
fun loadFeedData(context: Context): List<Feed> {
    val jsonFileName = "feed.json"
    val inputStream = context.assets.open(jsonFileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Feed>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable
fun loadStoryData(context: Context): List<Story> {
    val jsonFileName = "story.json"
    val inputStream = context.assets.open(jsonFileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Story>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable
fun loadSuggestionData(context: Context): List<Suggestion> {
    val jsonFileName = "suggestion.json"
    val inputStream = context.assets.open(jsonFileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Suggestion>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable
fun No3() {
    val context = LocalContext.current
    val feedData = loadFeedData(context)
    val storyData = loadStoryData(context)

    var suggestionHit by rememberSaveable { mutableStateOf(0) }

    Box {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            LazyColumn(
                modifier = Modifier
            ) {
                item {
                    Header()
                    StoryRow(storyData)
                }
                items(feedData) { item ->
                    FeedColumn(
                        feedData = item,
                    )
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Black),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                BottomBar(message = "Home", logo = R.drawable.home)
                BottomBar(message = "Search", logo = R.drawable.search)
                BottomBar(message = "Post", logo = R.drawable.post)
                BottomBar(message = "Reels", logo = R.drawable.reels)
                BottomBar(message = "Account", logo = R.drawable.account)
            }
        }
    }
}

@Composable
fun BottomBar(message: String, logo: Int) {

    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            painter = painterResource(id = logo),
            contentDescription = "Logo",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_dark),
            contentDescription = "Logo",
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "Like",
            )

            Image(
                painter = painterResource(id = R.drawable.dm),
                contentDescription = "dm",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Composable
fun StoryRow(storyData: List<Story>) {
    LazyRow(
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        items(storyData) {
            StoryUser(
                storyData = it
            )
        }
    }
}

@Composable
private fun getResourceIdForImage(imageFileName: String): Int {
    return LocalContext.current.resources.getIdentifier(
        imageFileName,
        "drawable",
        LocalContext.current.packageName
    )
}

@Composable
fun StoryUser(storyData: Story, modifier: Modifier = Modifier) {
    val resourceId = getResourceIdForImage(imageFileName = storyData.profilePicture)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val context = LocalContext.current
        TextButton(
            onClick = {
                Toast.makeText(context, "${storyData.username} story", Toast.LENGTH_SHORT).show()
            },
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.story),
                        contentDescription = "Border IG",
                        modifier = Modifier
                            .size(90.dp),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "profile picture",
                        Modifier
                            .clip(shape = CircleShape)
                            .height(80.dp)
                            .width(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

        }

        Text(
            text = storyData.username,
            fontSize = 13.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


@Composable
fun FeedColumn(feedData: Feed) {
    var textExpand by rememberSaveable { mutableStateOf(false) }
    val random = Random.nextInt(3)

    val resourceId = getResourceIdForImage(imageFileName = feedData.profilePicture)
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "profile picture",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(38.dp)
                        .width(38.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = feedData.username,
                modifier = Modifier
                    .padding(horizontal = 11.dp)
                    .weight(1f),
                fontSize = 15.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "more",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.White
            )
        }

        val feedContent = getResourceIdForImage(imageFileName = feedData.feedContent)
        Image(
            painter = painterResource(id = feedContent),
            contentDescription = "image content",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(140.dp)
            ) {
                val context = LocalContext.current
                if (feedData.isLike) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Like Button", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = "like",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                } else {
                    IconButton(onClick = {
                        Toast.makeText(context, "Liked Button", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.liked),
                            contentDescription = "liked",
                            tint = Color.Red,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                IconButton(onClick = {
                    Toast.makeText(context, "Comment Button", Toast.LENGTH_SHORT).show()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = "comment",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }


                IconButton(onClick = {
                    Toast.makeText(context, "Send Button", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.messanger),
                        contentDescription = "send",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            if (feedData.isLike) {
                val context = LocalContext.current
                IconButton(onClick = {
                    Toast.makeText(context, "Save Button", Toast.LENGTH_SHORT).show()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            } else {
                val context = LocalContext.current
                IconButton(onClick = {
                    Toast.makeText(context, "Save Button", Toast.LENGTH_SHORT).show()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.saved_light),
                        contentDescription = "save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        when (feedData.like) {
            0 -> {}
            1 -> {
                Text(
                    text = "${feedData.like} Like",
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 8.dp)
                )
            }

            else -> {
                Text(
                    text = "${formatLikes(feedData.like)} Likes",
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
            }
        }


        TextButton(
            onClick = { textExpand = !textExpand },
            modifier = Modifier.padding(bottom = 10.dp),
            shape = RoundedCornerShape(0.dp)
        )
        {
            if (!textExpand) {
                clickableText(
                    username = feedData.username,
                    caption = feedData.caption,
                    maxLine = 2
                )
            } else {
                clickableText(
                    username = feedData.username,
                    caption = feedData.caption,
                    maxLine = 5000
                )
            }
        }
        var formattedDate by remember { mutableStateOf("") }

        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val sameYearFormat = SimpleDateFormat("MMMM d", Locale.US)
        val notSameYearFormat = SimpleDateFormat("MMMM d, YYYY", Locale.US)
        val parsedDate = inputDateFormat.parse(feedData.date)

        if (parsedDate != null) {
            val calendar = Calendar.getInstance()
            calendar.time = parsedDate

            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val year = calendar.get(Calendar.YEAR)

            formattedDate = if (year == currentYear) {
                sameYearFormat.format(parsedDate)
            } else {
                notSameYearFormat.format(parsedDate)
            }
        } else {
            formattedDate = feedData.date
        }

        Text(
            text = formattedDate,
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 10.dp)
        )

    }
    val context = LocalContext.current
    val suggestionData = loadSuggestionData(context)

    if (random == 0) {
        SuggestRow(suggestionData = suggestionData)
    }
}

fun formatLikes(likes: Int): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    return numberFormat.format(likes)
}

@Composable
fun SuggestRow(suggestionData: List<Suggestion>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        items(suggestionData) {
            SuggestionRow(
                suggestionData = it
            )
        }
    }
}


@Composable
fun SuggestionRow(suggestionData: Suggestion) {
    OutlinedCard(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        val resourceId = getResourceIdForImage(imageFileName = suggestionData.profilePicture)
        Box {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd),
                tint = Color.White
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "profile picture",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(110.dp)
                        .width(110.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = suggestionData.username,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Follow", color = Color.White)
                }
            }
        }
    }

}

@Composable
fun clickableText(username: String, caption: String, maxLine: Int) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = Bold, fontSize = 15.sp)) {
            append(username)
        }
        withStyle(style = SpanStyle(fontWeight = Normal, fontSize = 15.sp)) {
            append(" $caption")
        }
    }

    Text(
        text = text,
        maxLines = maxLine,
        textAlign = TextAlign.Justify,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun No3Preview() {
    No3()
}