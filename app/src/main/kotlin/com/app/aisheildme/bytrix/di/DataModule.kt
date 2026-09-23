package com.app.aisheildme.bytrix.di

import android.content.Context
import androidx.room.Room
import com.app.aisheildme.bytrix.data.local.db.AppDatabase
import com.app.aisheildme.bytrix.data.local.db.CheckInHistoryDao
import com.app.aisheildme.bytrix.data.remote.api.DocumentApi
import com.app.aisheildme.bytrix.data.remote.api.MiscApi
import com.app.aisheildme.bytrix.data.remote.api.NotificationApi
import com.app.aisheildme.bytrix.data.remote.api.PagesApi
import com.app.aisheildme.bytrix.data.remote.api.PatientProfileApi
import com.app.aisheildme.bytrix.data.remote.api.RecordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDocumentApi(retrofit: Retrofit): DocumentApi =
        retrofit.create(DocumentApi::class.java)

    @Provides
    @Singleton
    fun provideRecordApi(retrofit: Retrofit): RecordApi =
        retrofit.create(RecordApi::class.java)

    @Provides
    @Singleton
    fun provideNotificationApi(retrofit: Retrofit): NotificationApi =
        retrofit.create(NotificationApi::class.java)

    @Provides
    @Singleton
    fun providePagesApi(retrofit: Retrofit): PagesApi =
        retrofit.create(PagesApi::class.java)

    @Provides
    @Singleton
    fun provideMiscApi(retrofit: Retrofit): MiscApi =
        retrofit.create(MiscApi::class.java)

    @Provides
    @Singleton
    fun providePatientProfileApi(retrofit: Retrofit): PatientProfileApi =
        retrofit.create(PatientProfileApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .build()

    @Provides
    @Singleton
    fun provideCheckInHistoryDao(db: AppDatabase): CheckInHistoryDao = db.checkInHistoryDao()
}
