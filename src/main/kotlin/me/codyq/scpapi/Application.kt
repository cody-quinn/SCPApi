package me.codyq.scpapi

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.codyq.scpapi.models.SCP
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.slf4jLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@OptIn(ExperimentalSerializationApi::class)
fun Application.main(){
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        json()
    }
    install(Koin) {
        slf4jLogger()
        modules(scpAppModule)
    }

    val scp: ScpService by inject()
    val database: DbService by inject()

    val connection = database.getConnection()

    routing {
        get("/scp/{id}"){
            val id = call.parameters["id"]!!.toString()

            var scpObj: SCP? = null
            val scpDbCheck = connection.sync().get(id)
            if (scpDbCheck.isNullOrBlank()) {
                scpObj = scp.sayScp(id)
                connection.sync().set(id, Json.encodeToString(scpObj))
                connection.sync().expire(id, 21_600)
            } else {
                scpObj = Json.decodeFromString<SCP>(scpDbCheck)
            }

            val scpJsonStr = Json.encodeToString(scpObj)
            call.respond(scpJsonStr)
        }
    }


}

val scpAppModule = module {
    single<ScpService> { ScpServiceImpl(get()) }
    single { ScpRepository() }
    single<DbService> { DbServiceImpl("redis://127.0.0.1:6379") }
}