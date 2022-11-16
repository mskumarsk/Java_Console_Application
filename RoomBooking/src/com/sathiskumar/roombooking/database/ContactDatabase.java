package database;

public class ContactDatabase {
    private String id;
    private String name;
    private String phoneNumber;
    private String acRooms;
    private String nonAcRooms;
    private String acRoomAmount;
    private String nonAcRoomAmount;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcRooms() {
        return acRooms;
    }

    public void setAcRooms(String acRooms) {
        this.acRooms = acRooms;
    }

    public String getNonAcRooms() {
        return nonAcRooms;
    }

    public void setNonAcRooms(String nonAcRooms) {
        this.nonAcRooms = nonAcRooms;
    }

    public String getAcRoomAmount() {
        return acRoomAmount;
    }

    public void setAcRoomAmount(String acRoomAmount) {
        this.acRoomAmount = acRoomAmount;
    }

    public String getNonAcRoomAmount() {
        return nonAcRoomAmount;
    }

    public void setNonAcRoomAmount(String nonAcRoomAmount) {
        this.nonAcRoomAmount = nonAcRoomAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
