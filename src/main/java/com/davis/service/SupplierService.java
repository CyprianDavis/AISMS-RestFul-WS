package com.davis.service;

import java.time.Year;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.davis.dao.SupplierDao;
import com.davis.model.IdGeneration;
import com.davis.model.Status;
import com.davis.model.Supplier;

/**
 * Service class for managing supplier-related operations.
 * This class acts as an intermediary between the controller and the data access layer (DAO).
 * It handles business logic for managing suppliers.
 *
 * @Service Indicates that this class is a Spring-managed service component.
 */
@Service
public class SupplierService {
	@Autowired
    private IdGeneration idGeneration;

    @Autowired
    private SupplierDao supplierDao;
    

    /**
     * Adds a new supplier to the system.
     *
     * @param supplier The supplier to be added.
     * @return The added supplier.
     */
    public Supplier addSupllier(Supplier supplier) {
    	 int year = Year.now().getValue();
         long auto_id = idGeneration.getNextIdNumber("supplier");
         supplier.setSupplierId(generateSupplierId(auto_id, year));

         supplier.setCreatedOn(new Date());
         supplier.setStatus(Status.ACTIVE.toString().toUpperCase());
        return supplierDao.addSupplier(supplier);
    }
    private String generateSupplierId(long id, int year) {
        if (id <= 9) {
            return "SU000" + id + "" + year;
        } else if (id >= 10 && id <= 99) {
            return "SU00" + id + "" + year;
        } else if (id >= 100 && id <= 999) {
            return "SU0" + id + "" + year;
        } else {
            return "SU" + id + "" + year;
        }
    }

    /**
     * Retrieves a list of all suppliers in the system.
     *
     * @return A list of suppliers.
     */
    public List<Supplier> getSupplies() {
        return supplierDao.getSupplies();
    }

    /**
     * Retrieves a specific supplier by their unique identifier.
     *
     * @param id The unique identifier of the supplier to retrieve.
     * @return The supplier with the specified ID, or null if not found.
     */
    public Supplier getSupplier(String id) {
        return supplierDao.getSupplier(id);
    }
}