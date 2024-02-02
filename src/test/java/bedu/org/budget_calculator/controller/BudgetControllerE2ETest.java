package bedu.org.budget_calculator.controller;

import bedu.org.budget_calculator.dto.ErrorDTO;
import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.model.Estatus;
import bedu.org.budget_calculator.repository.BudgetRepository;
import bedu.org.budget_calculator.repository.ConceptRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class BudgetControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ConceptRepository conceptRepository;


    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    final LocalDate endDate = LocalDate.of(2024,02,22);
    final LocalDate startDate = LocalDate.of(2024,02,2);


    @BeforeEach
    public void setup(){
        conceptRepository.deleteAll();//LA BD TEST SE LLENA CON VALORES se debe borrar por llave foranea
        budgetRepository.deleteAllInBatch();
    }
    @Test
    @DisplayName("GET /budgets should return an empty list")
    void emptyListTest() throws Exception {

        // Realizar una petición de tipo GET
        // hacia /budgets y esperar que el resultado sea 200
        MvcResult result = mockMvc.perform(get("/budgets"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }
    @Test
    @DisplayName("GET /budgets should return a list of budgets")
    void findAllTest() throws Exception {
        Budget fakeBudget1 = new Budget();
        Budget fakeBudget2 = new Budget();

        fakeBudget1.setNameBudget("TEST GET LIST 1");
        fakeBudget1.setStatus(Estatus.PENDIENTE);
        fakeBudget1.setTotal(125.96);
        fakeBudget1.setActive(true);
        fakeBudget1.setEndDate(endDate);
        fakeBudget1.setStartDate(startDate);
        Client fakeClient = new Client();
        fakeClient.setId(1);

        fakeBudget1.setCustomerId(fakeClient);

        fakeBudget2.setNameBudget("TEST GET LIST 2");
        fakeBudget2.setStatus(Estatus.PENDIENTE);
        fakeBudget2.setTotal(15000);
        fakeBudget2.setActive(true);
        fakeBudget2.setCustomerId(fakeClient);
        fakeBudget2.setEndDate(endDate);
        fakeBudget2.setStartDate(startDate);

        budgetRepository.save(fakeBudget1);
        budgetRepository.save(fakeBudget2);

        MvcResult result = mockMvc.perform(get("/budgets"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<BudgetDTO>> listTypeReference = new TypeReference<List<BudgetDTO>>() {};

        // Convertimos el JSON a un objeto de Java
        List<BudgetDTO> response = objectMapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertTrue(response.size() == 2);
        assertEquals(fakeBudget1.getNameBudget(),response.get(0).getNameBudget());
        assertEquals(fakeBudget1.getStatus(),response.get(0).getStatus());
        assertEquals(fakeBudget1.getTotal(),response.get(0).getTotal());

        assertEquals(fakeBudget2.getNameBudget(),response.get(1).getNameBudget());
        assertEquals(fakeBudget2.getStatus(),response.get(1).getStatus());
        assertEquals(fakeBudget2.getTotal(),response.get(1).getTotal());

    }

    @Test
    @DisplayName("POST /budgets should return an error if nameBudget is missing")
    void budgetNameMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/budgets").contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":10.0,"+
                        "\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Leemos el contenido con caracteres de acentos y ñ
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        // { "code": "ERR_VALID", "message": "Hubo un error al validar los datos de
        // entrada", "details": ["El titulo es obligatorio"]}

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"A error ocurred procesando input data\",\"details\":[\"El campo nombre presupuesto es obligatorio\"]}";

        assertEquals(expectedResponse, content);
    }
    @Test
    @DisplayName("POST /budgets should return an error if startDate is missing")
    void totalMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/budgets").contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":10.0,"+
                        "\"nameBudget\":\"Test E2E POST\"," +
                        //"\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Convertimos el JSON a un objeto de Java
        ErrorDTO response = objectMapper.readValue(content, ErrorDTO.class);

        assertEquals("ERR_VALID", response.getCode());
        assertEquals("A error ocurred procesando input data", response.getMessage());

        List<String> details = (List<String>) response.getDetails();

        assertEquals("La feha Inicio es obligatoria", details.get(0));
    }

    @Test
    @DisplayName("POST /budgets should return create a budget successfully")
    void CreateBudgetTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/budgets").contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":10.0,"+
                        "\"nameBudget\":\"Test E2E POST\"," +
                        "\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals(201,result.getResponse().getStatus());
    }

    @Test
    @DisplayName("PATH /budgets/{id} should return an error if total is 0")
    void TotalBadDataInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(patch("/budgets/1").contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":0.0,"+
                        "\"nameBudget\":\"Pruebas 2: Alta para test\"," +
                        "\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Leemos el contenido con caracteres de acentos y ñ
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"A error ocurred procesando input data\",\"details\":[\"El total debe ser mayor a 0\"]}";

        assertEquals(expectedResponse, content);
    }
    @Test
    @DisplayName("PATH /budgets/{id} should return an error if not found budget")
    void UpdateNotFoundBudgetTest() throws Exception {
        MvcResult result = mockMvc.perform(patch("/budgets/100").contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":1250.0,"+
                        "\"nameBudget\":\"Pruebas 2: Alta para test\"," +
                        "\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isNotFound())
                .andReturn();

        // Leemos el contenido con caracteres de acentos y ñ
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = "{\"code\":\"ERR_BUDGET_NOT_FOUND\",\"message\":\"No se encontró el presupuesto.\",\"details\":100}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("PATH /budgets/{id} should return 204")
    void UpdateInRequestBodyTest() throws Exception {
        Budget fakeBudget1 = new Budget();
        fakeBudget1.setNameBudget("TEST PATCH 1");
        fakeBudget1.setStatus(Estatus.PENDIENTE);
        fakeBudget1.setTotal(125.96);
        fakeBudget1.setActive(true);
        fakeBudget1.setEndDate(endDate);
        fakeBudget1.setStartDate(startDate);
        Client fakeClient = new Client();
        fakeClient.setId(1);

        fakeBudget1.setCustomerId(fakeClient);

        budgetRepository.save(fakeBudget1);

        MvcResult result = mockMvc.perform(patch("/budgets/"+fakeBudget1.getId()).contentType("application/json").content(
                        "{\"customerId\":{ \"id\":1 }," +
                        "\"total\":1250.0,"+
                        "\"nameBudget\":\"Pruebas 2: Update para test\"," +
                        "\"startDate\":\"2024-12-28\"," +
                        "\"endDate\":\"2024-12-28\"," +
                        "\"status\":\"ACTIVO\"}" ))
                .andExpect(status().isNoContent())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals(204,result.getResponse().getStatus());
    }

    @Test
    @DisplayName("DELETE /budgets/{id} should return an error if not found budget")
    void DeleteNotFoundBudgetTest() throws Exception {
        MvcResult result = mockMvc.perform(delete("/budgets/9999"))
                .andExpect(status().isNotFound())
                .andReturn();

        // Leemos el contenido con caracteres de acentos y ñ
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = "{\"code\":\"ERR_BUDGET_NOT_FOUND\",\"message\":\"No se encontró el presupuesto.\",\"details\":9999}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("DELETE /budgets{id} should delete a budget successfully")
    void DeleteInRequestBodyTest() throws Exception {
        Budget fakeBudget1 = new Budget();
        fakeBudget1.setNameBudget("TEST GET LIST 1");
        fakeBudget1.setStatus(Estatus.PENDIENTE);
        fakeBudget1.setTotal(125.96);
        fakeBudget1.setActive(true);
        fakeBudget1.setEndDate(endDate);
        fakeBudget1.setStartDate(startDate);

        Client fakeClient = new Client();
        fakeClient.setId(1);

        fakeBudget1.setCustomerId(fakeClient);

        Budget savedBudget = budgetRepository.save(fakeBudget1);

        mockMvc.perform(delete("/budgets/"+savedBudget.getId()))
                .andExpect(status().isNoContent());

        assertFalse(budgetRepository.existsById(savedBudget.getId()));
    }
}
