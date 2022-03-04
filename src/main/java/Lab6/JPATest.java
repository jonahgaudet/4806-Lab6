package Lab6;

import javax.persistence.*;
import java.util.List;

public class JPATest {

    public void retrieveData(EntityManager em) {
        // Querying the contents of the database for AddressBook
        Query qBook = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> resultsBook = qBook.getResultList();

        System.out.println("List of AddressBooks\n----------------");

        for (AddressBook a : resultsBook) {
            System.out.println(a.getName() + " (id=" + a.getId() + ")");
            System.out.println("Number of buddies: " + a.getSize());
            for (BuddyInfo b : a.getBuddies()) {
                System.out.println(b);
            }
            System.out.println();
        }

        // Querying the contents of the database for AddressBook
        Query qBuddy = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> resultsBuddy = qBuddy.getResultList();

        System.out.println("List of BuddyInfos\n----------------");

        for (BuddyInfo b : resultsBuddy) {
            System.out.println(b.getName() + " (id=" + b.getId() + ")");
            System.out.println(b + "\n");
        }
    }

    public void performJPA() {

        System.out.println("Creating buddies...");

        String name1 = "Jonah Gaudet";
        String address1 = "123 Main Street";
        String phoneNumber1 = "6131234567";
        BuddyInfo buddy1 = new BuddyInfo(name1, address1, phoneNumber1);

        String name2 = "Yegui Cai";
        String address2 = "123 Other Street";
        String phoneNumber2 = "6139876543";
        BuddyInfo buddy2 = new BuddyInfo(name2, address2, phoneNumber2);

        System.out.println("Creating address book...");

        String bookName = "My Address Book";
        AddressBook book = new AddressBook(bookName);


        // Connect to database through EntityManagerFactory
        System.out.println("Creating entity manager(s)...\n");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab6");
        EntityManager em = emf.createEntityManager();

        // Create transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persist entity
        em.persist(book);
        tx.commit();

        // Retrieve data
        System.out.println();
        retrieveData(em);

        // Add buddies
        System.out.println("\nAdding buddies to the book...: \n");
        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        // Starting a new transaction, persisting the entity, and retrieving the data
        tx.begin();
        em.persist(book);
        tx.commit();
        retrieveData(em);

        // Closing connection
        em.close();

        emf.close();
    }

    public static void main(String[] args) {
        System.out.println("Running JPA example: ");
        JPATest jpaTest = new JPATest();
        jpaTest.performJPA();
    }
}

