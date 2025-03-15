package com.davis.model;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * Represents an Inventory entity in the system.
 * The Inventory class manages stock levels, product details, and associated purchase orders.
 * 
 * @author CYPRIAN DAVIS
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Inventory.viewInventory", query = "SELECT i FROM Inventory i")
})
public class Inventory {

    @Autowired
    private IdGeneration idGeneration;

    @Id
    @Column(name = "StockId")
    private String inventoryId; // Unique identifier for the inventory item

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product; // Product associated with the inventory

   

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier; // Supplier of the product

    @Column(name = "QuantityAvailable")
    private int unitsAvailable; // Quantity of the product available in stock

    private double unitSellingPrice; // Price at which the product is sold

    private int totalUnitsPurchased; // Total units purchased for this inventory

    private int reservedUnits; // Quantity of the product reserved (not available for sale)

    private int incomingQuantity; // Quantity of the product expected to arrive

    @Temporal(TemporalType.DATE)
    private Date expiryDate; // Expiry date of the product

    private int reOrderPoint; // Reorder point for the product

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn; // Date when the inventory was created

    private double totalCost; // Total cost of the inventory

    private String status; // Status of the inventory (e.g., ACTIVE, OUT_OF_STOCK)

    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn; // Date when the inventory was last updated

    /**
     * Initializes the inventory by generating a unique inventory ID,
     * setting the creation date, and setting the default status to "ACTIVE".
     * This method is automatically called after the bean is constructed.
     */
    @PostConstruct
    public void initializeInventoryId() {
        // Generate the inventory ID before the bean is fully initialized
        int year = Year.now().getValue();
        long auto_id = idGeneration.getNextIdNumber("inventoryId");
        this.inventoryId = getInventoryId(auto_id, year);

        // Set date of creation
        LocalDateTime now = LocalDateTime.now();
        this.createdOn = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        // Set status
        this.status = Status.ACTIVE.toString().toUpperCase();
    }

    /**
     * Default constructor for the Inventory class.
     */
    public Inventory() {
    }

    /**
     * Generates a unique inventory ID based on the given ID and year.
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

    /**
     * Returns the inventory ID.
     *
     * @return The inventory ID.
     */
    public String getInventoryId() {
        return inventoryId;
    }

    /**
     * Sets the inventory ID.
     *
     * @param inventoryId The inventory ID to set.
     */
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * Returns the product associated with the inventory.
     *
     * @return The product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the inventory.
     *
     * @param product The product to set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the quantity of the product available in stock.
     *
     * @return The units available.
     */
    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    /**
     * Sets the quantity of the product available in stock.
     * If the units available are less than 1, the status is updated to "OUT_OF_STOCK".
     *
     * @param unitsAvailable The units available to set.
     */
    public void setUnitsAvailable(int unitsAvailable) {
        if (unitsAvailable < 1) {
            this.status = Status.OUT_OF_STOCK.toString().toUpperCase();
        }
        this.unitsAvailable = unitsAvailable;
    }

    /**
     * Returns the reorder point for the product.
     *
     * @return The reorder point.
     */
    public int getReOrderPoint() {
        return reOrderPoint;
    }

    /**
     * Sets the reorder point for the product.
     *
     * @param reOrderPoint The reorder point to set.
     */
    public void setReOrderPoint(int reOrderPoint) {
        this.reOrderPoint = reOrderPoint;
    }

    /**
     * Returns the date when the inventory was created.
     *
     * @return The creation date.
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the date when the inventory was created.
     *
     * @param createdOn The creation date to set.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Returns the date when the inventory was last updated.
     *
     * @return The last updated date.
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Sets the date when the inventory was last updated.
     *
     * @param updatedOn The last updated date to set.
     */
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * Returns the quantity of the product reserved (not available for sale).
     *
     * @return The reserved units.
     */
    public int getReservedUnits() {
        return reservedUnits;
    }

    /**
     * Sets the quantity of the product reserved (not available for sale).
     *
     * @param reservedUnits The reserved units to set.
     */
    public void setReservedUnits(int reservedUnits) {
        this.reservedUnits = reservedUnits;
    }

    /**
     * Returns the quantity of the product expected to arrive.
     *
     * @return The incoming quantity.
     */
    public int getIncomingQuantity() {
        return incomingQuantity;
    }

    /**
     * Sets the quantity of the product expected to arrive.
     *
     * @param incomingQuantity The incoming quantity to set.
     */
    public void setIncomingQuantity(int incomingQuantity) {
        this.incomingQuantity = incomingQuantity;
    }

    /**
     * Returns the price at which the product is sold.
     *
     * @return The unit selling price.
     */
    public double getUnitSellingPrice() {
        return unitSellingPrice;
    }

    /**
     * Sets the price at which the product is sold.
     *
     * @param unitSellingPrice The unit selling price to set.
     */
    public void setUnitSellingPrice(double unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    /**
     * Returns the expiry date of the product.
     *
     * @return The expiry date.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the product.
     *
     * @param expiryDate The expiry date to set.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

   
    /**
     * Returns the status of the inventory.
     *
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the inventory.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the total units purchased for this inventory.
     *
     * @return The total units purchased.
     */
    public int getTotalUnitsPurchased() {
        return totalUnitsPurchased;
    }

    /**
     * Sets the total units purchased for this inventory.
     *
     * @param totalUnitsPurchased The total units purchased to set.
     */
    public void setTotalUnitsPurchased(int totalUnitsPurchased) {
        this.totalUnitsPurchased = totalUnitsPurchased;
    }

    /**
     * Returns the supplier of the product.
     *
     * @return The supplier.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the supplier of the product.
     *
     * @param supplier The supplier to set.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Returns the total cost of the inventory.
     *
     * @return The total cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost of the inventory.
     *
     * @param totalCost The total cost to set.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
