package com.example.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Optionals;

import com.example.inventory.entity.Inventory;
import com.example.inventory.entity.Login;
import com.example.inventory.entity.Orders;
import com.example.inventory.entity.RestInventor;
import com.example.inventory.entity.Users;
import com.example.inventory.model.Registration;
import com.example.inventory.repos.EditInventoryRepo;
import com.example.inventory.repos.InventoryRepo;
import com.example.inventory.repos.LoginRepo;
import com.example.inventory.repos.OrderRepo;
import com.example.inventory.repos.UserRepo;
import com.example.inventory.services.Services;

@SpringBootTest
class InventoryApplicationTests {

	@Autowired
	private Services services;
	
	@MockBean
	private EditInventoryRepo restrepo;
	
	@MockBean
	private InventoryRepo inventoryrepo;
	
	@MockBean
	private LoginRepo loginrepo;
	
	@MockBean
	private OrderRepo orderrepo;
	
	@MockBean
	private UserRepo userrepo;
	
	final private List<Users> users = Stream.of(new Users("Mahi1234","Mahitha","1893298392","mahita@gmail.com","admin",new Date()),
			new Users("indran","indran","1893298392","mahita@gmail.com","user",new Date())).toList();
	
	final private List<Login> logins = Stream.of(new Login("Mahi1234","1"),new Login("indran","1")).toList();
	
	final private List<Inventory> inventory = Stream.of(new Inventory(1,1234,"samsung",8,2,6,new Date()),
			new Inventory(2,1234,"google",8,2,6,new Date())).toList();
	
	@Test
	public void authentication() {
		
		when(loginrepo.findById("indran")).thenReturn(Optional.of(new Login("indran","1")));
		when(userrepo.findByUserId("indran")).thenReturn(
				Optional.of(new Users("indran","indran","1893298392","mahita@gmail.com","user",new Date())));
		
		
		assertEquals(services.authentication(new Login("indran","1")), "user@indran");
		
	}
	
	@Test
	public void registration() {
		
		when(userrepo.save(null)).thenReturn(null);
		when(loginrepo.save(null)).thenReturn(null);
		
		assertEquals(services.registration(
				new Registration("indran","1234567890","indrang010@gmail.com","user",new Date(),"1")
				),true);
	}

	@Test
	public void editInventory() {
		
		when(restrepo.save(null)).thenReturn(null);
		when(inventoryrepo.save(null)).thenReturn(inventory);
		when(inventoryrepo.findByMaterialIdAndLocationNumber("google",new Long(1234))).thenReturn(
				new Inventory(2,890,"google",8,2,6,new Date())
				);
		
		assertEquals(services.editInventory(new RestInventor("google",6,new Long(1234),new Date())),"Updated!!!");
	}
	
	@Test 
	public void acceptOrders() {
		when(orderrepo.save(null)).thenReturn(null);
		when(inventoryrepo.findByMaterialIdAndLocationNumber("google",new Long(1234))).thenReturn(
				new Inventory(2,890,"google",8,2,6,new Date()));
		
		when(inventoryrepo.save(null)).thenReturn(null);
		when(userrepo.findByUserId("indran")).thenReturn(
				Optional.of(new Users("indran","indran","1893298392","mahita@gmail.com","user",new Date())));
		
		assertEquals(services.acceptOrders(
				"indran", new Orders("indran",new Date(),new Time(100),new Long(1234),"google",new Long(3),"")), "Order Successful!!! :>");
		
	}
	
	@Test
	public void getInventorys() {
		when(inventoryrepo.findAllByLocationNumber(new Long(1234))).thenReturn(inventory);
		
		assertEquals(2, services.getInventorys(new Long(1234)).size());
	}
	
	@Test
	public void getInventory() {
		when(inventoryrepo.findByMaterialIdAndLocationNumber("google",new Long(1234))).thenReturn(
				new Inventory(2,890,"google",8,2,6,new Date()));
		
		assertNotNull(services.getInventory( new Long(1234),"google"));
		
	}
}
