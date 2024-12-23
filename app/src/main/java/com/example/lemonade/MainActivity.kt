package com.example.lemonade

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lemonade() {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val randomNum = remember { mutableIntStateOf(1) }
        var currentImg = R.drawable.lemon_tree
        var currentImgDesc = stringResource(R.string.lemon_tree_content_description)
        var currentText = stringResource(R.string.lemon_tree_text)
        val step = remember { mutableIntStateOf(1) }
        when(step.intValue){
            1 -> {
                currentImg = R.drawable.lemon_tree
                currentImgDesc = stringResource(R.string.lemon_tree_content_description)
                currentText = stringResource(R.string.lemon_tree_text)
            }
            2 -> {
                randomNum.intValue = (1..3).random()
                currentImg = R.drawable.lemon_squeeze
                currentImgDesc = stringResource(R.string.lemon_content_description)
                currentText = stringResource(R.string.lemon_text)
            }
            3 -> {
                currentImg = R.drawable.lemon_drink
                currentImgDesc = stringResource(R.string.glass_of_lemonade_content_description)
                currentText = stringResource(R.string.glass_of_lemonade_text)
            }
            4 -> {
                currentImg = R.drawable.lemon_restart
                currentImgDesc = stringResource(R.string.empty_glass_content_description)
                currentText = stringResource(R.string.empty_glass_text)
            }
            else -> {
                step.intValue = 1
            }
        }
        Image(
            painter = painterResource(currentImg),
            contentDescription = currentImgDesc,
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(Color(105, 216, 185, 100))
                .clickable {
                    Log.d("TAG", "step.intValue = ${step.value}\nrandomNum = ${randomNum.value}")
                    if (step.intValue == 2) {
                        if (randomNum.value != 0) {
                            randomNum.value--
                        } else {
                            step.intValue++
                        }
                    } else {
                        step.intValue++
                    }
                }
        )
        Text(
            text = currentText,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp),
            color = Color.DarkGray
        )
    }
}
