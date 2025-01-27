package com.romkaleb.grazertt.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.romkaleb.grazertt.R
import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.presentation.view_models.UserListScreenViewModel
import com.romkaleb.grazertt.ui.theme.GrazerTTTheme
import com.romkaleb.grazertt.util.Constants

@Composable
fun UserListScreen(viewModel: UserListScreenViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val userList = uiState.value.userList
    val isLoading = uiState.value.isLoading
    val errorMessage = uiState.value.errorMessage

    GrazerTTTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ScreenStateViews(
                isLoading = isLoading,
                errorMessage = errorMessage
            )
            UserList(userList)
        }
    }
}

@Composable
fun ScreenStateViews(
    isLoading: Boolean,
    errorMessage: String
) {
    Box {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.tertiary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            errorMessage.isNotBlank() -> {
                Text(
                    text = errorMessage,
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun UserList(userList: List<User>) {
    LazyColumn {
        itemsIndexed(userList) { index, item ->
            UserItem(user = item)
            if (index < userList.lastIndex) {
                Divider(thickness = 1.dp)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(80.dp)
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        AsyncImage(
            model = user.profileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = user.name)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = user.getAge().toString())
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    GrazerTTTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.tertiary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            UserList(Constants.stubUserList)
        }
    }
}

@Preview
@Composable
fun ScreenStateViewsPreview() {
    GrazerTTTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ScreenStateViews(
                isLoading = false,
                errorMessage = "Some error message Some error message Some error message"
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun UserItemPreview(
    user: User = User(
        "Roman Lebedinsky",
        732326400,
        "https://this-person-does-not-exist.com/img/avatar-ebf5a943068fa6dce23a201289133f68.jpg"
    )
) {
    GrazerTTTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            UserItem(user = user)
        }
    }
}
