package com.example.gitnuts.ui.userlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.gitnuts.data.model.User
import com.example.gitnuts.data.repository.UserRepository
import com.example.gitnuts.ui.userlist.model.UserItemMapper
import com.example.gitnuts.ui.userlist.model.UserItemUIModel
import com.example.gitnuts.utils.ResultAPI
import com.example.gitnuts.utils.UIState
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class  UserListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var vm: UserListViewModel
    @Mock
    private lateinit var userRepository: UserRepository
    private val mapper = UserItemMapper()

    @Mock
    private lateinit var userListResponseObserver: Observer<UIState<List<UserItemUIModel>>>

    @Captor
    private lateinit var captorUserList: ArgumentCaptor<UIState<List<UserItemUIModel>>>

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun getUserListSuccess() {
        runBlocking {
            val stubUserList = arrayListOf(User(id = 123))
            whenever(userRepository.getUsers()).thenReturn(ResultAPI.OnSuccess(stubUserList))
            vm = UserListViewModel(userRepository, mapper)
            vm.userListResponse.observeForever(userListResponseObserver)
            captorUserList.run {
                verify(userListResponseObserver, times(1)).onChanged(capture())
                assert(value is UIState.Success)
                assert((value as UIState.Success).data[0].id == stubUserList[0].id)
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}