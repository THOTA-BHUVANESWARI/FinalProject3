package com.src.mn;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.src.dao.CampaignDAO;
import com.src.dao.CampaignDAOImpl;
import com.src.dao.CharityDAO;
import com.src.dao.CharityDAOImpl;
import com.src.dao.DonorDAO;
import com.src.dao.DonorDAOImpl;
import com.src.dao.TransactionDAO;
import com.src.dao.TransactionDAOImpl;
import com.src.exception.PasswordMismatchException;
import com.src.model.Campaign;
import com.src.model.Charity;
import com.src.model.Donor;
import com.src.model.Transaction;
import com.src.util.PasswordValidator;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DonorDAO donorDAO = new DonorDAOImpl();
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        CampaignDAO campaignDAO = new CampaignDAOImpl();
        CharityDAO charityDAO = new CharityDAOImpl();

        int mainChoice = -1;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Manage Donors");
            System.out.println("2. Manage Transactions");
            System.out.println("3. Manage Campaigns");
            System.out.println("4. Manage Charities");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 1:
                    manageDonors(sc, donorDAO);
                    break;
                case 2:
                    manageTransactions(sc, transactionDAO, donorDAO, campaignDAO);
                    break;
                case 3:
                    manageCampaigns(sc, campaignDAO);
                    break;
                case 4:
                    manageCharities(sc, charityDAO);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (mainChoice != 0);

        sc.close();
    }

    // -------------------- Donor CRUD --------------------
    private static void manageDonors(Scanner sc, DonorDAO donorDAO) {
        int choice = -1;
        do {
            System.out.println("\n--- DONOR MENU ---");
            System.out.println("1. Add Donor");
            System.out.println("2. View Donor by ID");
            System.out.println("3. View All Donors");
            System.out.println("4. Update Donor");
            System.out.println("5. Delete Donor");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
            case 1:
                System.out.print("Enter Name: "); String name = sc.nextLine();
                System.out.print("Enter Email: "); String email = sc.nextLine();
                System.out.print("Enter Phone: "); String phone = sc.nextLine();
                System.out.print("Enter Address: "); String address = sc.nextLine();
                System.out.print("Enter Password: "); String password = sc.nextLine();
                System.out.print("Confirm Password: "); String confirmPassword = sc.nextLine();

                // ✅ Lambda validation
                PasswordValidator validator = (p1, p2) -> p1.equals(p2);
                try {
                    if (!validator.validate(password, confirmPassword))
                        throw new PasswordMismatchException("Passwords do not match!");

                    Donor newDonor = new Donor();
                    newDonor.setDonorName(name);
                    newDonor.setEmail(email);
                    newDonor.setPhone(phone);
                    newDonor.setAddress(address);
                    newDonor.setPassword(password);

                    System.out.println(donorDAO.insertDonor(newDonor) ? "Donor added!" : "Failed!");

                } catch (PasswordMismatchException e) {
                    System.err.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


                case 2:
                    System.out.print("Enter Donor ID: "); int id = sc.nextInt(); sc.nextLine();
                    Donor donor = donorDAO.findDonorById(id);
                    if (donor != null) {
                        System.out.println(donor.getDonorId() + " | " + donor.getDonorName() + " | " +
                                           donor.getEmail() + " | " + donor.getPhone() + " | " + donor.getAddress());
                    } else System.out.println("Donor not found!");
                    break;

                case 3:
                    List<Donor> donors = donorDAO.findAllDonors();
                    for (Donor d : donors)
                        System.out.println(d.getDonorId() + " | " + d.getDonorName() + " | " +
                                           d.getEmail() + " | " + d.getPhone() + " | " + d.getAddress());
                    break;

                case 4:
                    System.out.print("Enter Donor ID to update: "); int updateId = sc.nextInt(); sc.nextLine();
                    Donor updateDonor = donorDAO.findDonorById(updateId);
                    if (updateDonor != null) {
                        System.out.print("New Name (" + updateDonor.getDonorName() + "): "); String newName = sc.nextLine();
                        System.out.print("New Email (" + updateDonor.getEmail() + "): "); String newEmail = sc.nextLine();
                        System.out.print("New Phone (" + updateDonor.getPhone() + "): "); String newPhone = sc.nextLine();
                        System.out.print("New Address (" + updateDonor.getAddress() + "): "); String newAddress = sc.nextLine();

                        updateDonor.setDonorName(newName.isEmpty() ? updateDonor.getDonorName() : newName);
                        updateDonor.setEmail(newEmail.isEmpty() ? updateDonor.getEmail() : newEmail);
                        updateDonor.setPhone(newPhone.isEmpty() ? updateDonor.getPhone() : newPhone);
                        updateDonor.setAddress(newAddress.isEmpty() ? updateDonor.getAddress() : newAddress);

                        System.out.println(donorDAO.updateDonor(updateDonor) ? "Updated!" : "Failed!");
                    } else System.out.println("Donor not found!");
                    break;

                case 5:
                    System.out.print("Enter Donor ID to delete: "); int delId = sc.nextInt(); sc.nextLine();
                    System.out.println(donorDAO.deleteDonor(delId) ? "Deleted!" : "Failed!");
                    break;
                case 6:
                    System.out.print("Enter your registered email: ");
                    String emailToReset = sc.nextLine();
                    Donor existingDonor = donorDAO.getDonorByEmail(emailToReset);

                    if (existingDonor != null) {
                        System.out.print("Enter New Password: ");
                        String newPassword = sc.nextLine();
                        System.out.print("Confirm New Password: ");
                        String confirmNewPassword = sc.nextLine();

                        PasswordValidator validator2 = (p1, p2) -> p1.equals(p2);
                        if (validator2.validate(newPassword, confirmNewPassword)) {
                            boolean updated = donorDAO.updatePasswordByEmail(emailToReset, newPassword);
                            System.out.println(updated ? "Password updated successfully!" : "Failed to update password!");
                        } else {
                            System.err.println("Passwords do not match!");
                        }
                    } else {
                        System.err.println("No donor found with that email!");
                    }
                    break;


                case 0: System.out.println("Returning to main menu..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    // -------------------- Transaction CRUD --------------------
    private static void manageTransactions(Scanner sc, TransactionDAO transactionDAO, DonorDAO donorDAO, CampaignDAO campaignDAO) {
        int choice = -1;
        do {
            System.out.println("\n--- TRANSACTION MENU ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transaction by ID");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Transactions by Donor");
            System.out.println("5. View Transactions by Campaign");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: "); 
            choice = sc.nextInt(); 
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Donor ID: "); 
                    int donorId = sc.nextInt(); sc.nextLine();

                    System.out.print("Enter Campaign ID: "); 
                    int campaignId = sc.nextInt(); sc.nextLine();

                    // Get the campaign object to retrieve charityId
                    Campaign campaign = campaignDAO.findCampaignById(campaignId);
                    if (campaign == null) {
                        System.out.println("Invalid Campaign ID!");
                        break;
                    }
                    int charityId = campaign.getCharityId();

                    System.out.print("Enter Amount: "); 
                    double amount = sc.nextDouble(); sc.nextLine();

                    Transaction transaction = new Transaction();
                    transaction.setDonorId(donorId);
                    transaction.setCampaignId(campaignId);
                    transaction.setCharityId(charityId); // automatically set
                    transaction.setAmount(amount);

                    System.out.println(transactionDAO.insertTransaction(transaction) ? "Transaction added!" : "Failed!");
                    break;

                case 2:
                    System.out.print("Enter Transaction ID: "); 
                    int transId = sc.nextInt(); sc.nextLine();
                    Transaction t = transactionDAO.findTransactionById(transId);
                    if (t != null) {
                        System.out.println(t.getTransactionId() + " | Donor ID: " + t.getDonorId() + 
                                           " | Campaign ID: " + t.getCampaignId() + 
                                           " | Charity ID: " + t.getCharityId() +
                                           " | Amount: " + t.getAmount());
                    } else System.out.println("Transaction not found!");
                    break;

                case 3:
                    List<Transaction> transactions = transactionDAO.findAllTransactions();
                    for (Transaction tr : transactions)
                        System.out.println(tr.getTransactionId() + " | Donor ID: " + tr.getDonorId() + 
                                           " | Campaign ID: " + tr.getCampaignId() + 
                                           " | Charity ID: " + tr.getCharityId() +
                                           " | Amount: " + tr.getAmount());
                    break;

                case 4:
                    System.out.print("Enter Donor ID: "); 
                    int dId = sc.nextInt(); sc.nextLine();
                    List<Transaction> byDonor = transactionDAO.findTransactionsByDonorId(dId);
                    for (Transaction td : byDonor)
                        System.out.println(td.getTransactionId() + " | Campaign ID: " + td.getCampaignId() +
                                           " | Charity ID: " + td.getCharityId() +
                                           " | Amount: " + td.getAmount());
                    break;

                case 5:
                    System.out.print("Enter Campaign ID: "); 
                    int cId = sc.nextInt(); sc.nextLine();
                    List<Transaction> byCampaign = transactionDAO.findTransactionsByCampaign(cId);
                    for (Transaction tc : byCampaign)
                        System.out.println(tc.getTransactionId() + " | Donor ID: " + tc.getDonorId() +
                                           " | Charity ID: " + tc.getCharityId() +
                                           " | Amount: " + tc.getAmount());
                    break;

                case 0: 
                    System.out.println("Returning to main menu..."); 
                    break;

                default: 
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }


    // -------------------- Campaign CRUD --------------------
    

    private static void manageCampaigns(Scanner sc, CampaignDAO campaignDAO) {
        int choice = -1;
        do {
            System.out.println("\n--- CAMPAIGN MENU ---");
            System.out.println("1. Add Campaign");
            System.out.println("2. View Campaign by ID");
            System.out.println("3. View All Campaigns");
            System.out.println("4. Update Campaign");
            System.out.println("5. Delete Campaign");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: "); 
            choice = sc.nextInt(); 
            sc.nextLine();

            switch (choice) {
                case 1: // Add Campaign
                    System.out.print("Enter Campaign Name: "); 
                    String name = sc.nextLine();

                    System.out.print("Enter Description: "); 
                    String desc = sc.nextLine();

                    System.out.print("Enter Charity ID: "); 
                    int charityId = sc.nextInt(); 
                    sc.nextLine();

                    LocalDate startDate = null;
                    System.out.print("Enter Start Date (YYYY-MM-DD): "); 
                    try {
                        startDate = LocalDate.parse(sc.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid start date format! Use YYYY-MM-DD.");
                        break;
                    }

                    LocalDate endDate = null;
                    System.out.print("Enter End Date (YYYY-MM-DD): "); 
                    try {
                        endDate = LocalDate.parse(sc.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid end date format! Use YYYY-MM-DD.");
                        break;
                    }

                    Campaign campaign = new Campaign();
                    campaign.setCampaignName(name);
                    campaign.setDescription(desc);
                    campaign.setCharityId(charityId);
                    campaign.setStartDate(startDate);
                    campaign.setEndDate(endDate);

                    System.out.println(campaignDAO.insertCampaign(campaign) ? "Campaign added!" : "Failed!");
                    break;

                case 2: // View Campaign by ID
                    System.out.print("Enter Campaign ID: "); 
                    int id = sc.nextInt(); 
                    sc.nextLine();
                    Campaign c = campaignDAO.findCampaignById(id);
                    if (c != null) {
                        System.out.println(c.getCampaignId() + " | " 
                                         + c.getCampaignName() + " | " 
                                         + c.getDescription() + " | " 
                                         + c.getStartDate() + " | " 
                                         + c.getEndDate());
                    } else {
                        System.out.println("Campaign not found!");
                    }
                    break;

                case 3: // View All Campaigns
                    List<Campaign> campaigns = campaignDAO.findAllCampaigns();
                    for (Campaign camp : campaigns) {
                        System.out.println(camp.getCampaignId() + " | " 
                                         + camp.getCampaignName() + " | " 
                                         + camp.getDescription() + " | " 
                                         + camp.getStartDate() + " | " 
                                         + camp.getEndDate());
                    }
                    break;

                case 4: // Update Campaign
                    System.out.print("Enter Campaign ID to update: "); 
                    int updId = sc.nextInt(); 
                    sc.nextLine();
                    Campaign updCampaign = campaignDAO.findCampaignById(updId);
                    if (updCampaign != null) {
                        System.out.print("New Name (" + updCampaign.getCampaignName() + "): "); 
                        String newName = sc.nextLine();

                        System.out.print("New Description (" + updCampaign.getDescription() + "): "); 
                        String newDesc = sc.nextLine();

                        System.out.print("New Start Date (" + updCampaign.getStartDate() + "): "); 
                        String newStart = sc.nextLine();

                        System.out.print("New End Date (" + updCampaign.getEndDate() + "): "); 
                        String newEnd = sc.nextLine();

                        updCampaign.setCampaignName(newName.isEmpty() ? updCampaign.getCampaignName() : newName);
                        updCampaign.setDescription(newDesc.isEmpty() ? updCampaign.getDescription() : newDesc);
                        if (!newStart.isEmpty()) {
                            try {
                                updCampaign.setStartDate(LocalDate.parse(newStart));
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid start date format! Skipping update for start date.");
                            }
                        }
                        if (!newEnd.isEmpty()) {
                            try {
                                updCampaign.setEndDate(LocalDate.parse(newEnd));
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid end date format! Skipping update for end date.");
                            }
                        }

                        System.out.println(campaignDAO.updateCampaign(updCampaign) ? "Updated!" : "Failed!");
                    } else {
                        System.out.println("Campaign not found!");
                    }
                    break;

                case 5: // Delete Campaign
                    System.out.print("Enter Campaign ID to delete: "); 
                    int delId = sc.nextInt(); 
                    sc.nextLine();
                    System.out.println(campaignDAO.deleteCampaign(delId) ? "Deleted!" : "Failed!");
                    break;

                case 0: // Exit
                    System.out.println("Returning to main menu..."); 
                    break;

                default: 
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }


 // -------------------- Charity CRUD --------------------
    private static void manageCharities(Scanner sc, CharityDAO charityDAO) {
        int choice = -1;
        do {
            System.out.println("\n--- CHARITY MENU ---");
            System.out.println("1. Add Charity");
            System.out.println("2. View Charity by ID");
            System.out.println("3. View All Charities");
            System.out.println("4. Update Charity");
            System.out.println("5. Delete Charity");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Add Charity
                    System.out.print("Enter Charity Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Registration No: ");
                    String regNo = sc.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contact = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Location: ");
                    String location = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    Charity charity = new Charity();
                    charity.setName(name);
                    charity.setRegNo(regNo);
                    charity.setContact(contact);
                    charity.setEmail(email);
                    charity.setLocation(location);
                    charity.setPassword(password);

				try {
					System.out.println(charityDAO.insertCharity(charity) ? "Charity added!" : "Failed!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;

                case 2: // View Charity by ID
                    System.out.print("Enter Charity ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Charity c = charityDAO.findCharityById(id);
                    if (c != null) {
                        System.out.println(c.getCharityId() + " | " + c.getName() + " | " +
                                           c.getRegNo() + " | " + c.getContact() + " | " +
                                           c.getEmail() + " | " + c.getLocation());
                    } else System.out.println("Charity not found!");
                    break;

                case 3: // View All Charities
                    List<Charity> charities = charityDAO.findAllCharities();
                    for (Charity ch : charities) {
                        System.out.println(ch.getCharityId() + " | " + ch.getName() + " | " +
                                           ch.getRegNo() + " | " + ch.getContact() + " | " +
                                           ch.getEmail() + " | " + ch.getLocation());
                    }
                    break;

                case 4: // Update Charity
                    System.out.print("Enter Charity ID to update: ");
                    int updId = sc.nextInt();
                    sc.nextLine();
                    Charity updCharity = charityDAO.findCharityById(updId);
                    if (updCharity != null) {
                        System.out.print("New Name (" + updCharity.getName() + "): ");
                        String newName = sc.nextLine();
                        System.out.print("New Registration No (" + updCharity.getRegNo() + "): ");
                        String newReg = sc.nextLine();
                        System.out.print("New Contact Number (" + updCharity.getContact() + "): ");
                        String newContact = sc.nextLine();
                        System.out.print("New Email (" + updCharity.getEmail() + "): ");
                        String newEmail = sc.nextLine();
                        System.out.print("New Location (" + updCharity.getLocation() + "): ");
                        String newLocation = sc.nextLine();
                        System.out.print("New Password: ");
                        String newPassword = sc.nextLine();

                        updCharity.setName(newName.isEmpty() ? updCharity.getName() : newName);
                        updCharity.setRegNo(newReg.isEmpty() ? updCharity.getRegNo() : newReg);
                        updCharity.setContact(newContact.isEmpty() ? updCharity.getContact() : newContact);
                        updCharity.setEmail(newEmail.isEmpty() ? updCharity.getEmail() : newEmail);
                        updCharity.setLocation(newLocation.isEmpty() ? updCharity.getLocation() : newLocation);
                        updCharity.setPassword(newPassword.isEmpty() ? updCharity.getPassword() : newPassword);

                        System.out.println(charityDAO.updateCharity(updCharity) ? "Updated!" : "Failed!");
                    } else System.out.println("Charity not found!");
                    break;

                case 5: // Delete Charity
                    System.out.print("Enter Charity ID to delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    System.out.println(charityDAO.deleteCharity(delId) ? "Deleted!" : "Failed!");
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}