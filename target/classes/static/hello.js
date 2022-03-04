function addBuddy () {
    console.log("Submitting buddy");
    var sendInfo = {
        bookID: $("#bookID").val(),
        buddy: {
            name: $("#addBuddyName").val(),
            address: $("#addBuddyAddress").val(),
            phoneNumber: $("#addBuddyPhoneNumber").val()
        }
    };
    console.log(sendInfo)

    $.ajax({
            type: "POST",
            url: "/addbuddyinfo",
            data: JSON.stringify(sendInfo),
            contentType: "application/json; charset=utf-8",
    }).then(function(data) {
        console.log("Done");
        console.log(data);
        var newRow = '<tr><td>' + data.id +
                    '</td><td>' + data.name +
                    '</td><td>' + data.address +
                    '</td><td>' + data.phoneNumber +
                    '</td></tr>';
        $('#buddies-table').append(newRow);
        $("#addBuddyName").val("");
        $("#addBuddyAddress").val("");
        $("#addBuddyPhoneNumber").val("");
    });
}

function createBook () {
    alert("Creating book");
    console.log("Creating a new book");
    var sendInfo = {
        name: $("#addressbookName").val()
    };
    console.log(sendInfo)

    $.ajax({
            type: "POST",
            url: "/addressbook",
            data: JSON.stringify(sendInfo),
            contentType: "application/json; charset=utf-8",
    }).then(function(data) {
        console.log("Done");
        console.log(data);
        var div = $('#bookView');
        div.empty();

        var linkarray = data._links.self.href.split('/');

        div.append("<h2>AddressBook</h2>");
        div.append("<p>This is an AddressBook object!</p>");
        div.append("<p>AddressBook Name: " + data.name + "</p>")
        div.append("<p>AddressBook Id: " + linkarray[linkarray.length - 1] + "</p>")

        div.append("<p><b>Buddies contained in the Address Book:</b></p>");
        div.append("<table id=\"buddies-table\">" +
                "<tr><th>ID</th><th>Name</th><th>Address</th><th>Phone Number</th></tr>");

        console.log(linkarray[linkarray.length - 1]);
        div.append("<p>Current AddressBook Id: <input id=\"bookID\" type=\"text\" value=\"" + linkarray[linkarray.length - 1] + "\" readonly/></p>")
        div.append("<p>Name: <input id=\"addBuddyName\" type=\"text\"/></p>")
        div.append("<p>Address: <input id=\"addBuddyAddress\" type=\"text\" /></p>")
        div.append("<p>Phone number: <input id=\"addBuddyPhoneNumber\" type=\"text\" /></p>")

        div.append("<button id=\"buddySubmit\" onclick=\"addBuddy()\" type=\"submit\" value=\"Submit\" >Add buddy</button>")
    });
}