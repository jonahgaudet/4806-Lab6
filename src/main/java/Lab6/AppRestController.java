package Lab6;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppRestController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    public AppRestController (AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/viewaddressbooks")
    public ResponseEntity<JSONArray> findAll() {
        JSONArray items = new JSONArray();
        for (AddressBook a : addressBookRepository.findAll())
            items.add(a);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/addbuddyinfo")
    public ResponseEntity<JSONObject> addBuddy(@RequestBody AddBuddyToBook addBuddy) {
        System.out.println(addBuddy.getBookID());
        System.out.println(addBuddy.getBuddy());

        AddressBook book = addressBookRepository.findById(addBuddy.getBookID());
        BuddyInfo buddy = addBuddy.getBuddy();
        book.addBuddy(buddy);
        buddyInfoRepository.save(buddy);
        addressBookRepository.save(book);
        book.getInfo();
        System.out.println(buddy);

        JSONObject item = new JSONObject();
        item.put("bookid", book.getId());
        item.put("id", buddy.getId());
        item.put("name", buddy.getName());
        item.put("address", buddy.getAddress());
        item.put("phoneNumber", buddy.getPhoneNumber());
        System.out.println(item.toJSONString());
        return ResponseEntity.ok(item);
    }
}
