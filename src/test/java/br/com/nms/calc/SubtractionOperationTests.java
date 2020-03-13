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
class SubtractionOperationTests {

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
	void subtractionWithTwoInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(5), new BigDecimal(2));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/subtract")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(3)));
	}

	@Test
	void subtractWithNegativeInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(-4), new BigDecimal(2));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(-2)));
	}

	@Test
	void subtractWithTwoNegativeInteger() throws Exception {
		Operation operation = new Operation(new BigDecimal(-4), new BigDecimal(-5));
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/calc/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new Gson().toJson(operation)));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.result", is(-9)));
	}

}
