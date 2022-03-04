package Lab6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuddyInfoTest {
    BuddyInfo buddy;
    String name = "My buddy";

    @BeforeEach
    public void setUp() {
        buddy = new BuddyInfo(name);
    }

    @Test
    public void getName() {
        assertEquals(name, buddy.getName());
    }

    @Test
    public void persistence() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab6");
        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persisting the BuddyInfo object
        em.persist(buddy);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        assertNotNull(results);
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), buddy);


        // Closing connection
        em.close();
        emf.close();
    }
}