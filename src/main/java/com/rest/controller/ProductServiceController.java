package com.rest.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.rest.exception.*;
import com.rest.model.Products;

@RestController
//@Controller
public class ProductServiceController {
	
	// Everything Can see in POSTMAN (Rest service)
	private static Map <String , Products> productRepo= new HashMap<String, Products>() ;
	
	static List <Products> list= new ArrayList<Products> () ;  
	static {
		Products honey = new Products();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		
		Products honey1 = new Products();
		honey1.setId("2");
		honey1.setName("Honey1");
		productRepo.put(honey1.getId(), honey1);
		
		list.add(honey);
		list.add(honey1);		
	}
	
	@GetMapping("/products")
	public List<Products> getProducts(){
		return list;
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") String id){
		
		if(!productRepo.containsKey(id)) throw new ProductNotFoundException() ;
			
		Products p = productRepo.get(id);
		
		return new ResponseEntity<Object>("Product received " + p.getName(), HttpStatus.OK);
		
	}
	
	@PostMapping("/postproducts")
	public ResponseEntity<Object> postProduct(@RequestBody Products product){
		
		productRepo.put(product.getId(),product);
					
		return new ResponseEntity<Object>("Post successful " + product.getName(), HttpStatus.OK);
		
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> putProduct(@PathVariable("id") String id,@RequestBody Products product){
		
		if(!productRepo.containsKey(id)) throw new ProductNotFoundException() ;
		
		product.setId(id);
		productRepo.put(id,product);
		
		return new ResponseEntity<Object>("Product is updated successful " + product.getName(), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteproducts/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
		
		if(!productRepo.containsKey(id)) throw new ProductNotFoundException() ;
		
		
		productRepo.remove(id);
		
		return new ResponseEntity<Object>("Product is removed successful ", HttpStatus.OK);
		
	}
	
}
