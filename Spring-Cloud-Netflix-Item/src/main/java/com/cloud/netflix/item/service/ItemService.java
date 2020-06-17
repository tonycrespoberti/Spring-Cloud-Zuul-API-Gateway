package com.cloud.netflix.item.service;

import java.util.List;

import com.cloud.netflix.item.model.Item;


public interface ItemService {

	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	
}
