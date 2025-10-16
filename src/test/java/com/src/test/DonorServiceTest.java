package com.src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.src.dao.DonorDAO;
import com.src.dao.DonorDAOImpl; // Your real DAO
import com.src.model.Donor;
import com.src.service.DonorServiceImpl;

public class DonorServiceTest {

    private DonorDAO donorDAO;
    private DonorServiceImpl donorService;

    @BeforeEach
    public void setUp() {
        donorDAO = new DonorDAOImpl(); // Real DAO
        donorService = new DonorServiceImpl(); // Inject real DAO
    }

    @Test
    public void testAddDonor() {
        Donor donor = new Donor();
        donor.setDonorName("kartik");
        donor.setEmail("karthik2@email.com");
        donor.setPhone("9087654321");
        donor.setAddress("mangalagiri");
        
        donor.setPassword("Test@123");
        donor.setConfirmPassword("Test@123");

        boolean result = donorService.addDonor(donor);
        assertTrue(result, "Donor should be added to database");
    }

   

    @Test
    public void testDeleteDonor() {
        int idToDelete = 59; // Make sure this ID exists in your DB
        boolean result = donorService.deleteDonor(idToDelete);
        assertTrue(result, "Donor should be deleted from DB");
    }
}
