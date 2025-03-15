package com.davis.model;

public enum Status {
	PENDING,
	CONFIRMED,
	DELIVERED,
	CANCELLED,
	COMPLETED,
	RETURNED,
	IN_PROGRESS,
	IN_STOCK,
    OUT_OF_STOCK,
    RESERVED,
    DISCONTINUED,
    EXPIRED,
    ACTIVE,
	INACTIVE,
	TERMINATED;


	
	 @Override
	    public String toString() {
	        // Replace underscores with a space for display
	        return name().replace('_', ' ');
	    }

}