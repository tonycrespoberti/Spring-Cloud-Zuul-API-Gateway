package com.cloud.netflix.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cloud.netflix.item.cliente.ProductoClienteRest;
import com.cloud.netflix.item.model.Item;


@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService{
	
	@Autowired
	private ProductoClienteRest clienteFeign;
 
	@Override
	public List<Item> findAll() { 

		return clienteFeign.list().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {

		return new Item(clienteFeign.detail(id), cantidad);
	}

}
