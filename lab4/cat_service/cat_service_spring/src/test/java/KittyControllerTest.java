import lab4.Main;
import lab4.controller.KittyController;
import lab4.dto.KittyDto;
import lab4.models.Breed;
import lab4.models.Color;
import lab4.service.KittyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = KittyController.class)
@ContextConfiguration(classes = Main.class)
class KittyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KittyService kittyService;

    private final KittyDto kitty = new KittyDto(
            1L, "Mur", LocalDate.of(2020, 1, 1), Breed.BRITISH_SHORTHAIR, Color.BLACK, null, List.of()
    );

    @Test
    void testGetKittyUnauthorized() throws Exception {
        mockMvc.perform(get("/kitties/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetKittyAsUser() throws Exception {
        when(kittyService.getKittyById(1L)).thenReturn(kitty);

        mockMvc.perform(get("/kitties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mur"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testDeleteKittyForbiddenForUser() throws Exception {
        mockMvc.perform(delete("/kitties/1"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void deleteKittyshouldReturn200() throws Exception {
        mockMvc.perform(delete("/kitties/1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetKittiesAsUser() throws Exception {
        when(kittyService.findKitties(any(), any(), any(), any()))
                .thenReturn(new org.springframework.data.domain.PageImpl<>(List.of(kitty)));

        mockMvc.perform(get("/kitties")
                        .param("name", "Mur")
                        .param("breed", "BRITISH_SHORTHAIR")
                        .param("color", "BLACK")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Mur"));
    }
}
