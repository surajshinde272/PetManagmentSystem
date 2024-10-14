package com.software;


import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
    	try { PetDAO petDAO = new PetDAO();
         Scanner scanner = new Scanner(System.in);
         
         while (true) { System.out.println("******************************************************");
             System.out.println("Choose an option:");
             System.out.println("1. Add a pet");
             System.out.println("2. Display all pets");
             System.out.println("3. Update a pet");
             System.out.println("4. Delete a pet");
             System.out.println("5. Exit");
             System.out.println("6. count pets");
             System.out.println("7. price pet");
             System.out.println("8. vaccineted");
             System.out.println("******************************************************");
             
            int choice = scanner.nextInt();
             scanner.nextLine();  // Consume the newline
             
             switch (choice) {
                 case 1:
                     Pet newPet = new Pet();
                     System.out.print("Enter pet ID: ");
                     newPet.setId(scanner.nextInt());
                     scanner.nextLine();  // Consume the newline
                     
                     System.out.print("Enter pet name: ");
                     newPet.setName(scanner.nextLine());
                     
                     System.out.print("Enter pet species: ");
                     newPet.setSpecies(scanner.nextLine());
                     
                     System.out.print("Enter pet age: ");
                     newPet.setAge(scanner.nextInt());
                     scanner.nextLine();  // Consume the newline
                     
                     System.out.print("Enter pet price: ");
                     newPet.setPrice(scanner.nextInt());
                     scanner.nextLine();  // Consume the newline
                     

                   System.out.print("Enter vaccination status (true or false): ");
                   newPet.setVaccinated(scanner.nextBoolean());
                   scanner.nextLine(); 
                   
                     
                       petDAO.insertPet(newPet);
                     System.out.println("Pet added successfully.");
                     System.out.println("******************************************************");
                   break;
                     
                 case 2:
                     List<Pet> pets = petDAO.getAllPets();
                    for (Pet pet : pets) {
                        System.out.println(pet.getId() + " - " + pet.getName() + " - " +
                                           pet.getSpecies() + " - " + pet.getAge()+ " - "+ pet.getPrice()+" -"+   pet.getVaccinated());
                    }
                	
                     
                 case 3:
                	 System.out.print("Enter the ID of the pet to update: ");
                     int updateId = scanner.nextInt();
                     scanner.nextLine();  // Consume the newline
                     
                     Pet petToUpdate = petDAO.getPetById(updateId);
                     if (petToUpdate != null) {
                         System.out.print("Enter new name: ");
                         petToUpdate.setName(scanner.nextLine());
                         
                         System.out.print("Enter new species: ");
                         petToUpdate.setSpecies(scanner.nextLine());
                         
                         System.out.print("Enter new age: ");
                         petToUpdate.setAge(scanner.nextInt());
                         scanner.nextLine();  // Consume the newline
                         
                         System.out.print("Enter pet price: ");
                         petToUpdate.setPrice(scanner.nextInt());
                         scanner.nextLine();  // Consume the newline
                         
                         System.out.print("Enter vaccination status (true or false): ");
                         petToUpdate.setVaccinated(scanner.nextBoolean());
                         scanner.nextLine(); 
                         
                         
                         petDAO.updatePet(petToUpdate);
                         System.out.println("Pet updated successfully.");
                         System.out.println("******************************************************");
                     } else {
                         System.out.println("Pet not found.");
                     }
                     break;
                     
                     
                 case 4:
                     System.out.print("Enter the ID of the pet to delete: ");
                     int deleteId = scanner.nextInt();
                     scanner.nextLine();  // Consume the newline
                     
                     if (petDAO.deletePet(deleteId)) {
                         System.out.println("Pet deleted successfully.");
                         System.out.println("******************************************************");
                     } else {
                         System.out.println("Pet not found.");
                     }
                     break;
                     
                 case 5:
                     System.out.println("Exiting the application.");
                     System.out.println("******************************************************");
                     scanner.close();
                     System.exit(0);
                     break;
                     
                 case 6:
                     System.out.print("Enter the species/category to count: ");
                     String category = scanner.nextLine();
                     
                     int count = petDAO.countPetsByCategory(category);
                     System.out.println("Number of pets in the category '" + category + "': " + count);
                     System.out.println("******************************************************");
                     break;
                
                 case 7:
                     System.out.print("Enter the minimum price: ");
                     double minPrice = scanner.nextDouble();
                     
                     System.out.print("Enter the maximum price: ");
                     double maxPrice = scanner.nextDouble();
                     
                     List<Pet> petsInPriceRange = petDAO.findPetsByPriceRange(minPrice, maxPrice);
                     if (petsInPriceRange.isEmpty()) {
                         System.out.println("No pets found within the specified price range.");
                     } else {
                         System.out.println("Pets within the price range:");
                         for (Pet pet : petsInPriceRange) {
                             System.out.println(pet.getId() + " - " + pet.getName() + " - " +
                                                pet.getSpecies() + " - " + pet.getAge()+ "_"+pet.getPrice()+"_"+   pet.getVaccinated());
                         }
                     } System.out.println("******************************************************");
                     break;
                     
                 case 8:
                     System.out.print("Enter the pet species: ");
                     String species = scanner.nextLine();
//                     
//                     System.out.print("Enter vaccination status (true or false): ");
//                     boolean vaccinated = scanner.nextBoolean();
//                     scanner.nextLine();  // Consume the newline
                     System.out.print("Is the pet vaccinated? (true/false): ");
                     String vaccinatedInput = scanner.nextLine().toLowerCase();
                     
                     boolean Vaccinated = false;
                     if (vaccinatedInput.equals("true")) {
                         Vaccinated = true;
                     }
                     
                     System.out.println("Vaccination status: " + Vaccinated);
                     
                    
                     scanner.close();
                     
                     List<Pet> petsByVaccination = petDAO.findPetsByVaccinationStatusAndSpecies(species, Vaccinated);
                     if (petsByVaccination.isEmpty()) {
                         System.out.println("No pets found matching the specified criteria.");
                     } else {
                         System.out.println("Pets matching the specified criteria:");
                         for (Pet pet : petsByVaccination) {
                             System.out.println(pet.getId() + " - " + pet.getName() + " - " +
                                                pet.getSpecies() + " - " + pet.getAge()+" - "+pet.getPrice()+" - "+   pet.getVaccinated());
                         }
                     }
                     break;
                     
                     
                 default:
                     System.out.println("Invalid choice. Please select a valid option.");
                     System.out.println("******************************************************");
             }
         } }catch (Exception e) {System.out.println("it is not valid,try again");}
     }

}
