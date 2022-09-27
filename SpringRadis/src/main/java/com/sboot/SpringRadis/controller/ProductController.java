package com.sboot.SpringRadis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sboot.SpringRadis.entity.Product;
import com.sboot.SpringRadis.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@PutMapping("/update/{id}")
	@CachePut(key = "#id",value = "Product")
	public Product updateProduct(@RequestBody Product product,@PathVariable int id ) {
		return repository.update(product,id);
	}
	
	@GetMapping
    @Cacheable(key = "#id",value = "Product",unless = "#result.price < 1000")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Object findProduct(@PathVariable int id) {
        return repository.findProductById(id);
    }
    
    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "Product")
    public String remove(@PathVariable int id)   {
    	return repository.deleteProduct(id);
	}
}
