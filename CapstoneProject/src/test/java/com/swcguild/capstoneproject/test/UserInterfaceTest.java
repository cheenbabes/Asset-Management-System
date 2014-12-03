/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.test;

import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.UserNote;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class UserInterfaceTest {

    UserInterface userDao;
    JdbcTemplate jdbcT;
    //Set<Event> events;
    User u1;
    User u2;
    User u3;

    public UserInterfaceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException, FileNotFoundException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        userDao = ctx.getBean("userDao", UserInterface.class);

        jdbcT = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        jdbcT.execute("delete from assets_events");
        jdbcT.execute("delete from events");
        jdbcT.execute("delete from users");

        //events = new HashSet<>();
        //events.add(new Event());
        u1 = new User();
        u1.setUserName("testMan1");
        u1.setPassword("thePass0rd");
        u1.setGoodStanding(true);
        u1.setEnabled(1);
        u1.setName("John Doe");

        u2 = new User();
        u2.setUserName("testMan2");
        u2.setPassword("thePass3rd");
        u2.setGoodStanding(true);
        u2.setEnabled(1);
        u2.setName("John Smith");

        u3 = new User();
        u3.setUserName("testLady1");
        u3.setPassword("thePass1rd");
        u3.setGoodStanding(true);
        u3.setEnabled(1);
        u3.setName("Jane Doe");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteUpdateUserTest() {
        User fromStorage;
        boolean failure = false;

        //successfully add and retrieve users
        userDao.addUser(u1);
        fromStorage = userDao.getUserByUserId(u1.getUserId());
        assertEquals(u1, fromStorage);

        userDao.addUser(u2);
        fromStorage = userDao.getUserByUserId(u2.getUserId());
        assertEquals(u2, fromStorage);

        //delete user and confirm removal
        userDao.deleteUser(u1);
        fromStorage = userDao.getUserByUserId(u1.getUserId());
        assertTrue(null == fromStorage);

        //update user and confirm changes
        u3.setUserId(u2.getUserId());
        userDao.editUser(u3);
        fromStorage = userDao.getUserByUserId(u2.getUserId());
        assertEquals(u3, fromStorage);

        //attempt update of non-exstant user and confirm failure
        try {
            userDao.editUser(u1);
            failure = false;
        } catch (Exception e) {
            failure = true;
        }
        assertTrue(failure);
        fromStorage = userDao.getUserByUserId(u1.getUserId());
        assertTrue(null == fromStorage);
    }

    @Test
    public void resetupdatePasswordTest() {
        User fromStorage;
        String resetPw;
        String newPw = "new0";

        userDao.addUser(u1);
        userDao.resetPassword(u1);
        resetPw = u1.getUserName();
        fromStorage = userDao.getUserByUserId(u1.getUserId());
        assertEquals(resetPw, fromStorage.getPassword());

        userDao.changeUserPassword(u1, newPw);
        fromStorage = userDao.getUserByUserId(u1.getUserId());
        assertEquals(newPw, fromStorage.getPassword());

    }

    @Test
    public void addGetUserNote() {
        userDao.addUser(u1);

        String note = "One item was damaged";

        userDao.addNoteToUser(note, u1.getUserId()); //THIS WORKS WITH JDBC FINALLY GEEEEEZ
        List<UserNote> getNote = userDao.getUserNotes(u1.getUserId());
        assertEquals(getNote.size(), 1);

    }
    
    @Test
    public void getAllUsers(){
        userDao.addUser(u1);
        userDao.addUser(u2);
        
        Set<User> userList = userDao.getAllUsers();
        
        assertEquals(userList.size(), 2);
        assertTrue(userList.contains(u1));
        assertTrue(userList.contains(u2));
        
    }
}
