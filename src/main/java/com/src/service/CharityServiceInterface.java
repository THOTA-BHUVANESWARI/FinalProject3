package com.src.service;

import java.util.List;

import com.src.model.Charity;

public interface CharityServiceInterface {
    boolean addCharity(Charity charity);
    Charity getCharityById(int id);
    Charity getCharityByEmail(String email);
    

    List<Charity> getAllCharities();
    boolean updateCharity(Charity charity);
    boolean deleteCharity(int id);
}
