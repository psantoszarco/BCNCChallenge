package com.inditex.ecommerce.rest_web.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.rest_web.config.TestConfig;
import com.inditex.ecommerce.rest_web.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestConfig
public class PriceControllerTest {
    private static MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @BeforeAll
    public static void setUp(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testReadAllSuccessful() throws Exception {
        Price expectedPrice = Utils.getDefaultPrice();
        given(priceService.fetchPriceList()).willReturn(List.of(expectedPrice));

        mockMvc.perform(get(contextPath + PricesController.PRICES).contextPath(contextPath))
                .andExpect(status().isOk()).andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    List<Price> price = convertJSONListToObject(json, new TypeReference<List<Price>>(){});

                    assertEquals(1, price.size());
                    assertEquals(expectedPrice.getPriceId(), price.get(0).getPriceId());
                    assertEquals(expectedPrice.getBrandId(), price.get(0).getBrandId());
                    assertEquals(expectedPrice.getStartDate(), price.get(0).getStartDate());
                    assertEquals(expectedPrice.getEndDate(), price.get(0).getEndDate());
                    assertEquals(expectedPrice.getPriceList(), price.get(0).getPriceList());
                    assertEquals(expectedPrice.getProductId(), price.get(0).getProductId());
                    assertEquals(expectedPrice.getPriority(), price.get(0).getPriority());
                    assertEquals(expectedPrice.getPrice(), price.get(0).getPrice());
                    assertEquals(expectedPrice.getCurrency(), price.get(0).getCurrency());
                });
    }

    @Test
    void testSaveSuccessful() throws Exception {
        Price expectedPrice = Utils.getDefaultPrice();
        given(priceService.savePrice(expectedPrice)).willReturn(expectedPrice);

        mockMvc.perform(post(contextPath + PricesController.PRICES)
                        .contextPath(contextPath)
                        .contentType(APPLICATION_JSON)
                        .content(convertObjectInJson(expectedPrice)))
                .andExpect(status().isOk()).andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    Price price = convertJSONStringToObject(json, Price.class);

                    assertEquals(expectedPrice.getPriceId(), price.getPriceId());
                    assertEquals(expectedPrice.getBrandId(), price.getBrandId());
                    assertEquals(expectedPrice.getStartDate(), price.getStartDate());
                    assertEquals(expectedPrice.getEndDate(), price.getEndDate());
                    assertEquals(expectedPrice.getPriceList(), price.getPriceList());
                    assertEquals(expectedPrice.getProductId(), price.getProductId());
                    assertEquals(expectedPrice.getPriority(), price.getPriority());
                    assertEquals(expectedPrice.getPrice(), price.getPrice());
                    assertEquals(expectedPrice.getCurrency(), price.getCurrency());
                });
    }

    @Test
    void testUpdateSuccessful() throws Exception {
        Price expectedPrice = Utils.getDefaultPrice();
        given(priceService.updatePrice(expectedPrice, expectedPrice.getPriceId())).willReturn(expectedPrice);

        mockMvc.perform(put(contextPath + PricesController.PRICES + PricesController.ID_MAPPING, expectedPrice.getPriceId())
                        .contextPath(contextPath)
                        .contentType(APPLICATION_JSON)
                        .content(convertObjectInJson(expectedPrice)))
                .andExpect(status().isOk()).andDo(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    Price price = convertJSONStringToObject(json, Price.class);

                    assertEquals(expectedPrice.getPriceId(), price.getPriceId());
                    assertEquals(expectedPrice.getBrandId(), price.getBrandId());
                    assertEquals(expectedPrice.getStartDate(), price.getStartDate());
                    assertEquals(expectedPrice.getEndDate(), price.getEndDate());
                    assertEquals(expectedPrice.getPriceList(), price.getPriceList());
                    assertEquals(expectedPrice.getProductId(), price.getProductId());
                    assertEquals(expectedPrice.getPriority(), price.getPriority());
                    assertEquals(expectedPrice.getPrice(), price.getPrice());
                    assertEquals(expectedPrice.getCurrency(), price.getCurrency());
                });
    }

    @Test
    void testDeleteSuccessful() throws Exception {
        Price expectedPrice = Utils.getDefaultPrice();
        doNothing().when(priceService).deletePriceById(expectedPrice.getPriceId());

        mockMvc.perform(delete(contextPath + PricesController.PRICES + PricesController.ID_MAPPING, expectedPrice.getPriceId())
                        .contextPath(contextPath))
                .andExpect(status().isOk());
    }

    private static <T> T convertJSONStringToObject(String json, Class<T> objectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.readValue(json, objectClass);
    }

    private static <T> List<T> convertJSONListToObject(String json, TypeReference<List<T>> objectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.readValue(json, objectClass);
    }

    private static <T> String convertObjectInJson(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsString(object);
    }

}
