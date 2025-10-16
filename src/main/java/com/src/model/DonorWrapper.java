package com.src.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DonorWrapper {

    private List<Donor> donor;  // match JSON key "donor"

    public List<Donor> getDonor() {
        return donor;
    }

    public void setDonor(List<Donor> donor) {
        this.donor = donor;
    }
}
