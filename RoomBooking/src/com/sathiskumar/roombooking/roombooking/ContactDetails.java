package roombooking;

import database.ContactDatabase;
import repository.Database;

import java.util.ArrayList;

public class ContactDetails {

    public void contact() {
        Database database = Database.getInstance();
        ArrayList<ContactDatabase> contactDatabase = database.getContactDatabases();
        System.out.println("================================>> CONTACT DETAILS <<================================");
        System.out.println("HOTEL NAME               :  " + contactDatabase.get(0).getName());
        System.out.println("PHONE NO                 :  " + contactDatabase.get(0).getPhoneNumber());
        System.out.println("AC ROOMS                 :  " + contactDatabase.get(0).getAcRooms());
        System.out.println("AC ROOM AMOUNT           :  " + contactDatabase.get(0).getAcRoomAmount());
        System.out.println("NON AC ROOMS             :  " + contactDatabase.get(0).getNonAcRooms());
        System.out.println("NON AC ROOMS AMOUNT      :  " + contactDatabase.get(0).getNonAcRoomAmount());
        System.out.println("ADDRESS                  :  " + contactDatabase.get(0).getAddress());
    }
}
