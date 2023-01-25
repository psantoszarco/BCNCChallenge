package com.inditex.ecommerce.integrationTest;

import com.inditex.ecommerce.domain.model.entity.Price;
import com.inditex.ecommerce.domain.repository.PriceRepository;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init/price-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class PriceControllerIT {
    @Autowired
    private PriceRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_create_one_price() throws Exception {
        final File jsonFile = new ClassPathResource("data/price.json").getFile();
        final String priceToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/prices")
                        .contentType(APPLICATION_JSON)
                        .content(priceToCreate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(9)));

        assertThat(this.repository.findAll()).hasSize(5);
    }

    @Test
    void should_retrieve_all_prices() throws Exception {
        this.mockMvc.perform(get("/prices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[0].priceId").value(95))
                .andExpect(jsonPath("$.[1].priceId").value(96))
                .andExpect(jsonPath("$.[2].priceId").value(97))
                .andExpect(jsonPath("$.[3].priceId").value(98));
    }

    @Test
    void should_update_a_price_by_id() throws Exception {
        final File jsonFile = new ClassPathResource("data/price.json").getFile();
        final String priceToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(put("/prices/95")
                    .contentType(APPLICATION_JSON)
                    .content(priceToUpdate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(9)));

        Iterable<Price> priceList = this.repository.findAll();
        assertThat(priceList).hasSize(4);

        priceList.forEach(el -> {
            if (el.getPriceId() == 95) {
                assertEquals(2L, el.getBrandId());
                assertNotNull(el.getStartDate());
                assertNotNull(el.getEndDate());
                assertEquals(3L, el.getPriceList());
                assertEquals(19L, el.getProductId());
                assertEquals(5, el.getPriority());
                assertEquals(55.55D, el.getPrice(), 0);
                assertEquals("DOL", el.getCurrency());
            } else {
                assertNotEquals("DOL", el.getCurrency());
            }
        });
    }

    @Test
    void should_update_non_existent_user_by_id() throws Exception {
        final File jsonFile = new ClassPathResource("data/price.json").getFile();
        final String priceToUpdate = Files.readString(jsonFile.toPath());

        ServletException exception = assertThrows(
                ServletException.class,
                () -> { this.mockMvc.perform(put("/prices/6")
                                .contentType(APPLICATION_JSON)
                                .content(priceToUpdate))
                        .andDo(print());
                }
        );

        assertEquals("Request processing failed: com.inditex.ecommerce.domain.exceptions.PriceNotFoundException: The price with id 6 is not found", exception.getMessage());
    }

    @Test
    void should_delete_one_price() throws Exception {
        this.mockMvc.perform(delete("/prices/{id}", 95))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(this.repository.findAll()).hasSize(3);
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_10_00() throws Exception {
        final File jsonFile = new ClassPathResource("data/search_14_06_10_00.json").getFile();
        final String searchPerformed = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(get("/getPrices")
                    .contentType(APPLICATION_JSON)
                    .content(searchPerformed))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.priceId").value(95))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(0))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_18_00() throws Exception {
        final File jsonFile = new ClassPathResource("data/search_14_06_18_00.json").getFile();
        final String searchPerformed = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(get("/getPrices")
                        .contentType(APPLICATION_JSON)
                        .content(searchPerformed))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.priceId").value(96))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_21_00() throws Exception {
        final File jsonFile = new ClassPathResource("data/search_14_06_21_00.json").getFile();
        final String searchPerformed = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(get("/getPrices")
                        .contentType(APPLICATION_JSON)
                        .content(searchPerformed))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.priceId").value(95))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(0))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void should_retrieve_a_price_in_15_06_at_10_00() throws Exception {
        final File jsonFile = new ClassPathResource("data/search_15_06_10_00.json").getFile();
        final String searchPerformed = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(get("/getPrices")
                        .contentType(APPLICATION_JSON)
                        .content(searchPerformed))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.priceId").value(97))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(30.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void should_retrieve_a_price_in_16_06_at_21_00() throws Exception {
        final File jsonFile = new ClassPathResource("data/search_16_06_21_00.json").getFile();
        final String searchPerformed = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(get("/getPrices")
                        .contentType(APPLICATION_JSON)
                        .content(searchPerformed))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.priceId").value(98))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
}
