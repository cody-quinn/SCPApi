package me.codyq.scpapi

import me.codyq.scpapi.models.SCP
import me.codyq.scpapi.models.SCPImage
import org.koin.dsl.module


interface ScpService {
    fun sayScp(id: Int): SCP
}

class ScpServiceImpl(private val scpRepository: ScpRepository) : ScpService {
    override fun sayScp(id: Int) = scpRepository.getScp(id)
}

class ScpRepository {
    fun getScp(id: Int): SCP = SCP(
        10,
        id,
        "Euclid",
        "Box",
        "Statue",
        listOf<SCPImage>(SCPImage("url","test"))
    )
}

val scpAppModule = module {
    single<ScpService> { ScpServiceImpl(get()) }
    single { ScpRepository() }
}