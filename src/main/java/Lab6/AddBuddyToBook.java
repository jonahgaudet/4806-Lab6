package Lab6;

public class AddBuddyToBook {

    private long bookID;

    private BuddyInfo buddy;

    public AddBuddyToBook () {

    }

    public AddBuddyToBook (long bookID, String name) {
        this.bookID=bookID;
        this.buddy = new BuddyInfo(name);
    }

    public AddBuddyToBook (long bookID, String name, String address, String phoneNumber) {
        this.bookID=bookID;
        this.buddy = new BuddyInfo(name, address, phoneNumber);
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID (long bookID) {
        this.bookID = bookID;
    }

    public BuddyInfo getBuddy() {
        return buddy;
    }

    public void setBuddy(BuddyInfo buddy) {
        this.buddy = buddy;
    }
}
