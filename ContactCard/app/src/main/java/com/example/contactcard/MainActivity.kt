package com.example.contactcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactcard.ui.theme.ContactCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactCard(
                        appName = "Contact Card",
                        profileIcon = R.drawable.profile,
                    )
                    ContactList(
                        phoneNumber = "+420 123 456 789",
                        emailAdress = "steger.cyril@gmail.com" ,
                        githubLink = "https://github.com/nightguarder")
                    ButtonClick(
                        githubLink = "https://github.com/nightguarder"
                    )
                }
            }
        }
    }
}


@Composable
fun DrawProfileImg(description: String,img:Int){
    val image = painterResource(id = img)
    Column (modifier = Modifier
        .padding(5.dp)
        , // Ensure the Column takes the entire available space
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        Image(painter = image, contentDescription = "$description", modifier = Modifier
            .clip(CircleShape)
            .size(200.dp)
            .scale(1.5f)
            .offset(x = 18.dp)
        )

    }
}
@Composable
fun ContactInfoItem(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 5.dp)
    )
    {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        // Spacer
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, fontSize = 20.sp, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun Icon(imageVector: ImageVector, contentDescription: Nothing?, tint: Color) {
    Image(
        painter = rememberVectorPainter(image = imageVector),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(28.dp)
            .padding(4.dp),
        colorFilter = ColorFilter.tint(tint)
    )
}


//Main Page layout (Section 1)
@Composable
fun ContactCard(appName: String,profileIcon: Int) {
    //Each @Composable is basically a div
    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize(), // Ensure the Column takes the entire available space
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally)
    //Div for H1, image, and br
    {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$appName",
            style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Medium, letterSpacing = 1.2.sp
       )
        Spacer(modifier = Modifier.height(16.dp))
        DrawProfileImg(description = "Profile icon", img = profileIcon)
        Text(text = "Full Stack Developer", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Medium)
    }
}
//Contact list layout (Section 2)
@Composable
fun ContactList(phoneNumber: String,emailAdress: String, githubLink: String){
    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    //Div for Phone, email , github
    {
        // Phone Number
        ContactInfoItem(
            icon = Icons.Default.Phone,
            text = phoneNumber
        )
        ContactInfoItem(icon = Icons.Default.Email, text = emailAdress)
        ContactInfoItem(icon = Icons.Default.Star, text = githubLink )
    }
}
//Button on the bottom
@Composable
fun ButtonClick(githubLink: String){
    //TODO: Broo fix the alignment! Use some margin or alignment to the child.
    //TODO: Implement a link handle
    val uriHandler = LocalUriHandler.current
    Column (modifier = Modifier .padding(bottom = 280.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = { uriHandler.openUri(githubLink) }) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.surface)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Open GitHub")
        }
    }
}
@Preview(showBackground = false , showSystemUi = true, name = "preview")
@Composable
fun ContactCardPreview(){
    ContactCard(appName = "Contact Card", profileIcon = R.drawable.profile)
    ContactList(phoneNumber ="+420 123 456 789" , emailAdress = "steger.cyril@gmail.com",githubLink = "https://github.com/nightguarder")
    ButtonClick(githubLink = "https://github.com/nightguarder")
}
