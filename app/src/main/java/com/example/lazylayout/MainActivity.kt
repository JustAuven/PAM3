package com.example.lazylayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lazylayout.ui.theme.LazyLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gadgets = listOf(
            Gadget(1, "Smartphone", "Perangkat komunikasi genggam", R.drawable.smartphone),
            Gadget(2, "Laptop", "Komputer portabel untuk bekerja", R.drawable.laptop),
            Gadget(3, "Tablet", "Perangkat layar sentuh", R.drawable.tablet),
            Gadget(4, "Smartwatch", "Jam tangan pintar", R.drawable.smartwatch)
        )

        setContent {
            LazyLayoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GadgetListWithHeader(
                        gadgets = gadgets,
                        nim = "245150400111026",
                        nama = "Naufal Aldrin Danita"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GadgetListWithHeader(gadgets: List<Gadget>, nim: String, nama: String) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NIM: $nim",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Nama: $nama",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        items(gadgets) { gadget ->
            GadgetItem(gadget)
        }
    }
}

@Composable
fun GadgetItem(gadget: Gadget) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = gadget.imageRes),
                contentDescription = gadget.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = gadget.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = gadget.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}