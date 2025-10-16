package com.src.model;

import java.util.Objects;

import com.src.annotation.Column;
import com.src.annotation.Id;
import com.src.annotation.Table;

@Table(name = "donor")
public class Donor {

    @Id
    @Column(name = "donorId")
    private int donorId;

    @Column(name = "name", length = 100)
    private String donorName;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "password", length = 255)
    private String password;

    // Not persisted in DB, used only for validation
    private String confirmPassword;

    // Constructors
    public Donor() {}

    public Donor(int donorId, String donorName, String email, String phone, String address, String password) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    // Getters and Setters
    public int getDonorId() { return donorId; }
    public void setDonorId(int donorId) { this.donorId = donorId; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    // hashCode and equals
    @Override
    public int hashCode() {
        return Objects.hash(address, donorId, donorName, email, phone, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Donor other = (Donor) obj;
        return donorId == other.donorId &&
                Objects.equals(donorName, other.donorName) &&
                Objects.equals(email, other.email) &&
                Objects.equals(phone, other.phone) &&
                Objects.equals(address, other.address) &&
                Objects.equals(password, other.password);
    }

    // toString
    @Override
    public String toString() {
        return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", email=" + email + 
               ", phone=" + phone + ", address=" + address + ", password=" + password + 
               ", confirmPassword=" + confirmPassword + "]";
    }
}
