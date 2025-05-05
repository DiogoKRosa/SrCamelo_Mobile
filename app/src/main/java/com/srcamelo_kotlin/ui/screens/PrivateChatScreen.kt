package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.MessageText
import com.srcamelo_kotlin.ui.components.NumberText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Gray
import com.srcamelo_kotlin.ui.theme.LightGray
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun MessageInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {}
){
    var text by remember { mutableStateOf(TextFieldValue(value)) }

    Box(
        modifier = modifier.height(45.dp).clip(RoundedCornerShape(28.dp)).background(White)
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            ),
            modifier = Modifier.align(Alignment.CenterStart)
        ){ innerTextField ->
            Box(modifier = Modifier.fillMaxSize().padding(start = 14.dp),
                contentAlignment = Alignment.CenterStart){
                if(value.isEmpty()){
                    Text("Digite uma mensagem...",
                        style = TextStyle(
                            color = Color(0x65333333),
                            fontSize = 14.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
                innerTextField()
            }

        }
    }
}

@Composable
fun SenderBalloonChat(
    modifier: Modifier = Modifier,
    message: String,
    hour:String
){
    Column(modifier = modifier){
        Card(
            shape = RoundedCornerShape(15.dp),
            colors = CardColors(containerColor = Color(0x44FF6700), contentColor = Gray,
                disabledContentColor = LightGray, disabledContainerColor = LightOrange),
            modifier = Modifier.widthIn(max = 250.dp)
        ) {
            MessageText(text = message, modifier = Modifier.padding(10.dp))
        }
        NumberText(text = hour, size = 10,
            modifier = Modifier.align(Alignment.End).padding(end = 13.dp))
    }


}

@Composable
fun ReceiverBalloonChat(
    modifier: Modifier = Modifier,
    message: String,
    hour: String
){
    Column{
        Card(
            shape = RoundedCornerShape(15.dp),
            colors = CardColors(containerColor = LightGray, contentColor = Gray,
                disabledContentColor = LightGray, disabledContainerColor = LightOrange),
            modifier = Modifier.widthIn(max = 250.dp)
        ) {
            MessageText(text = message, modifier = Modifier.padding(10.dp))
        }
        NumberText(text = hour, size = 10,
            modifier = Modifier.align(Alignment.End).padding(end = 13.dp))
    }


}

@Composable
fun PrivateChatScreen(
    uid: String? = "",
    onClickBack: () -> Unit = {},
    //messageViewModel: ChatViewModel = hiltViewModel()
){
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    //val message = messageViewModel.message.value
    var message by remember {mutableStateOf("")}

    Scaffold(
        topBar = { BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Suporte") },
        bottomBar = {
            Box(modifier = Modifier.background(color = DarkOrange, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)).fillMaxWidth()
                .padding(bottom = bottomPadding, top=14.dp, start = 6.dp)){
                Row(modifier = Modifier.fillMaxWidth()){
                    MessageInput(modifier = Modifier.weight(1f),value = message.toString(),
                        onValueChange = {it -> message = it/*messageViewModel.setMessage(it)*/})
                    IconButton(
                        onClick={}
                    ) { Icon(painter = painterResource(R.drawable.paper_plane_icon), contentDescription = "Enviar") }
                }
            }

        },
        containerColor = LightOrange,
        contentColor = LightOrange
    ) { innerPadding ->


        Column (modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp)
            .padding(top = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Bottom){
            ReceiverBalloonChat(message= "Olá como podemos ajudar?", hour = "16:32")
            Spacer(modifier = Modifier.height(27.dp))
            SenderBalloonChat(message = "Olá, estou com dificuldade para encontrar as minhas vendas",
                hour = "16:32", modifier = Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.height(27.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PrivateChatScreenPreview(){
    PrivateChatScreen()
}