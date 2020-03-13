package br.com.nms.calc;

import br.com.nms.calc.model.Operation;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DivideOperationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void beforeEachScenario() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.defaultRequest(MockMvcRequestBuilders.get("/"))
				.build();
	}


	@Test
	void multiplicationWithTwoInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(10), new BigDecimal(2));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/divide")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(5)));
	}

	@Test
	void multiplicationWithNegativeInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(-4), new BigDecimal(2));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/divide")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(-2)));
	}

	@Test
	void multiplicationWithTwoNegativeInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(-4), new BigDecimal(-5));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/divide")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(0.8)));
	}

	@Test
	void multiplicationWithZero() throws Exception {
		Operation operation = new Operation(new BigDecimal(0), new BigDecimal(3));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/divide")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(0)));
	}

	@Test
	void multiplicationWithZeroInSecondNumber() throws Exception {
		Operation operation = new Operation(new BigDecimal(3), new BigDecimal(0));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/divide")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isInternalServerError());
	}

}
