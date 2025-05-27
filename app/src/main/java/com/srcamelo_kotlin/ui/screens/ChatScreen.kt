package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CardText
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.CustomVendorBottomBar
import com.srcamelo_kotlin.ui.components.RegularBlackSubTitle
import com.srcamelo_kotlin.ui.theme.LightGray
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.viewModel.ChatViewModel
import com.srcamelo_kotlin.ui.viewModel.UiState
import kotlinx.coroutines.delay


@Composable
fun UserChatRow(
    modifier: Modifier = Modifier,
    onClickChat: () -> Unit = {},
    urlImage: String = "",
    userName: String = "UserName",
    lastMessagePreview: String = "Last Message Preview"
){
    Row(modifier = modifier.fillMaxWidth().height(85.dp).padding(start = 20.dp).padding(vertical = 20.dp)
        .clickable { onClickChat() }){
        AsyncImage(
            model = urlImage,
            contentDescription = userName,
            placeholder = painterResource(R.drawable.placeholder_logo_black),
            error = painterResource(R.drawable.placeholder_logo_black),
            modifier = Modifier.size(45.dp).clip(CircleShape)
        )
        Column(modifier= Modifier.padding(start = 10.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(3.dp)){
            CardText(text = userName, size = 14)
            RegularBlackSubTitle(text = lastMessagePreview, size = 14)
        }
    }
}

@Composable
fun ChatScreen(
    onClickBack: () -> Unit = {},
    onClickHome: () -> Unit = {},
    onClickCart: () -> Unit = {},
    onClickBalloon: () -> Unit = {},
    onClickBar: () -> Unit = {},
    navController: NavController,
    dataStoreManager: DataStoreManager,
    chatViewModel: ChatViewModel = hiltViewModel()
){
    val loginId by dataStoreManager.getUserId().collectAsState("")
    val chat by chatViewModel.chatList.observeAsState(emptyList())
    val chatState by chatViewModel.chatUiState.observeAsState(UiState.Loading)

    LaunchedEffect(loginId){
        while (true){
            chatViewModel.GetAllLastMessage(loginId)
            delay(5000)
        }
    }

    Scaffold(
        topBar = { BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Conversas")},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                CustomVendorBottomBar(
                    homeClick = onClickHome,
                    cartClick = onClickCart,
                    balloonClick = onClickBalloon,
                    barClick = onClickBar
                )
            }
        },
        containerColor = LightOrange,
        contentColor = LightOrange
    ) { innerPadding ->
        when(chatState){
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    items(chat){ item ->
                        val otherParticipant = item.participantDetails.firstOrNull { it.userId != loginId }
                        UserChatRow(
                            onClickChat = {
                                navController.navigate(
                                    SrCameloScreens.PrivateChat.name + "/${otherParticipant?.userId ?: ""}"
                                )
                            },
                            userName = otherParticipant?.userName ?: "Desconhecido",
                            urlImage = "${BuildConfig.BASE_URL}${otherParticipant?.image ?: ""}",
                            lastMessagePreview = item.message
                        )
                        Spacer(modifier = Modifier.width(365.dp).height(1.dp).border(1.dp, LightGray))
                    }
                }
            }
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    val errorMessage = (chatState as UiState.Error).message
                    CardText(text = errorMessage)
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
private fun ChatScreenPreview(){
    //ChatScreen()
}