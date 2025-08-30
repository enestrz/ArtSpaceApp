package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

// ArtItem nesnelerinden oluşan liste
private val artItemList = listOf(
    ArtItem("Space Nebula", "From NASA", R.drawable.nabula),
    ArtItem("Parrot", "Ahmet Botanik", R.drawable.parrot),
    ArtItem("Ankara Otoban", "Hakan Yollu", R.drawable.highway),
    ArtItem("Pink Flamengo", "Ayşe Leylek", R.drawable.flamengo),
    ArtItem("Harley Davidson", "Motorcu Kenan", R.drawable.motorcycle)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ArtSpaceAppLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceAppLayout() {
    var counter by remember { mutableIntStateOf(0) }

    val currentArtItem = artItemList[counter]

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtViewSection(
            artItem =  currentArtItem,
            modifier = Modifier.weight(1f)
        )
        DirectiveButtons(counter) {
            val artLast = artItemList.size - 1
            if (it == -1 && counter == 0) counter = artLast
            else if (it == 1 && counter == artLast)  counter = 0
            else counter += it
        }
    }
}

@Composable
fun ArtViewSection(
    artItem: ArtItem,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(artItem.imageId),
                    contentDescription = artItem.imageId.toString(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = artItem.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Text(
                text = artItem.artist,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }

}

@Composable
fun DirectiveButtons(
    counter: Int,
    onCounterChanged: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                onCounterChanged(-1)
            }
        ) {
            Text(
                text = "Previous"
            )
        }
        Text(
            (counter + 1).toString()
        )
        Button(
            onClick = {
                onCounterChanged(1)
            }
        ) {
            Text(
                text = "Next"
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceAppLayout()
    }
}