package Lab6;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AddressBookRepository addressbookrepo;

    @Test
    public void contextLoads() throws Exception {
        assertTrue(addressbookrepo != null);
    }
}
