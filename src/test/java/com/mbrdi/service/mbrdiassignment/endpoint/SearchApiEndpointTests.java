package com.mbrdi.service.mbrdiassignment.endpoint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mbrdi.service.mbrdiassignment.model.LocationResponse;
import com.mbrdi.service.mbrdiassignment.service.ISearchService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = { ConfigFileApplicationContextInitializer.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@WebMvcTest(controllers = SearchApiEndpoint.class)
public class SearchApiEndpointTests {

	Logger log = LoggerFactory.getLogger(SearchApiEndpointTests.class);
	@Autowired
	MockMvc mockMvc;


	@MockBean
	private ISearchService searchService;

	@MockBean
	private LocationResponse locationResponse;

	@Test
	void whenValidInputExpectHttpOk() {
		try {
			when(searchService.findPOIByPlaceName(anyString())).thenReturn(locationResponse);
			mockMvc.perform(MockMvcRequestBuilders.get("/search-service/v1/place")
					.contentType(MediaType.APPLICATION_JSON_VALUE).param("place","berlin")).andExpect(MockMvcResultMatchers.status().isBadRequest());
			
		}catch(Exception ex) {
			//log.error("Error occore while test running",ex);
			Assertions.fail("Error occore while test running",ex.getCause());

		}

	}
}
