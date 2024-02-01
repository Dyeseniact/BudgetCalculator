package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.material.MaterialDTO;
import bedu.org.BudgetCalculator.model.Material;
import bedu.org.BudgetCalculator.repository.MaterialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MaterialControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaterialRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("GET /material/{id} should return an error if the material is not found")
    void materialNotFoundTest() throws Exception {


        MvcResult result = mockMvc.perform(get("/material/" + 5333333L))
                .andExpect(status().isNotFound())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_DATA_NOT_FOUND\",\"message\":\"Material not found with ID:\",\"details\":5333333}";

        assertEquals(expectedResponse, content);
    }
    @Test
    @DisplayName("POST /material should return an error if name is missing")
    void nameMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/material").contentType("application/json").content("{\"quantity\":15, \"price\":40.0}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();


        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"A error ocurred procesando input data\",\"details\":[\"Name cannot be empty\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /material should return an error if quantity is missing")
    void quantityMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/material").contentType("application/json").content("{\"name\":\"Martillo\", \"price\":40.0}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();


        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"A error ocurred procesando input data\",\"details\":[\"Quantity must be greater than or equal to 1\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("PUT /material/{id} should update a material successfully")
    void updateMaterialTest() throws Exception {
        Material existingMaterial = new Material();
        existingMaterial.setName("Cal");
        existingMaterial.setPrice(40.0);
        existingMaterial.setQuantity(15);
        Material savedMaterial = repository.save(existingMaterial);

        MaterialDTO updatedMaterialDTO = new MaterialDTO();
        updatedMaterialDTO.setName("Cal Mejorada");
        updatedMaterialDTO.setPrice(50.0);
        updatedMaterialDTO.setQuantity(20);

        mockMvc.perform(put("/material/" + savedMaterial.getId())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedMaterialDTO)))
                .andExpect(status().isOk());

        Material updatedMaterial = repository.findById(savedMaterial.getId()).orElseThrow();
        assertEquals(updatedMaterialDTO.getName(), updatedMaterial.getName());
        assertEquals(updatedMaterialDTO.getPrice(), updatedMaterial.getPrice());
        assertEquals(updatedMaterialDTO.getQuantity(), updatedMaterial.getQuantity());
    }

    @Test
    @DisplayName("PUT /material/{id} should return 404 if material not found")
    void updateMaterialNotFoundTest() throws Exception {
        MaterialDTO updatedMaterialDTO = new MaterialDTO();
        updatedMaterialDTO.setName("Material Inexistente");
        updatedMaterialDTO.setPrice(25.0);
        updatedMaterialDTO.setQuantity(5);

        mockMvc.perform(put("/material/" + 999L)
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedMaterialDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /material/{id} should delete a material successfully")
    void deleteMaterialTest() throws Exception {
        Material existingMaterial = new Material();
        existingMaterial.setName("Material Test Eliminar");
        existingMaterial.setPrice(30.0);
        existingMaterial.setQuantity(10);
        Material savedMaterial = repository.save(existingMaterial);

        mockMvc.perform(delete("/material/" + savedMaterial.getId()))
                .andExpect(status().isNoContent());

        assertFalse(repository.existsById(savedMaterial.getId()));
    }
}
