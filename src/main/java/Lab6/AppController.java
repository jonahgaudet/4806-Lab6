package Lab6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    public AppController (AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

//    @GetMapping("/")
//    public String defaultPage(Model model) {
//        return addressbook(model);
//    }

    @GetMapping("/addressbookview")
    public String addressbook(Model model) {
        model.addAttribute("addressbook", new AddressBook());
        return "addressbookview";
    }

    @GetMapping("/createaddressbook")
    public String addressbookForm(Model model) {
        model.addAttribute("addressbook", new AddressBook());
        return "createaddressbook";
    }

    @PostMapping("/createaddressbook")
    public String submitAddressBookForm(@ModelAttribute AddressBook addressbook, Model model) {
        System.out.println(addressbook.getId());
        addressBookRepository.save(addressbook);
        System.out.println(addressbook.getId());

        model.addAttribute("addressbook", addressbook);

        model.addAttribute("buddyinfo", new BuddyInfo());
        return "addressbookview";
    }

    @GetMapping("/getAddressBook")
    public String getAddressBook(@RequestParam(name="id", required=false, defaultValue="-1") String id, Model model) {
        if (id.equals("-1"))
            return "createaddressbook";

        AddressBook addressbook = addressBookRepository.findById(Integer.parseInt(id));
        model.addAttribute("addressbook", addressbook);

        model.addAttribute("buddyinfo", new BuddyInfo());

        return "addressbookview";
    }
}
