package com.src.dao;

import java.util.List;

import com.src.model.Donor;

public interface DonorDAO {
    boolean insertDonor(Donor donor);
    Donor findDonorById(int donorId);
    Donor getDonorByEmail(String email);
    List<Donor> findAllDonors();
    boolean updateDonor(Donor donor);
    boolean deleteDonor(int donorId);
    boolean updatePasswordByEmail(String email, String newPassword);

}
