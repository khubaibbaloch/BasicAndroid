package com.BasicAndroid.basicandroid.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMyDataSource(): MyDataSource {
        return MyDataSource()
    }

    @Provides
    @Singleton
    fun provideMyRepository(myDataSource: MyDataSource): MyRepository {
        return MyRepository(myDataSource)
    }
}