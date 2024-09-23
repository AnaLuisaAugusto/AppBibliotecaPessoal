package com.example.inventory.ui.intro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.R

@Composable
fun IntroScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BodyContent(
        onStartClick = onStartClick,
        modifier = modifier
    )
}

@Composable
fun BodyContent(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(45.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo do site",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "BIBLIOTECA PESSOAL",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(45.dp))

        Image(
            painter = painterResource(id = R.drawable.girl),
            contentDescription = "Imagem de Introdução",
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Crie suas coleções de livros aqui!",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = onStartClick,
            modifier = Modifier
                .width(200.dp) // Define a largura
                .height(55.dp), // Define a altura
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD555)),
            border = BorderStroke(2.dp, Color(0xFFF7B100))
        ) {
            Text(
                text = "INICIAR",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(onStartClick = {})
}
