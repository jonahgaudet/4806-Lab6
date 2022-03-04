package Lab6;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany( cascade = CascadeType.PERSIST)
    private List<BuddyInfo> buddies;

    private String name;

    public AddressBook () {
        this.buddies = new ArrayList<>();
    }

    public AddressBook(String name) {
        this.buddies = new ArrayList<>();
        this.name = name;
    }

    public AddressBook(long id, String name) {
        this.id = (int)id;
        this.buddies = new ArrayList<>();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuddyInfo> getBuddies () {
        return buddies;
    }

    public void addBuddy(BuddyInfo newBuddy) {
        buddies.add(newBuddy);
    }

    public boolean remove (BuddyInfo buddy) {
        return buddies.remove(buddy);
    }

    public void removeAt (int index) {
        buddies.remove(index);
    }

    public boolean isEmpty () {
        return buddies.isEmpty();
    }

    public int getSize () {
        return buddies.size();
    }

    public boolean contains (BuddyInfo buddy) {
        return buddies.contains(buddy);
    }

    public void clear () {
        buddies.clear();
    }

    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        AddressBook other = (AddressBook) obj;

//        if (id != null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;

        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;


        if (this.buddies.size() != other.getSize())
            return false;

        for (BuddyInfo b : this.buddies) {
            if (!other.contains(b))
                return false;
        }

        return true;
    }

    public void getInfo () {
        System.out.println(name);
        for (int i = 0; i < buddies.size() ; i++) {
            System.out.println(buddies.get(i));
        }
    }

    public static void main(String[] args) {
        AddressBook book = new AddressBook("My Address Book");
        BuddyInfo buddy1 = new BuddyInfo("Jonah Gaudet", "123 Main St", "6131234567");
        BuddyInfo buddy2 = new BuddyInfo("Yegui Cai", "321 Other St", "6139876543");
        BuddyInfo buddy3 = new BuddyInfo("Justin Trudeau");

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.addBuddy(buddy3);

        book.getInfo();
    }
}
