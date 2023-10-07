package com.example.week4_willy.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_willy.R
import com.example.week4_willy.data.dummy_data
import com.example.week4_willy.model.categories
import com.example.week4_willy.model.products

@Composable
fun No2(kategori: List<categories>, produk: List<products>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "CodeBlu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(vertical = 15.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        LazyRow {
            items(kategori) {
                CategoriesCard(
                    it,
                    Modifier
                        .height(180.dp)
                        .padding(start = 12.dp)
                        .width(120.dp)
                )
            }
        }
        Text(
            text = "Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 2.dp, top = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ){
            items(produk){
                ProductsCard(it, Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun CategoriesCard(kategori: categories, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = kategori.image_path),
                contentDescription = "kategori",
                modifier = Modifier
                    .size(120.dp)
                    .padding(15.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = kategori.category_name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${kategori.number_of_items} Products",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )
        }

    }
}

@Composable
fun ProductsCard(produk: products, modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = produk.image_path),
                contentDescription = "kategori",
                modifier = Modifier
                    .size(170.dp)
                    .padding(horizontal = 15.dp).padding(top = 15.dp, bottom = 3.dp),
                contentScale = ContentScale.FillHeight
            )

            Text(
                text = produk.product_name,
                fontSize= 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Rp. ${produk.price}",
                fontSize= 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 16.dp)
            )
            Text(
                text = "Kota ${produk.location}",
                fontSize= 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 16.dp)
            )
            Text(
                text = "${produk.sold} Sold",
                fontSize= 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp, start = 16.dp)
            )
        }

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun No2Preview() {
    No2(dummy_data().get_data_tokopedia_category(), dummy_data().get_data_tokopedia_product())
}