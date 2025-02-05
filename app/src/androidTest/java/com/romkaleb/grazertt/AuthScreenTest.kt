package com.romkaleb.grazertt

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.romkaleb.grazertt.presentation.activity.AuthActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AuthScreenTest {

    @get:Rule(order = 1) val hiltTestRule = HiltAndroidRule(this)
    @get:Rule(order = 2) val composeTestRule = createAndroidComposeRule<AuthActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun whenEmailAndPasswordEnteredLoginButtonEnabledTest() {
        composeTestRule.onNodeWithTag("LoginButton").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("EmailTextField").performTextInput("romkaleb@gmail.com")
        composeTestRule.onNodeWithTag("PasswordTextField").performTextInput("1234567890")
        composeTestRule.onNodeWithTag("LoginButton").assertIsEnabled()
    }
}

