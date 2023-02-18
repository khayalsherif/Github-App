package com.example.android.codelabs.paging.di

import androidx.room.Room
import com.example.android.codelabs.paging.BuildConfig
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.data.local.GithubDataBase
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDataSource
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDataSourceImpl
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDataSource
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDataSourceImpl
import com.example.android.codelabs.paging.data.remote.github.GithubService
import com.example.android.codelabs.paging.domain.GithubRepositoryImpl
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

val dataModule = module {

    single<CoroutineContext>(named(IO_CONTEXT)) { Dispatchers.IO }

    /////////////////////////////////////////////// REMOTE //////////////////////////////////////////////

    single {
        val client = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logger =
                HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logger)
        }
        client.build()
    }

    factory<GithubService> { get<Retrofit>().create(GithubService::class.java) }

    single<Moshi> {
        Moshi.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(getProperty<String>("base"))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    /////////////////////////////////////////////// LOCAL //////////////////////////////////////////////

    single(named("key_dao")) { get<GithubDataBase>().remoteKeysDao() }
    single(named("repo_dao")) { get<GithubDataBase>().gitHubRepoDao() }

    single<GithubRepoLocalDataSource> {
        GithubRepoLocalDataSourceImpl(githubRepoDao = get(named("repo_dao")))
    }

    single<RemoteKeysLocalDataSource> {
        RemoteKeysLocalDataSourceImpl(remoteKeysDao = get(named("key_dao")))
    }

    single {
        Room.databaseBuilder(
            get(),
            GithubDataBase::class.java,
            "app-database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    /////////////////////////////////////////////// REPOSITORY //////////////////////////////////////////////

    factory<GithubRepository> {
        GithubRepositoryImpl(
            githubRepoDataSource = get(),
            remoteKeysDataSource = get(),
            remoteService = get()
        )
    }

    /////////////////////////////// REMOTE ERROR MAP ///////////////////////////////////////////////

    // factory<ErrorMapper>(named(ERROR_MAPPER_NETWORK)) { RemoteErrorMapper() }

}