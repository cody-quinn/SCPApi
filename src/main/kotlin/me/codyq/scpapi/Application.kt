package me.codyq.scpapi

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.buildJsonArray
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.slf4jLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

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

    routing {
        get("/scp/{id}"){

            val id = call.parameters["id"]!!.toString()
            call.respond(scp.sayScp(id))


        }
    }


}

val scpAppModule = module {
    single<ScpService> { ScpServiceImpl(get()) }
    single { ScpRepository() }
    single<DbService> { DbServiceImpl("redis://127.0.0.1:6379") }
}