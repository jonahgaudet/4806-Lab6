package Lab6;

import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BuddyInfoRepository buddyRepo;

    @Autowired
    AddressBookRepository addressBookRepository;

    @Test
    public void addressBookEmpty() throws Exception {
        String url = "/addressbook";
        this.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("_embedded.addressbook", hasSize(0)));
    }

    @Test
    public void createAddressBook() throws Exception {
        String url = "/addressbook";
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"My address book\"}"))
                .andDo(print())
                .andExpect(status().is(201));

        assertNotEquals(this.addressBookRepository.count(), 0);
    }

    @Test
    public void createNewBuddy() throws Exception {
        String url = "/buddyinfo";
        MvcResult result = this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Jonah Gaudet\", " +
                        "\"address\": \"123 Main St\", \"phoneNumber\": \"6131234567\"}"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();

        assertNotNull(this.buddyRepo.findByName("Jonah Gaudet"));
    }

    @Test
    public void addnewBuddy() throws Exception {
//        "{\"bookID\": \"1\", \"buddy\": {\"name\": \"Test name 1\", \"address\": \"Test address\", \"phoneNumber\": \"6131230987\"}}"
        String url = "/buddyinfo";
        MvcResult result = this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookID\": \"1\", \"buddy\": {\"name\": \"Test name 1\", " +
                        "\"address\": \"Test address\", \"phoneNumber\": \"6131230987\"}}"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();

        assertNotNull(this.buddyRepo.findByName("Smartpreet Grewal"));
    }
}