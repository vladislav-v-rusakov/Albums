package com.vladus177.albums.common

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}