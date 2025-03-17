package com.davis.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.davis.model.Supplier;
import com.davis.service.SupplierService;

/**
 * Controller class for handling supplier-related HTTP requests.
 * This class maps incoming requests to appropriate service methods and returns the response.
 *
 * @Controller Indicates that this class is a Spring MVC controller.
 * @RequestMapping Specifies the base URL path for all endpoints in this controller.
 */
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * Handles a GET request to retrieve all suppliers.
     *
     * @return A list of all suppliers.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     * @ResponseStatus(HttpStatus.OK) Specifies that the HTTP response status will be 200 (OK) if successful.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Supplier> getSupplies() {
        return supplierService.getSupplies();
    }

    /**
     * Handles a GET request to retrieve a specific supplier by their unique identifier.
     *
     * @param id The unique identifier of the supplier to retrieve.
     * @return The supplier with the specified ID, or null if not found.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Supplier getSupplier(@PathVariable String id) {
        return supplierService.getSupplier(id);
    }

    /**
     * Handles a POST request to add a new supplier.
     *
     * @param supplier The supplier to be added, provided in the request body.
     * @return The added supplier.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     * @ResponseStatus(HttpStatus.CREATED) Specifies that the HTTP response status will be 201 (Created) if successful.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupllier(supplier);
    }
}