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

@Controller
@RequestMapping(value="/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/", method= RequestMethod.GET)
	@ResponseBody
	public List<Supplier> getSupplies(){
		return supplierService.getSupplies();
		
	}
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	@ResponseBody
	public Supplier getSupplier(@PathVariable String id) {
		return supplierService.getSupplier(id);
	}
	@RequestMapping(value="/",method= RequestMethod.POST)
	@ResponseBody
	public Supplier addSupplier(@RequestBody Supplier Supplier) {
		return supplierService.addSupllier(Supplier);
	}
	
	
	

}
