/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.servingwebcontent;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.web.client.TestRestTemplate;


//@WebMvcTest(controllers = GreetingController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServingWebContentApplicationTest {

	// @Autowired
	// private MockMvc mockMvc;

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	// @Test
	// public void homePage() throws Exception {
	// 	// N.B. jsoup can be useful for asserting HTML content
	// 	// mockMvc.perform(get("/index.html"))
	// 	// 		.andExpect(content().string(containsString("Simple contents")));
	// 	assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/api/greeting", String.class).contains("Simple contents"), "response expected to contain 'Simple contents' received: "+this.restTemplate.getForObject("http://localhost:"+port+"/hello?name=Tanzu", String.class));
	// }

	@Test
	public void greeting() throws Exception {
		// mockMvc.perform(get("/greeting"))
		// 		.andExpect(content().string(containsString("Hello, World!")));
		assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/api/greeting", String.class).contains("greeting_text"), "response expected to contain 'greeting_text' received: "+this.restTemplate.getForObject("http://localhost:"+port+"/api/greeting", String.class));
	}

	@Test
	public void greetingWithUser() throws Exception {
		assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/api/greeting/Ali", String.class).contains("Ali"), "response expected to contain 'Ali' received: "+this.restTemplate.getForObject("http://localhost:"+port+"/api/greeting/Ali", String.class));
	}

}
