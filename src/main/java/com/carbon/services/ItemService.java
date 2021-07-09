package com.carbon.services;

import java.util.List;

import com.carbon.entities.Item;
import com.carbon.enums.Status;
import com.carbon.models.Response;

/**
 * @author Chikadibia Anthony on 9th July 2021
 *
 */
public interface ItemService {
	Response addItem(Item item);
	Response updateItem(Item item);
	Response deleteItem(long itemId);
	Response getItemById(long itemId);
	List<Item> getUserItemsByStatus(long userId, Status status);
}
