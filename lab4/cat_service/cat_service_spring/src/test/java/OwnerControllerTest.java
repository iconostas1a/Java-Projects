import lab4.Main;
import lab4.controller.OwnerController;
import lab4.data.entity.Kitty;
import lab4.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
@ContextConfiguration(classes = Main.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @Test
    void testCreateOwnerUnauthorized401() throws Exception {
        mockMvc.perform(post("/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content("""
                                {
                                  "name": "ArsenM",
                                  "birthDate": "1917-01-01"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser(username = "other_user", roles = {"USER"})
    void testAddKittyToAnotherOwner403() throws Exception {
        doThrow(new SecurityException("Access denied"))
                .when(ownerService).addKittyToOwner(eq(1L), any(Kitty.class));

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
                .andExpect(status().isForbidden());
    }
}
