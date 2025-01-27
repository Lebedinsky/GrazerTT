package com.romkaleb.grazertt

import com.romkaleb.grazertt.data.model.User
import com.romkaleb.grazertt.domain.use_case.GetUserListUseCaseImpl
import com.romkaleb.grazertt.presentation.view_models.UserListScreenViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserListScreenViewModelTest {

    @Test
    fun `fetch users and update LiveData`() = runTest {
        val getUserListUseCase = mock(GetUserListUseCaseImpl::class.java)
        val users = listOf(User(name = "Roman Lebedinsky", dateOfBirth = 123456789, profileImageUrl = "Photo_Url"))
        `when`(getUserListUseCase.getUserList()).thenReturn(Result.success(users))

        val userViewModel = UserListScreenViewModel(getUserListUseCase)

        userViewModel.loadUserList()

        val result = userViewModel.uiState.value.userList
        assert(result == users)
    }
}