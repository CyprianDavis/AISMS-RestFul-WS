package com.davis.service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.davis.dao.InventoryDao;
import com.davis.model.IdGeneration;
import com.davis.model.Inventory;
import com.davis.model.Status;

/**
 * Service class for managing inventory-related operations.
 * This class acts as an intermediary between the controller and the data access layer (DAO).
 * It handles business logic and ensures transactional integrity for inventory operations.
 *
 * @Repository Indicates that this class is a Spring-managed repository component.
 * @Transactional Ensures that all methods in this class are executed within a transactional context.
 */
@Repository
@Transactional
public class InventoryService {

    @Autowired
    private IdGeneration idGeneration; // Utility for generating unique IDs

    @Autowired
    private InventoryDao inventoryDao; // Data access object for inventory-related operations

    /**
     * Adds a new inventory item to the system.
     * Generates a unique inventory ID, sets the creation date and status,
     * and then persists the inventory item using the DAO.
     *
     * @param inventory The inventory item to be added.
     * @return The added inventory item.
     */
    public Inventory addInventory(Inventory inventory) {
        // Generate a unique inventory ID
        int year = Year.now().getValue();
        long id = idGeneration.getNextIdNumber("inventoryId");
        inventory.setInventoryId(getInventoryId(id, year));

        // Set the creation date and status
        LocalDateTime now = LocalDateTime.now();
        inventory.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).toString());
        inventory.setStatus(Status.ACTIVE.toString().toUpperCase());

        // Save the inventory item using the DAO
        return inventoryDao.addInventory(inventory);
    }

    /**
     * Retrieves a list of all inventory items in the system.
     *
     * @return A list of inventory items.
     */
    public List<Inventory> getInventory() {
        return inventoryDao.getInventory();
    }

    /**
     * Generates a unique inventory ID based on the given ID and year.
     * The ID is formatted to ensure it has a consistent length and includes the current year.
     *
     * @param id    The unique ID number.
     * @param year  The current year.
     * @return      A formatted inventory ID.
     */
    private String getInventoryId(long id, int year) {
        if (id <= 9) {
            return "IN0000" + id + "" + year;
        } else if (id >= 10 && id <= 99) {
            return "IN000" + id + "" + year;
        } else if (id >= 100 && id <= 999) {
            return "IN00" + id + "" + year;
        } else if (id >= 1000 && id <= 9999) {
            return "IN0" + id + "" + year;
        } else {
            return "IN" + id + "" + year;
        }
    }
}