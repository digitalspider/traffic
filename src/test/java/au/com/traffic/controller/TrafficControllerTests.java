/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.com.traffic.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TrafficControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private TrafficController trafficController;

	@Before
	public void setup() {
		trafficController = new TrafficController();
	}

	@Test
	public void noParamAirportsShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/traffic")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, 2!"));
	}

	@Test
	public void paramAirportsShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/airports").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.context").value("Hello, Spring Community!"));
	}

	@Test
	public void testGreetAll() throws Exception {

		this.mockMvc.perform(get("/local"))
				.andDo(print()).andExpect(status().isOk());
		//.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
		// [{"id":1,"content":"Hello, david,test null!"},{"id":2,"content":"Hello, tim,tam null!"},{"id":3,"content":"Hello, gloria,grace null!"}]
	}
}
