package me.codyq.scpapi

import me.codyq.scpapi.models.SCP



interface ScpService {
    suspend fun sayScp(id: String): SCP
}

class ScpServiceImpl(private val scpRepository: ScpRepository) : ScpService {
    override suspend fun sayScp(id: String) = scpRepository.getScp(id)
}

class ScpRepository {
    fun getScp(id: String): SCP {
        return genSCP(id)
    }
}


