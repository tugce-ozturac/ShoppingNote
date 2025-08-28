package com.tugce.shoppingnote.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugce.shoppingnote.R
import com.tugce.shoppingnote.model.Item
@Composable
fun DetailScreen(item: Item?, deleteFunction: () -> Unit) {
    Box(modifier =
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
        , contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text=item?.itemName ?: "",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            val imageBitmap = item?.image?.let { byteArray ->
                BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)?.asImageBitmap()
            }


            Image(bitmap = imageBitmap ?: ImageBitmap.imageResource(id = R.drawable.selectimage),
                contentDescription = item?.itemName ?: "",
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 200.dp)
            )

            Text(text=item?.storeName ?: "",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(text=item?.price ?: "",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                deleteFunction()
            }) {
                Text("Delete")
            }
        }
    }
}