package com.software;


//Data Access Object
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
public class PetDAO { 
	public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM petmanagement.pets");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Pet pet = new Pet();
                pet.setId(resultSet.getInt("id"));
                pet.setName(resultSet.getString("name"));
                pet.setSpecies(resultSet.getString("species"));
                pet.setAge(resultSet.getInt("age"));
                pet.setPrice(resultSet.getInt("price"));
                pet.setVaccinated(resultSet.getBoolean("vaccinated"));
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pets;
    }
    
    public void insertPet(Pet pet) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO pets (id, name, species, age, price,vaccinated) VALUES (?, ?, ?, ?, ?,?)")) {
            
            preparedStatement.setInt(1, pet.getId());
            preparedStatement.setString(2, pet.getName());
            preparedStatement.setString(3, pet.getSpecies());
            preparedStatement.setInt(4, pet.getAge());
            preparedStatement.setInt(5, pet.getPrice());   
            preparedStatement.setBoolean(6, pet.getVaccinated());
            preparedStatement.executeUpdate();
            
        //
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
public Pet getPetById(int id) {
    Pet pet = null;
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM pets WHERE id = ?")) {
        
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            pet = new Pet();
            pet.setId(resultSet.getInt("id"));
            pet.setName(resultSet.getString("name"));
            pet.setSpecies(resultSet.getString("species"));
            pet.setAge(resultSet.getInt("age"));
            pet.setPrice(resultSet.getInt("price"));
            pet.setVaccinated(resultSet.getBoolean("vaccinated"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return pet;
}
public void updatePet(Pet pet) {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE pets SET id=?, name = ?, species = ?, age = ?, price = ?, vaccinated = ? WHERE id = ?")) {
    	preparedStatement.setInt(1, pet.getId());
        preparedStatement.setString(2, pet.getName());
        preparedStatement.setString(3, pet.getSpecies());
        preparedStatement.setInt(4, pet.getAge());
      // 
        preparedStatement.setInt(5, pet.getPrice());   
        preparedStatement.setBoolean(6, pet.getVaccinated());
        preparedStatement.executeUpdate();
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Pet updated successfully.");
        } else {
            System.out.println("No pet found with the given ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean deletePet(int id) {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "DELETE FROM pets WHERE id = ?")) {
        
        preparedStatement.setInt(1, id);
        
        int rowsAffected = preparedStatement.executeUpdate();
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public int countPetsByCategory(String category) {
    int count = 0;
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT COUNT(*) FROM pets WHERE species = ?")) {
        
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return count;
}

public List<Pet> findPetsByPriceRange(double minPrice, double maxPrice) {
    List<Pet> pets = new ArrayList<>();
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM pets WHERE price >= ? AND price <= ?")) {
        
        preparedStatement.setDouble(1, minPrice);
        preparedStatement.setDouble(2, maxPrice);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Pet pet = new Pet();
            pet.setId(resultSet.getInt("id"));
            pet.setName(resultSet.getString("name"));
            pet.setSpecies(resultSet.getString("species"));
            pet.setAge(resultSet.getInt("age"));
            pets.add(pet);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return pets;
}

public List<Pet> findPetsByVaccinationStatusAndSpecies(String species, boolean vaccinated) {
    List<Pet> pets = new ArrayList<>();
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM pets WHERE species = ? AND vaccinated = ?")) {
        
        preparedStatement.setString(1, species);
        preparedStatement.setBoolean(2, vaccinated);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Pet pet = new Pet();
            pet.setId(resultSet.getInt("id"));
            pet.setName(resultSet.getString("name"));
            pet.setSpecies(resultSet.getString("species"));
            pet.setAge(resultSet.getInt("age"));
            pets.add(pet);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return pets;
}
    
    // Add methods to insert, update, delete pets
}

