package com.cloud.netflix.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.netflix.item.model.Item;
import com.cloud.netflix.item.model.Producto;
import com.cloud.netflix.item.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@RestController
public class ItemController {

	@Autowired
	//@Qualifier("serviceFeign") //Specifying a name, Spring knows which one to uses, because we have two classes that implement the same interface.
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	//********
	
	@GetMapping(path = "/list/products")
	public List<Item> list(){
		
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(path = "/view/{id}/cantidad/{cantidad}")
	public Item detail(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		return itemService.findById(id, cantidad);
	}
	
	//Only to test Hystrix providing an alternative method for fault tolerance
	public Item metodoAlternativo(Long id, Integer cantidad) {
		
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setName("Default - Móvil Oppo");
		producto.setPrice(500.00);
		item.setProducto(producto);
		
		return item;
		
	}
}
