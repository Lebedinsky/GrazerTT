package com.romkaleb.grazertt.presentation.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.romkaleb.grazertt.presentation.activity.UserListActivity
import com.romkaleb.grazertt.presentation.view_models.AuthViewModel
import com.romkaleb.grazertt.ui.theme.GrazerTTTheme

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.value.isSignedIn) {
        val context = LocalContext.current
        context.startActivity(Intent(context, UserListActivity::class.java))
        (context as Activity).finish()
    }
    val email = uiState.value.email
    val password = uiState.value.password
    val isLoading = uiState.value.isLoading

    AuthForm(
        email = email,
        password = password,
        isLoading = isLoading,
        onEmailChanged = { viewModel.setEmail(it.trim()) },
        onPasswordChanged = { viewModel.setPassword(it.trim()) },
        onLoginClicked = { viewModel.onLoginClicked() }
    )
}

@Composable
fun AuthForm(
    email: String,
    password: String,
    isLoading: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    GrazerTTTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            if (isLoading) {
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(64.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.tertiary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Welcome my friend!",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(30.dp)
            ) {
                EmailTextField(
                    value = email,
                    onChange = { onEmailChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("EmailTextField")
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextField(
                    value = password,
                    onChange = { onPasswordChanged(it) },
                    onDoneClicked = {
                        if (isCredentialsValid(email, password)) {
                            onLoginClicked()
                        } else {
                            focusManager.clearFocus()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("PasswordTextField")
                )
                Spacer(modifier = Modifier.height(30.dp))
                LoginButton(
                    onClick = { onLoginClicked() },
                    isEnabled = isCredentialsValid(email, password),
                    modifier = Modifier.testTag("LoginButton")
                )
            }
        }
    }
}

@Composable
fun EmailTextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    placeholder: String = "Your email please"
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) }
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onChange: (String) -> Unit,
    onDoneClicked: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Your password please"
) {
    var isPassHidden by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = ""
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPassHidden = !isPassHidden },
                content = {
                    Icon(
                        imageVector = if (isPassHidden) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = ""
                    )
                },
            )
        },
        visualTransformation = if (isPassHidden) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDoneClicked() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) }
    )
}

@Composable
fun LoginButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
    modifier: Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
    ) {
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 50.dp, vertical = 5.dp)
        )

    }
}

fun isEmailValid(email: String) = email.isNotBlank()

fun isPasswordValid(password: String) = password.isNotBlank()

fun isCredentialsValid(email: String, password: String): Boolean {
    return isEmailValid(email) && isPasswordValid(password)
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthForm(
        email = "romkaleb@gmail.com",
        password = "1234567890",
        isLoading = false,
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    GrazerTTTheme {
        EmailTextField(value = "romkaleb@gmail.com", onChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    GrazerTTTheme {
        PasswordTextField(value = "", onChange = {}, onDoneClicked = {})
    }
}
