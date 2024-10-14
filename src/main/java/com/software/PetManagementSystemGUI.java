package com.software;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PetManagementSystemGUI extends JFrame {

    private void displayPetsTable(List<Pet> pets) {
        String[] columnNames = {"ID", "Name", "Species", "Age", "Price", "Vaccinated"};
        Object[][] data = new Object[pets.size()][6];

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            data[i][0] = pet.getId();
            data[i][1] = pet.getName();
            data[i][2] = pet.getSpecies();
            data[i][3] = pet.getAge();
            data[i][4] = pet.getPrice();
            data[i][5] = pet.getVaccinated() ? "Yes" : "No";
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable petTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(petTable);

        JFrame displayFrame = new JFrame("Display Pets");
        displayFrame.setSize(600, 400);
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.add(scrollPane);
        displayFrame.setVisible(true);
    }

    // ... (existing code)

    private void displayPets() {
        try {
            List<Pet> pets = petDAO.getAllPets();
            
            if (!pets.isEmpty()) {
                displayPetsTable(pets);
            } else {
                JOptionPane.showMessageDialog(this, "No pets found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while retrieving pets.");
        }
    }

    // ... (other methods)

   
    private final PetDAO petDAO;
    private JTextField idField, nameField, speciesField, ageField, priceField, categoryField, minPriceField, maxPriceField, speciesSearchField;
    private JCheckBox vaccinatedCheckBox;

    public PetManagementSystemGUI() {
        petDAO = new PetDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Pet Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        speciesField = new JTextField();
        ageField = new JTextField();
        priceField = new JTextField();
        vaccinatedCheckBox = new JCheckBox("Vaccinated");

        categoryField = new JTextField();
        minPriceField = new JTextField();
        maxPriceField = new JTextField();

        speciesSearchField = new JTextField();

        JButton addButton = new JButton("Add Pet");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPet();
            }
        });

        JButton displayButton = new JButton("Display Pets");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPets();
            }
        });

        JButton updateButton = new JButton("Update Pet");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePet();
            }
        });

        JButton deleteButton = new JButton("Delete Pet");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePet();
            }
        });

        JButton countButton = new JButton("Count Pets");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countPets();
            }
        });

        JButton priceRangeButton = new JButton("Pets in Price Range");
        priceRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petsInPriceRange();
            }
        });

        JButton vaccinatedPetsButton = new JButton("Vaccinated Pets");
        vaccinatedPetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaccinatedPets();
            }
        });

        panel.add(new JLabel("Pet ID:"));
        panel.add(idField);

        panel.add(new JLabel("Pet Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Pet Species:"));
        panel.add(speciesField);

        panel.add(new JLabel("Pet Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Pet Price:"));
        panel.add(priceField);

        panel.add(new JLabel("Vaccinated:"));
        panel.add(vaccinatedCheckBox);

        panel.add(addButton);
        panel.add(displayButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(countButton);
       panel.add(priceRangeButton);
        panel.add(vaccinatedPetsButton);

        add(panel);
    }

    private void addPet() {
        try {
            Pet newPet = new Pet();
            newPet.setId(Integer.parseInt(idField.getText()));
            newPet.setName(nameField.getText());
            newPet.setSpecies(speciesField.getText());
            newPet.setAge(Integer.parseInt(ageField.getText()));
            newPet.setPrice(Integer.parseInt(priceField.getText()));
            newPet.setVaccinated(vaccinatedCheckBox.isSelected());

            petDAO.insertPet(newPet);
            JOptionPane.showMessageDialog(this, "Pet added successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your input fields.");
        }
    }

    
    private void updatePet() {
        try {
            int updateId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the pet to update:"));

            Pet petToUpdate = petDAO.getPetById(updateId);
            if (petToUpdate != null) {
                petToUpdate.setName(nameField.getText());
                petToUpdate.setSpecies(speciesField.getText());
                petToUpdate.setAge(Integer.parseInt(ageField.getText()));
                petToUpdate.setPrice(Integer.parseInt(priceField.getText()));
                petToUpdate.setVaccinated(vaccinatedCheckBox.isSelected());

                petDAO.updatePet(petToUpdate);
                JOptionPane.showMessageDialog(this, "Pet updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Pet not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your input fields.");
        }
    }

    private void deletePet() {
        try {
            int deleteId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the pet to delete:"));

            if (petDAO.deletePet(deleteId)) {
                JOptionPane.showMessageDialog(this, "Pet deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Pet not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid ID.");
        }
    }

    private void countPets() {
        try {
            String category = JOptionPane.showInputDialog("Enter the species/category to count:");
            int count = petDAO.countPetsByCategory(category);
            JOptionPane.showMessageDialog(this, "Number of pets in the category '" + category + "': " + count);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while counting pets.");
        }
    }
    private void petsInPriceRange() {
        try {
            double minPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the minimum price:"));
            double maxPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the maximum price:"));

            List<Pet> petsInPriceRange = petDAO.findPetsByPriceRange(minPrice, maxPrice);
            if (petsInPriceRange.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No pets found within the specified price range.");
            } else {
                StringBuilder displayText = new StringBuilder("ID   | Name          | Species       | Age   | Price | Vaccinated\n");
                displayText.append("--------------------------------------------------------------\n");

                for (Pet pet : petsInPriceRange) {
                    displayText.append(String.format("%-4d | %-14s | %-14s | %-4d | %-4d | %s%n",
                            pet.getId(), pet.getName(), pet.getSpecies(),
                            pet.getAge(), pet.getPrice(), pet.getVaccinated() ? "Yes" : "No"));
                }

                JTextArea textArea = new JTextArea(displayText.toString());
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                JFrame displayFrame = new JFrame("Pets in Price Range");
                displayFrame.setSize(600, 400);
                displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                displayFrame.add(scrollPane);
                displayFrame.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while searching for pets in the specified price range.");
        }
    }



    private void vaccinatedPets() {
    try {
        String species = JOptionPane.showInputDialog("Enter the pet species:");
        boolean vaccinated = JOptionPane.showInputDialog("Is the pet vaccinated? (true/false):").equalsIgnoreCase("true");

        List<Pet> petsByVaccination = petDAO.findPetsByVaccinationStatusAndSpecies(species, vaccinated);

        if (petsByVaccination.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No pets found matching the specified criteria.");
        } else {
            StringBuilder displayText = new StringBuilder("ID   | Name          | Species       | Age   | Price | Vaccinated\n");
            displayText.append("--------------------------------------------------------------\n");

            for (Pet pet : petsByVaccination) {
                displayText.append(String.format("%-4d | %-14s | %-14s | %-4d | %-4d | %s%n",
                        pet.getId(), pet.getName(), pet.getSpecies(),
                        pet.getAge(), pet.getPrice(), pet.getVaccinated() ? "Yes" : "No"));
            }

            JTextArea textArea = new JTextArea(displayText.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);

            JFrame displayFrame = new JFrame("Vaccinated Pets");
            displayFrame.setSize(600, 400);
            displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            displayFrame.add(scrollPane);
            displayFrame.setVisible(true);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "An error occurred while searching for vaccinated pets.");
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new PetManagementSystemGUI().setVisible(true);
        }
    });
}
}
