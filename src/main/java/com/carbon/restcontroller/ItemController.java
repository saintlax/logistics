package com.carbon.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.carbon.entities.Item;
import com.carbon.models.Response;
import com.carbon.services.ItemService;

@CrossOrigin(origins = "*")
@RestController("itemRestController")
public class ItemController {

	@Autowired
	ItemService itemservice;
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	 public ResponseEntity<?> addItem(@RequestBody Item item){
		Response response = itemservice.addItem(item);
		if(response.getStatusCode() == 200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			
	}
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
	 public ResponseEntity<?> updateItem(@RequestBody Item item){
		Response response = itemservice.updateItem(item);
		if(response.getStatusCode() == 200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			
	}
	
	@RequestMapping(value = "/deleteItem/{itemId}", method = RequestMethod.DELETE)
	 public ResponseEntity<?> deleteItem(@PathVariable long itemId){
		Response response = itemservice.deleteItem(itemId);
		if(response.getStatusCode() == 200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			
	}
	
	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
	 public ResponseEntity<?> getItemById(@PathVariable long itemId){
		Response response = itemservice.getItemById(itemId);
		if(response.getStatusCode() == 200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			
	}
}
