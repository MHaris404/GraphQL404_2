package com.mharis.graphql4042.data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mharis.graphql4042.data.repository.CountryRepoImpl
import com.mharis.graphql4042.domain.repository.CountryRepo
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApolloClient():ApolloClient{
        val okHttpClient = OkHttpClient.Builder().build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
        return apolloClient
    }

    @Provides
    fun provideCountryRepo(apolloClient: ApolloClient):CountryRepo{
        return CountryRepoImpl(apolloClient)
    }


}