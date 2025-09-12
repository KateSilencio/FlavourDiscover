package com.example.flavourdiscover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flavourdiscover.ui.theme.FlavourDiscoverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlavourDiscoverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        testFunction(1,2,3,4,5,6)

    }
}

// Нарушение 1: Длинный список параметров
fun testFunction(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int) {
    // Нарушение 2: TODO комментарий
    TODO("Это нужно доделать позже")

    // Нарушение 3: Неиспользуемый параметр
    val unused = "не используется"
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlavourDiscoverTheme {
        Greeting("Android")
    }
}