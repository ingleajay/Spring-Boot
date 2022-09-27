package com.sboot.SpringRadis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sboot.SpringRadis.entity.Product;

@Repository
public class ProductRepository {

	    public static final String HASH_KEY = "Product";
	    
	    @Autowired
	    private RedisTemplate template;

	    public Product save(Product product){
	        template.opsForHash().put(HASH_KEY,product.getId(),product);
	        return product;
	    }
	    
	    public Product update(Product product,int id){
	    	System.out.println("called update findProductById() from DB");
	    	template.opsForHash().put(HASH_KEY,id,product);
	        return product;
	    }

	    public List<Product> findAll(){
	        return template.opsForHash().values(HASH_KEY);
	    }

	    public Object findProductById(int id){
	    	System.out.println("called findProductById() from DB");
			Object j = template.opsForHash().get(HASH_KEY,id);
	        return j;
	    }


	    public String deleteProduct(int id){
	    	System.out.println("called delete findProductById() from DB");
	         template.opsForHash().delete(HASH_KEY,id);
	        return "product removed !!";
	    }
}