package com.project2.mediciERS;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.project2.mediciERS.dao.UserRepositoryDao;
import com.project2.mediciERS.entity.User;
import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepositoryDao userRepositoryDao;
	
	@Test
	public void getAllUsersTest() throws ApplicationException {
		when(userRepositoryDao.findAll()).thenReturn(Stream
				.of(new User(2, "123456", "John", "Smith", "Delaware", "222-222-2222", "employee", false),
						new User(4, "123456", "Deepthi", "Ramana", "Delaware", "333-333-3333", "employee", false)).
				collect(Collectors.toList()));
		assertEquals(2, userService.getAllUsers().size());
		
		}
	
	@Test
	public void deleteAUserTest() throws ApplicationException {
		User user = new User(3, "123456", "Akshith", "Prasad", "Newyork", "444-444-4444", "employee", false);
		userService.deleteUser(3);
		verify(userRepositoryDao, times(1)).deleteById(3);
				
	}
	@Test
	public void updateUserTest() throws ApplicationException {
		User updateUser = new User(3, "123456", "Akshith", "Prasad", "Newyork", "444-444-4444", "employee", false);
		when(userRepositoryDao.save(updateUser)).thenReturn(updateUser);
	}
	@Test
	public void registerTest() throws ApplicationException {
		User newUser = new User(4, "123456", "Aashritha", "Prasad", "Washington DC", "555-555-5555", "employee", false);
		when(userRepositoryDao.save(newUser)).thenReturn(newUser);
	}
	@Test
	public void getAUserTest() throws ApplicationException {
		User user = new User(4, "123456", "Aashritha", "Prasad", "Washington DC", "555-555-5555", "employee", false);
		when(userRepositoryDao.findById(4)).thenReturn(Optional.of(user));
		
		}
	
}
