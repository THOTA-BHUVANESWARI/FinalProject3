package com.src.dao;

import java.util.List;

import com.src.model.Charity;

public interface CharityDAO {
    boolean insertCharity(Charity charity) throws Exception;
    Charity findCharityById(int id);
    Charity findCharityByEmail(String email);
    List<Charity> findAllCharities();
    boolean updateCharity(Charity charity);
    boolean deleteCharity(int id);
	
}
