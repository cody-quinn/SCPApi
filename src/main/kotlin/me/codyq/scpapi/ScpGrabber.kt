package me.codyq.scpapi

import me.codyq.scpapi.models.SCP
import me.codyq.scpapi.models.SCPImage
import org.koin.dsl.module


interface ScpService {
    suspend fun sayScp(id: Int): SCP
}

class ScpServiceImpl(private val scpRepository: ScpRepository) : ScpService {
    override suspend fun sayScp(id: Int) = scpRepository.getScp(id)
}

class ScpRepository {
    suspend fun getScp(id: Int): SCP {
        println(getXml(id))
        return SCP(
            10,
            id,
            "Euclid",
            "Box",
            "Statue",
            listOf<SCPImage>(SCPImage("url","test"))
        )
    }
}


