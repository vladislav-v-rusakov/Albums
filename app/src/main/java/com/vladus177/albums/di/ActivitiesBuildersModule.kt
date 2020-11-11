package com.vladus177.albums.di

import com.vladus177.albums.ui.activity.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeUserListActivity(): UserListActivity
}