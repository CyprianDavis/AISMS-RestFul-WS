package com.davis.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.davis.dao.InventoryDao;
import com.davis.model.Inventory;

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
    private InventoryDao inventoryDao;

    /**
     * Adds a new inventory item to the system.
     *
     * @param inventory The inventory item to be added.
     * @return The added inventory item.
     */
    public Inventory addInventory(Inventory inventory) {
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
}