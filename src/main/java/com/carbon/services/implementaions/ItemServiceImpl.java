package com.carbon.services.implementaions;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbon.entities.Item;
import com.carbon.enums.Status;
import com.carbon.models.Response;
import com.carbon.repositories.ItemRepository;
import com.carbon.services.ItemService;


/**
 * @author Chikadibia Anthony on 9th July 2021
 *
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public Response addItem(Item item) {
		Response response = new Response();
		// you can't add a delivered item
		if(item.getStatus() != Status.DELIVERED) {
			itemRepository.save(item);
			response.setPayload(item);
			response.setStatusCode(200);
		}else {
			response.setError("Items marked as Delivered cannot be added");
			response.setStatusCode(400);
		}
			
		return response;
	}

	@Override
	public Response updateItem(Item item) {
		Response response = new Response();		
		Optional<Item> optional = itemRepository.findById(item.getId());
		if(optional.isPresent()) {
			Item dbItem = optional.get();
			switch(dbItem.getStatus()) {
			case WAREHOUSE: case IN_TRANSIT:
				// item in warehouse can be set back to warehouse or put in transit
				if(item.getStatus() == Status.WAREHOUSE || item.getStatus() == Status.IN_TRANSIT || item.getStatus() == Status.PICKED_UP) {
					itemRepository.save(item);
					response.setPayload(item);
					response.setStatusCode(200);
				}
			case PICKED_UP:
				if(item.getStatus() == Status.DELIVERED ) {
					response.setError("it’s not possible to be PICKED_UP and DELIVERED");
					response.setStatusCode(400);
				}
			case DELIVERED:
				if(item.getStatus() == Status.PICKED_UP ) {
					response.setError("it’s not possible to be PICKED_UP and DELIVERED");
					response.setStatusCode(400);
				}
				break;
			default:
				response.setError("Conditions beyond the scope of this test... lol");
				response.setStatusCode(512);
				break;				
			}
			
		}
		
		return response;
	}

	@Override
	public Response deleteItem(long itemId) {
		Response response = new Response();
		itemRepository.deleteById(itemId);
		response.setPayload("Item deleted successfully");
		response.setStatusCode(200);
		return response;
	}

	@Override
	public List<Item> getUserItemsByStatus(long userId, Status status) {
		return itemRepository.findByUserIdAndStatusOrderByIdDesc(userId, status);
	}

	@Override
	public Response getItemById(long itemId) {
		Response response = new Response();
		response.setError("No item was found");
		response.setStatusCode(400);
		Optional<Item> optional = itemRepository.findById(itemId);
		if(optional.isPresent()) {
			Item item = optional.get();
			response.setError(null);
			response.setPayload(item);
			response.setStatusCode(200);			
		}
		return response;
	}

}
