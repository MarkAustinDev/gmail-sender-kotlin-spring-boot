package com.kotlin.mailsender.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebMvcTest(MailController::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MailControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @MockBean
    lateinit var mailController: MailController

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()
    }

    @Test
    fun `Should return a successfull HTTP code when given a valid payload`() {

        val input = """{"name": "Bill Gates","Message": "Hi Mark","email": "Bill.Gates@microsoft.com"}"""

       val result = mockMvc.perform(
            post("/api/email").accept(MediaType.APPLICATION_JSON)
                .content(input)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        assertThat(result.response.status).isEqualTo(200)
    }

    @Test
    fun `Should return an unsuccessful HTTP code when given a valid payload`() {

        val result = mockMvc.perform(
            post("/api/email").accept(MediaType.APPLICATION_JSON)
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        assertThat(result.response.status).isEqualTo(400)
    }
}

