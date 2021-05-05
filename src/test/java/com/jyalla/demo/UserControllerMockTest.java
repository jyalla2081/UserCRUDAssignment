package com.jyalla.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import com.jyalla.demo.controller.UserRestController;
import com.jyalla.demo.exception.UserNotFoundException;
import com.jyalla.demo.modal.User;
import com.jyalla.demo.service.BlogService;
import com.jyalla.demo.service.UserService;


@TestMethodOrder(OrderAnnotation.class)
class UserControllerMockTest extends BaseClass {


    public static Logger logger = LoggerFactory.getLogger(UserControllerMockTest.class);

    @Mock
    UserService userService;

    @Mock
    BlogService blogService;

    @InjectMocks
    UserRestController userController;

    @Test
    @Order(1)
    void getUsers() {
        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        User user2 = new User(UUID.fromString("14d5fb10-e63f-4cb9-8c05-4717555cfd47"), "dummyUser2", "dummy2@email.com", "4321", "", true, "Admin");
        when(userService.getAllUsers()).thenReturn(List.of(user, user2));

        ResponseEntity<List<User>> users = userController.getUsers();
        logger.info("getUsers {}", users);
        assertEquals(2, userController.getUsers()
                .getBody()
                .size());
    }

    @Test
    @Order(3)
    void getSingleUser() {
        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        when(userService.getSingleUser(any(UUID.class))).thenReturn(user);
        ResponseEntity<User> oneUser = userController.getOneUser(user.getId());
        logger.info("SingleUser is {}", oneUser);
        assertEquals(true, userController.getUsers()
                .getStatusCode()
                .is2xxSuccessful());
    }

    @Test
    @Order(2)
    void postUser() {

        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        when(userService.save(user)).thenReturn(user);
        when(userService.getSingleUser(user.getId())).thenReturn(user);
        ResponseEntity<Object> saveUser = userController.saveUser(user);
        assertEquals(true, saveUser.getStatusCode()
                .is2xxSuccessful());
    }

    @Test
    @Order(4)
    void putUser() {
        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        when(userService.save(user)).thenReturn(user);
        when(userService.getSingleUser(user.getId())).thenReturn(user);
        ResponseEntity<Object> saveUser = userController.updateUser(user, user.getId());
        assertEquals(true, saveUser.getStatusCode()
                .is2xxSuccessful());
    }

    @Test
    @Order(5)
    void deleteUser() {
        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        doNothing().when(userService)
                .delete(user);
        when(userService.getSingleUser(any(UUID.class))).thenReturn(user);
        ResponseEntity<Object> deleteUser = userController.deleteUser(user.getId());
        assertEquals(true, deleteUser.getStatusCode()
                .is2xxSuccessful());
    }

    @Test()
    @Order(6)
    void userNotFound() {
        User user = new User(UUID.fromString("db47ce58-6f03-4d6d-9902-3837c925406d"), "dummyUser", "dummy@email.com", "1234", "", true, "Admin");
        when(userService.getSingleUser(any(UUID.class))).thenThrow(new UserNotFoundException());
        assertThrows(UserNotFoundException.class, () -> userController.deleteUser(user.getId()));
    }
}