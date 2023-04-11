package com.example

import com.example.di.koinModule
import com.example.httpClient.httpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import org.junit.After
import org.junit.Before
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

class ApplicationTest {
    @Before
    fun startKoinForTest() {
        startKoin{
            modules(koinModule)
        }
    }

    @Test
    fun requestTest() = testApplication {
        val response = httpClient.get("https://jsonplaceholder.typicode.com/posts/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n}", response.bodyAsText())
    }

    @After
    fun stopKoinAfterTest() = stopKoin()

}
