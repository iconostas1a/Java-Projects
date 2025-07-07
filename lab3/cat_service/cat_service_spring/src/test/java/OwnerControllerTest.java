import lab3.Main;
import lab3.controller.OwnerController;
import lab3.data.entity.Owner;
import lab3.dto.OwnerDto;
import lab3.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration(classes = Main.class)
@WebMvcTest(controllers = OwnerController.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @Test
    void testGetOwner() throws Exception {
        OwnerDto mockOwner = new OwnerDto(1L, "ArsenM", LocalDate.of(1917, 1, 1), List.of());

        Mockito.when(ownerService.getOwnerById(1L)).thenReturn(mockOwner);

        mockMvc.perform(get("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("ArsenM"));
    }
    @Test
    void testCreateOwner() throws Exception{
        Owner owner = new Owner();
        owner.setName("ArsenM");
        OwnerDto responseDto = new OwnerDto(1L, "ArsenM", LocalDate.of(1990, 1, 1), List.of());

        Mockito.when(ownerService.save(any())).thenReturn(responseDto);

        mockMvc.perform(post("/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "ArsenM",
                                  "birthDate": "1917-01-01"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ArsenM"));
    }
    @Test
    void testAddKittyToOwner() throws Exception {
        mockMvc.perform(post("/owner/1/kitties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Mur",
                                  "birthDate": "2024-05-01",
                                  "breed": "DVOROVAYA",
                                  "color": "BLACK"
                                }
                                """))
                .andExpect(status().isOk());

        verify(ownerService).addKittyToOwner(eq(1L), any());
    }
}
