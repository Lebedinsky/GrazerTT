package com.romkaleb.grazertt

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.presentation.use_case.IGetUserListUseCase
import com.romkaleb.grazertt.presentation.view_models.UserListScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class UserListScreenViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch users test`() = runTest {
        val getUserListUseCase = mock(IGetUserListUseCase::class.java)
        val users = listOf(User(name = "Roman Lebedinsky", dateOfBirth = 123456789, profileImageUrl = "Photo_Url"))
        `when`(getUserListUseCase.getUserList()).thenReturn(Result.success(users))

        val userViewModel = UserListScreenViewModel(getUserListUseCase)

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            userViewModel.uiState.collect {}
        }
        userViewModel.loadUserList()

        val result = userViewModel.uiState.value.userList
        assert(result == users)
        collectJob.cancel()
    }
}