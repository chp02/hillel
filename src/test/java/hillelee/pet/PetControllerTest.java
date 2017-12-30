package hillelee.pet;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by JavaEE on 12/30/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
    @Autowired
    JpaRepository petRepository;
    @Autowired
    MockMvc mockMvc;

    @After
    public void cleanup() {
        petRepository.deleteAll();
    }

    @Test
    public void getAllPets() throws Exception {
        petRepository.save(new Pet("Tom", "Cat", 3, LocalDate.now(), null, null));
        mockMvc.perform(MockMvcRequestBuilders.get("/pest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name", Matchers.is("Tom")));
    }

}