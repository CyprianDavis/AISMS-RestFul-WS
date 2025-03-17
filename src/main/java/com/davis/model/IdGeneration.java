package com.davis.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

/**
 * A utility class for generating unique IDs using a database sequence table.
 * This class is responsible for retrieving the current value of a sequence,
 * incrementing it, and updating the database with the new value.
 */
@Component // Marks this class as a Spring component, making it eligible for dependency injection.
public class IdGeneration {

	 @PersistenceContext
	    private EntityManager entityManager;

    /**
     * Retrieves the next ID number for a given sequence name.
     * This method performs the following steps:
     * 1. Retrieves the current value of the sequence from the database.
     * 2. Increments the value by 1.
     * 3. Updates the database with the new value.
     * 4. Returns the original (pre-incremented) value.
     *
     * @param idName The name of the sequence for which to generate the next ID.
     * @return The current value of the sequence before incrementing.
     * @throws RuntimeException If no rows are updated or if an error occurs during database operations.
     */
    public long getNextIdNumber(String idName) {
        long currentValue = 0; // Stores the current value of the sequence.
        try {
            // SQL query to get the current value for the sequence
            String selectSql = "SELECT idValue FROM ID_Gen WHERE idName = :idName";
            Query selectQuery = entityManager.createNativeQuery(selectSql);
            selectQuery.setParameter("idName", idName); // Sets the sequence name as a parameter.
            currentValue = ((Number) selectQuery.getSingleResult()).intValue(); // Retrieves the current value.

            // Increment the value
            long nextValue = currentValue + 1; // Calculates the next value.

            // SQL query to update the sequence value
            String updateSql = "UPDATE ID_Gen SET idValue = :nextValue WHERE idName = :idName";
            Query updateQuery = entityManager.createNativeQuery(updateSql);
            updateQuery.setParameter("nextValue", nextValue); // Sets the next value as a parameter.
            updateQuery.setParameter("idName", idName); // Sets the sequence name as a parameter.

            // Executes the update query
            int rowsUpdated = updateQuery.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("No rows updated. Check if the 'user' record exists.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Logs the exception.
            throw new RuntimeException("Error updating sequence value", e); // Throws a runtime exception.
        }
        return currentValue; // Returns the original (pre-incremented) value.
    }
}