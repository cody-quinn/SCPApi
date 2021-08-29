package me.codyq.scpapi

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection

interface DbService {
    fun getConnection(): StatefulRedisConnection<String, String>
}

class DbServiceImpl(private val uri: String) : DbService {
    private val client: RedisClient = RedisClient.create(uri)
    private val connectionPool: StatefulRedisConnection<String, String> = client.connect()

    override fun getConnection(): StatefulRedisConnection<String, String> {
        return connectionPool
    }
}
