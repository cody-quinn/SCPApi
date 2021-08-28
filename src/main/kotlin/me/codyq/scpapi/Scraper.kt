package me.codyq.scpapi

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.serializer
import nl.adaptivity.xmlutil.serialization.XML

suspend fun getXml(id: Int){
    val client = HttpClient(CIO)
    val response: HttpResponse = client.request("https://scp-wiki.wikidot.com/scp-$id")
    val data: String =  response.readText().trim()
//    what the fuck do i do
    val xmlData = null
    client.close()

}
