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
import com.davis.model.Product;
import com.davis.model.ProductCategory;
import com.davis.service.ProductService;

/**
 * Controller class for handling product-related HTTP requests.
 * This class maps incoming requests to appropriate service methods and returns the response.
 *
 * @Controller Indicates that this class is a Spring MVC controller.
 * @RequestMapping Specifies the base URL path for all endpoints in this controller.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Handles a GET request to retrieve all products.
     *
     * @return A list of all products.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     * @ResponseStatus(HttpStatus.OK) Specifies that the HTTP response status will be 200 (OK) if successful.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllproducts() {
        return productService.getProducts();
    }

    /**
     * Handles a GET request to retrieve all product categories.
     *
     * @return A list of all product categories.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductCategory> getProductCategory() {
        return productService.getProductCategories();
    }

    /**
     * Handles a GET request to retrieve a specific product by its SKU (Stock Keeping Unit).
     *
     * @param sku The SKU of the product to retrieve.
     * @return The product with the specified SKU, or null if not found.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     */
    @RequestMapping(value = "/{sku}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable String sku) {
        return productService.getProduct(sku);
    }

    /**
     * Handles a POST request to add a new product.
     *
     * @param product The product to be added, provided in the request body.
     * @return The added product.
     * @ResponseBody Indicates that the return value will be serialized directly into the HTTP response body.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}