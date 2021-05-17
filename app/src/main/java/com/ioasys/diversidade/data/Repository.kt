package com.ioasys.diversidade.data

import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource

}