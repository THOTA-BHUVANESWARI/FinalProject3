package com.src.model;

import java.util.Objects;

import com.src.annotation.Column;
import com.src.annotation.Id;
import com.src.annotation.Table;

@Table(name = "charity")
public class Charity {

    @Id
    @Column(name = "charityId")
    private int charityId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "regNo", unique = true, length = 50)
    private String regNo;

    @Column(name = "contact", length = 100)
    private String contact;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "location", length = 150)
    private String location;

    @Column(name = "password", length = 255)
    private String password; // plain-text for simplicity

    @Column(name = "isVerified")
    private boolean isVerified = false;

    @Column(name = "failedLoginAttempts")
    private int failedLoginAttempts = 0;

    @Column(name = "lockTime")
    private long lockTime; // timestamp in ms

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_DURATION = 15 * 60 * 1000; // 15 min

    public Charity() { }

    // Getters and Setters
    public int getCharityId() { return charityId; }
    public void setCharityId(int charityId) { this.charityId = charityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { this.isVerified = verified; }

    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public void setFailedLoginAttempts(int failedLoginAttempts) { this.failedLoginAttempts = failedLoginAttempts; }

    public long getLockTime() { return lockTime; }
    public void setLockTime(long lockTime) { this.lockTime = lockTime; }

    // ===== Security =====
    public boolean validatePassword(String inputPassword) {
        if (isLocked()) return false;
        boolean matched = inputPassword.equals(this.password);
        if (!matched) {
            failedLoginAttempts++;
            if (failedLoginAttempts >= MAX_FAILED_ATTEMPTS) {
                lockTime = System.currentTimeMillis();
            }
        } else {
            failedLoginAttempts = 0; // reset on success
        }
        return matched;
    }

    public boolean isLocked() {
        if (lockTime == 0) return false;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lockTime >= LOCK_DURATION) {
            failedLoginAttempts = 0;
            lockTime = 0;
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Charity)) return false;
        Charity other = (Charity) obj;
        return charityId == other.charityId && Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() { return Objects.hash(charityId, email); }
}
