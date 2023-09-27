package com.plcoding.daggerhiltcourse.domain.repository

import com.plcoding.daggerhiltcourse.data.datamodel.ImgFlipResponse

interface MyRepository {
    suspend fun doNetworkCall(): ImgFlipResponse
}