package com.src.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.src.dao.DonorDAO;
import com.src.dao.DonorDAOImpl;
import com.src.exception.PasswordMismatchException;
import com.src.model.Donor;
import com.src.model.DonorWrapper;

public class DonorServiceImpl implements DonorServiceInterface {

    private static final String BASE_URL = "http://localhost:8080/project4Rest/webresources/donors/";
    private DonorDAO donorDAO = new DonorDAOImpl();

    // ✅ Functional Interface for Lambda Validation
    @FunctionalInterface
    interface PasswordValidator {
        boolean validate(String password, String confirmPassword);
    }

    @Override
    public boolean addDonor(Donor donor) {
        try {
            // ✅ Lambda expression for password match validation
            PasswordValidator validator = (p1, p2) -> p1.equals(p2);

            if (!validator.validate(donor.getPassword(), donor.getConfirmPassword())) {
                throw new PasswordMismatchException("Passwords do not match!");
            }

            return donorDAO.insertDonor(donor);

        } catch (PasswordMismatchException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Donor getDonorById(int donorId) {
        Donor donor = null;
        try {
            // ✅ Create JAX-RS client
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(BASE_URL).path(String.valueOf(donorId)); // REST path for specific donor

            // Optional: print for debugging
            System.out.println("Fetching donor from: " + target.getUri());

            // Fetch donor as JSON and convert to Donor object
            donor = target.request(MediaType.APPLICATION_JSON).get(Donor.class);

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return donor;
    }


    @Override
    public Donor getDonorByEmail(String email) {
        Donor donor = null;
        try {
            // ✅ Create JAX-RS client
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(BASE_URL)
                                     .path("byemail")         // Path in your REST resource
                                     .queryParam("email", email); // Add email as query parameter

            // Optional: print for debugging
            System.out.println("Fetching donor by email from: " + target.getUri());

            // Fetch donor as JSON and convert to Donor object
            donor = target.request(MediaType.APPLICATION_JSON).get(Donor.class);

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return donor;
    }


    @Override
    public List<Donor> getAllDonors() {
        List<Donor> donors = new ArrayList<>();
        try {
            // ✅ Create JAX-RS client
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(BASE_URL).path("all"); // Adjust path as per your REST endpoint

            // Optional: print for debugging
            System.out.println("Fetching donors from: " + target.getUri());

            // Fetch donors as JSON and convert to List<Donor>]
            DonorWrapper wrapper = target.request(MediaType.APPLICATION_JSON)
                    .get(DonorWrapper.class);

donors = wrapper.getDonor(); // use singular "getDonor()" as per JSON

            System.out.println(donors);

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return donors;
    }

    @Override
    public boolean updateDonor(Donor donor) {
        return donorDAO.updateDonor(donor);
    }

    @Override
    public boolean deleteDonor(int donorId) {
        return donorDAO.deleteDonor(donorId);
    }

    // ✅ Forgot Password Implementation
    @Override
    public boolean resetPassword(String email, String newPassword, String confirmPassword) {
        try {
            PasswordValidator validator = (p1, p2) -> p1.equals(p2);

            if (!validator.validate(newPassword, confirmPassword)) {
                throw new PasswordMismatchException("New passwords do not match!");
            }

            return donorDAO.updatePasswordByEmail(email, newPassword);

        } catch (PasswordMismatchException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
