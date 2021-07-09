package com.carbon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.entities.Item;
import com.carbon.enums.Status;

/**
 * @author Chikadibia Anthony on 9th July 2021
 *
 */
@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	List<Item> findByUserId(long userID);
	
	List<Item> findByUserIdAndStatusOrderByIdDesc(long userID, Status status);
}
