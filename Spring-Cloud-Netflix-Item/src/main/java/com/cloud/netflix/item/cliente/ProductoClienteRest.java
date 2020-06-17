package com.cloud.netflix.item.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.netflix.item.model.Producto;

//We declare this interface as a feign rest client, indicating also the name and URL of the remote resource that we want to consume
//@FeignClient(name = "servicio-productos", url="192.168.1.135:8001") When we use Ribbon we can disengage the URL and Port- It will be in application-properties
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
	
	@GetMapping(path = "/list/products")  
	public List<Producto> list();
	
	@GetMapping(path = "/view/{id}")
	public Producto detail(@PathVariable Long id);

}
