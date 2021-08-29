package me.codyq.scpapi

import me.codyq.scpapi.models.SCP
import me.codyq.scpapi.models.SCPImage
import org.koin.dsl.module


interface ScpService {
    suspend fun sayScp(id: String): SCP
}

class ScpServiceImpl(private val scpRepository: ScpRepository) : ScpService {
    override suspend fun sayScp(id: String) = scpRepository.getScp(id)
}

class ScpRepository {
    suspend fun getScp(id: String): SCP {
        return genSCP(id)
    }
}


