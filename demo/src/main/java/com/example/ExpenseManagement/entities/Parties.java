package com.example.ExpenseManagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
public class Parties {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "party_id")
    private String id;

    private String partyName;

    @Column(name = "party_contact_no")
    private String contactNumber;

    @Column(name = "party_address")
    private String address;

    private String category;

    @Column(name = "party_pan")
    private String pan;

    @Column(name = "party_gst")
    private String gst;

    @Column(name = "party_notes")
    private String notes;

//    @Column(name = "updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updated;

    @Column(name = "updated",nullable = false)
    @UpdateTimestamp  // Hibernate will auto-update this field
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Parties()
    {

    }

    public Parties(String partyName, String contactNumber, String address, String category, String pan, String gst, String notes)
    {
        this.partyName = partyName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.category = category;
        this.pan = pan;
        this.gst = gst;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
