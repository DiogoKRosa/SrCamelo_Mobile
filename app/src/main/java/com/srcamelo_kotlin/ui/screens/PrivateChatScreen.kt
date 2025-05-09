package com.srcamelo_kotlin.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.Id
import com.srcamelo_kotlin.model.MessageModel
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CardText
import com.srcamelo_kotlin.ui.components.MessageText
import com.srcamelo_kotlin.ui.components.NumberText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Gray
import com.srcamelo_kotlin.ui.theme.LightGray
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White
import com.srcamelo_kotlin.ui.viewModel.ChatViewModel
import com.srcamelo_kotlin.ui.viewModel.UiState
import kotlinx.coroutines.delay
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrivateChatScreen(
    uid: String? = "",
    onClickBack: () -> Unit = {},
    dataStoreManager: DataStoreManager,
    chatViewModel: ChatViewModel = hiltViewModel()
){
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val message = chatViewModel.message.value
    val loginId by dataStoreManager.getUserId().collectAsState("")
    val chat by chatViewModel.privateChatMessages.observeAsState(emptyList())
    val chatState by chatViewModel.chatUiState.observeAsState(UiState.Loading)

    LaunchedEffect(loginId){
        while (true){
            if(!uid.isNullOrEmpty()){
                chatViewModel.GetAllMessagesFromPrivateChat(loginId = loginId, userId = uid)
            }
            delay(5000)
        }
    }

    when(chatState){
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                val errorMessage = (chatState as UiState.Error).message
                CardText(text = errorMessage)
            }
        }
        is UiState.Success -> {
            Scaffold(
                topBar = { BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Conversa") },
                bottomBar = {
                    Box(modifier = Modifier.background(color = DarkOrange, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)).fillMaxWidth()
                        .padding(bottom = bottomPadding, top=14.dp, start = 6.dp)){
                        Row(modifier = Modifier.fillMaxWidth()){
                            MessageInput(modifier = Modifier.weight(1f),value = message.text,
                                onValueChange = {it -> chatViewModel.setMessage(it)})
                            IconButton(
                                onClick={
                                    if(!uid.isNullOrEmpty()){
                                        chatViewModel.SendMessage(
                                            MessageModel(
                                                oid = Id(loginId),
                                                participants = mutableListOf(loginId, uid),
                                                sender = loginId,
                                                receiver = uid,
                                                message = message.text,
                                            )
                                        )
                                    }
                                }
                            ) {
                                Icon(painter = painterResource(R.drawable.paper_plane_icon),
                                    contentDescription = "Enviar")
                            }
                        }
                    }

                },
                containerColor = LightOrange,
                contentColor = LightOrange
            ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp)
                    .padding(top = 20.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom) {
                    items(chat){ message ->
                        val datetime = OffsetDateTime.parse(message.time, DateTimeFormatter.ISO_DATE_TIME)
                            .format(DateTimeFormatter.ofPattern("HH:mm"))
                        if(message.sender == loginId){
                            Box(modifier = Modifier.fillMaxWidth()){
                                SenderBalloonChat(message = message.message, hour = datetime, modifier = Modifier.align(Alignment.CenterEnd))
                            }
                        }else{
                            ReceiverBalloonChat(message= message.message, hour = datetime)
                        }
                        Spacer(modifier = Modifier.height(27.dp))
                    }
                }
            }
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
private fun PrivateChatScreenPreview(){
    PrivateChatScreen()
}
*/