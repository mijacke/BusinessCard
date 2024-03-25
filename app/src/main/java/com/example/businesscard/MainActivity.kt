package com.example.businesscard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.ui.theme.BusinessCardTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                SideEffect {
                    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
                    window.statusBarColor = android.graphics.Color.parseColor("#91c579")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xff91c579)
                ) {
                    BusinessCard(
                        onPhoneClick = { dialPhoneNumber() },
                        onEmailClick = { sendEmail() },
                        onInstagramClick = { openInstagram() }
                    )
                }
            }
        }
    }
    private fun dialPhoneNumber() {
        startActivity(Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel: +421 903051759")
        })
    }

    private fun sendEmail() {
        startActivity(Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto: lassumario2002@gmail.com")
        })
    }

    private fun openInstagram() {
        // Attempt to open in the Instagram app first
        val uri = Uri.parse("https://instagram.com/_u/mijackee")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.instagram.android")
        }

        // Fall back to browser if Instagram app is not installed
        if (intent.resolveActivity(packageManager) == null) {
            intent.data = Uri.parse("https://instagram.com/mijackee")
            intent.setPackage(null)
        }

        startActivity(intent)
    }
}

@Composable
fun BusinessCard(
    onPhoneClick: () -> Unit,
    onEmailClick: () -> Unit,
    onInstagramClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f), // This takes up 1/2 of the space
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = Color(0xff8d5524),
                modifier = Modifier
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Android Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Mário Laššú",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = Color(0xffc68642)
            )
            Text(
                text = "Android Developer Extraordinaire",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color(0xffd08642)
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactDetail(
                icon = Icons.Default.Phone,
                detail = "+421 903051759",
                contentDescription = "Phone number",
                color = 0xff8d5524,
                onClick = onPhoneClick // Use the passed lambda here
            )
            ContactDetail(
                icon = Icons.Default.Share,
                detail = "Instagram: mijackee   ",
                contentDescription = "Social media handle",
                color = 0xffc68642,
                onClick = onInstagramClick // Use the passed lambda here
            )
            ContactDetail(
                icon = Icons.Default.Email,
                detail = "lassumario2002@gmail.com",
                contentDescription = "Email address",
                color = 0xffe09b58,
                onClick = onEmailClick // Use the passed lambda here
            )
            Spacer(Modifier.height(24.dp))
        }


    }
}

@Composable
fun ContactDetail(icon: ImageVector, detail: String, contentDescription: String, color: Long, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Make the whole row clickable
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = Color(color),
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClick) // Make the icon clickable
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = detail,
            color = Color(color),
            modifier = Modifier
                .clickable(onClick = onClick) // Make the text clickable
        )
    }
}




@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(
            onPhoneClick = {},
            onEmailClick = {},
            onInstagramClick = {}
        )
    }
}