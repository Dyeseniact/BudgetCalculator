package bedu.org.budget_calculator.controller;


import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConceptControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    final LocalDate endDate = LocalDate.of(2024,8,17);
    final LocalDate startDate = LocalDate.of(2024,6,9);

    @Test
    @DisplayName("GET /concept/{id} should return an error if the concept is not found")
    void ConceptNotFoundTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/concepts/" + 5333333L))
                .andExpect(status().is5xxServerError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_DATA_NOT_FOUND\",\"message\":\"No se encontrÃ³ el concepto especificado\",\"details\":5333333}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /concept should return an error if description is missing")
    void nameMissingInRequestBodyTest() throws Exception {
        mockMvc.perform(post("/concepts").contentType("application/json").content("{\"unitprice\":\"500\", \"subtotal\" :\"2000\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

   @Test
   @DisplayName("PATCH /concept/{id} should return an error if unit is empty")
   void updateConceptUnitEmptyTest() throws Exception {
       ConceptDTO updatedConcept = new ConceptDTO();
       updatedConcept.setDescription("Test name");
       updatedConcept.setQuantity(3);

       mockMvc.perform(patch("/concepts/" + 20L).contentType("application/json").content(objectMapper.writeValueAsString(updatedConcept)))
               .andExpect(status().isInternalServerError());
   }


    @Test
    @DisplayName("DELETE /concepts/{id} should return an error if the concept is not found")
    void deleteConceptNotFoundTest() throws Exception {
        MvcResult result = mockMvc.perform(delete("/concepts/" + 9999))
                .andExpect(status().is5xxServerError())
                .andReturn();

        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = "{\"code\":\"ERR_DATA_NOT_FOUND\",\"message\":\"No se encontró el concepto especificado\",\"details\":9999}";

        assertEquals(expectedResponse, content);
    }


}
