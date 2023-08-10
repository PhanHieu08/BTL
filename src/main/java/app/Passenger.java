package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Passenger {
    private String ID_Passenger;
    private String ho_va_Ten;
    private String cccd;
    private LocalDate date;
    private String gender;
    private String email;
    private int Level;
    private String address;
    private String phone;

    private Account account;

    public Passenger(String ID_Passenger, String ho_va_Ten, String cccd, String gender, LocalDate date, String address, String phone, String email, int level, Account account) {
        this.ID_Passenger = ID_Passenger;
        this.ho_va_Ten = ho_va_Ten;
        this.cccd = cccd;
        this.gender = gender;
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.Level = level;
        this.account = account;
    }

    public String getID_Passenger() {
        return ID_Passenger;
    }

    public void setID_Passenger(String id) {
        this.ID_Passenger = id;
    }

    public String getHo_va_Ten() {
        return ho_va_Ten;
    }

    public void setHo_va_Ten(String name) {
        this.ho_va_Ten = name;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDate() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return formattedDate;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public void setDate(LocalDate dob) {
        this.date = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() { return account.getUsername(); }
    public String getPassword() { return account.getPassword(); }

    public String getIDAccount() { return account.getID_Account(); }
}
