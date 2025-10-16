package com.src.service;

import java.util.List;

import com.src.dao.CharityDAO;
import com.src.dao.CharityDAOImpl;
import com.src.model.Charity;

public class CharityServiceImpl implements CharityServiceInterface {

    private CharityDAO charityDAO = new CharityDAOImpl();

    @Override
    public boolean addCharity(Charity charity) {
        try {
            return charityDAO.insertCharity(charity);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();

            // Return false to indicate failure (important!)
            return false;
        }
    }

    @Override
    public Charity getCharityById(int id) {
        return charityDAO.findCharityById(id);
    }

    @Override
    public Charity getCharityByEmail(String email) {
        return charityDAO.findCharityByEmail(email);
    }

    @Override
    public List<Charity> getAllCharities() {
        return charityDAO.findAllCharities();
    }

    @Override
    public boolean updateCharity(Charity charity) {
        return charityDAO.updateCharity(charity);
    }

    @Override
    public boolean deleteCharity(int id) {
        return charityDAO.deleteCharity(id);
    }
}
