package com.ozgursakizli.simplechat.di

import com.ozgursakizli.simplechat.ui.chat.ChatViewModel
import com.ozgursakizli.simplechat.ui.main.MainViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory<CoroutineContext> {
        SupervisorJob()
    }
    factory {
        Dispatchers.IO
    }
    viewModel { MainViewModel() }
    viewModel { ChatViewModel() }
}