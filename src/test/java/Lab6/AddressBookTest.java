package Lab6;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
    AddressBook book;
    BuddyInfo buddy1, buddy2;

    private String name = "Test book";

    @BeforeEach
    public void setUp() {
        book = new AddressBook(name);
        buddy1 = new BuddyInfo("Test name");
        buddy2 = new BuddyInfo("Test name 2", "Test address", "Test phone");
    }

    @Test
    public void getName() {
        assertEquals(name, book.getName());
    }

    @Test
    public void addingBuddies() {
        assertFalse(book.contains(buddy1));
        assertFalse(book.contains(buddy2));
        assertTrue(book.isEmpty());
        assertEquals(0, book.getSize());

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        assertTrue(book.contains(buddy1));
        assertTrue(book.contains(buddy2));
        assertFalse(book.isEmpty());
        assertEquals(2, book.getSize());
    }

    @Test
    public void persistence() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab6");
        EntityManager em = emf.createEntityManager();

        // Create a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persist the BuddyInfo objects
        em.persist(buddy1);
        em.persist(buddy2);

        tx.commit();

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        tx.begin();

        // Persist the AddressBook object
        em.persist(book);

        tx.commit();

        // Query the database
        Query q = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        assertNotNull(results);
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), book);

        System.out.println("Show buddies in AddressBook:\n----------------");

        for (AddressBook a : results) {
            System.out.println("Book Name: " + a.getName());
            List<BuddyInfo> buddies = a.getBuddies();
            for (BuddyInfo b : buddies) {
                System.out.println("Name: " + b.getName());
                System.out.println("Address: " + b.getAddress());
                System.out.println("Phone Number: " + b.getPhoneNumber());
                System.out.println();
            }
        }
        // Closing connection
        em.close();
        emf.close();
    }

}