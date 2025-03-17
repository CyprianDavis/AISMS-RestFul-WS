package com.davis.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.davis.model.Inventory;
import com.davis.service.InventoryService;

/**
 * Controller class for handling inventory-related HTTP requests.
 * This class maps incoming requests to appropriate service methods and returns the response.
 *
 * @Controller Indicates that this class is a Spring MVC controller.
 * @RequestMapping Specifies the base URL path for all endpoints in this controller.
 */
@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Handles a GET request to retrieve all inventory items.
     *
     * @return A list of all inventory items.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     * @ResponseStatus(HttpStatus.OK) Specifies that the HTTP response status will be 200 (OK) if successful.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    /**
     * Handles a POST request to add a new inventory item.
     *
     * @param inventory The inventory item to be added, provided in the request body.
     * @return The added inventory item.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }
}