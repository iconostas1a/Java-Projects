import lab3.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab3.controller.KittyController;
import lab3.dto.KittyDto;
import lab3.dto.OwnerDto;
import lab3.models.Breed;
import lab3.models.Color;
import lab3.service.KittyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = Main.class)
@WebMvcTest(controllers = KittyController.class)
class KittyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KittyService kittyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final KittyDto kitty = new KittyDto(
            1L, "Mur", LocalDate.of(1111, 1, 1), Breed.BRITISH_SHORTHAIR, Color.BLACK, null, List.of()
    );

    @Test
    void testGetKitties() throws Exception {
        Page<KittyDto> resultPage = new PageImpl<>(List.of(kitty), PageRequest.of(0, 10), 1);
        when(kittyService.findKitties(eq("Mur"), eq("BRITISH_SHORTHAIR"), eq(Color.BLACK), any(Pageable.class)))
                .thenReturn(resultPage);

        mockMvc.perform(get("/kitties")
                        .param("name", "Mur")
                        .param("breed", "BRITISH_SHORTHAIR")
                        .param("color", "BLACK")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Mur"));
    }

    @Test
    void testGetKittyById() throws Exception {
        when(kittyService.getKittyById(1L)).thenReturn(kitty);

        mockMvc.perform(get("/kitties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mur"));
    }
    @Test
    void testDeleteKitty() throws Exception {
        doNothing().when(kittyService).deleteById(1L);

        mockMvc.perform(delete("/kitties/1"))
                .andExpect(status().isOk());
    }

}

