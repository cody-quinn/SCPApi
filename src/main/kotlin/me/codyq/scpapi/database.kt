package me.codyq.scpapi

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import org.koin.dsl.module


interface DbService {
    fun getConnection(): StatefulRedisConnection<String, String>
}

class DbServiceImpl(private val uri: String) : DbService {
    override fun getConnection(): StatefulRedisConnection<String, String> {
        val client: RedisClient = RedisClient.create(uri)
        return client.connect()
    }
}

val dbAppModule = module {
    single<DbService> { DbServiceImpl("redis://127.0.0.1:6379") }
}