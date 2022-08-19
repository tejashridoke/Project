package com.example.demo;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductController {
  
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product Laptop = new Product();
      Laptop.settype("Laptop");
      Laptop.setName("Acer Aspire7");
      productRepo.put(Laptop.gettype(), Laptop);
      
      Product washingMachine = new Product();
      washingMachine.settype("washingMachine");
      washingMachine.setName("Samsung WashingMachine");
      productRepo.put(washingMachine.gettype(), washingMachine);
      
      Product category= new Product();
      category.settype("category");
      category.setName("Electronics");
      productRepo.put(category.gettype(), category);
   }
   
   @RequestMapping(value = "/products/{type}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("type") String type) { 
      productRepo.remove(type);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{type}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("type") String type, @RequestBody Product product) { 
      productRepo.remove(type);
      product.settype(type);
      productRepo.put(type, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      productRepo.put(product.gettype(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
   @RequestMapping(value = "/products/{type}")
   public ResponseEntity<Object> select(@PathVariable("type") String type) { 
      //productRepo.remove(type);
      return new ResponseEntity<>(productRepo.get(type),HttpStatus.OK);
   }
   
}

