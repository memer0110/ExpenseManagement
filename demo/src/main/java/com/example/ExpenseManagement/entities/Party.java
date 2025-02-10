package com.example.ExpenseManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String partyName;
    private String contactNumber;
    private String address;
    private String category;
    private String pan;
    private String gst;
    private String notes;

    public Party()
    {

    }

    public Party(String partyName, String contactNumber, String address, String category, String pan, String gst, String notes)
    {
        this.partyName = partyName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.category = category;
        this.pan = pan;
        this.gst = gst;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", partyName='" + partyName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", pan='" + pan + '\'' +
                ", gst='" + gst + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
