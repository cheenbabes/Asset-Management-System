/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.test;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.EventNote;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
public class EventDaoTest {

    JdbcTemplate jdbcT;
    EventInterface eventDao;
    UserInterface userDao;
    AssetInterface assetDao;
    Event e1;
    User u1;
    Asset a1;
    Asset a2;
    AssetType at1;
    Category c1;

    public EventDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        eventDao = (EventInterface) ctx.getBean("eventDao");
        userDao = (UserInterface) ctx.getBean("userDao");
        assetDao = (AssetInterface) ctx.getBean("assetDao");
        jdbcT = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        jdbcT.execute("delete from assets_events");
        jdbcT.execute("delete from assets");
        jdbcT.execute("delete from asset_types");
        jdbcT.execute("delete from categories");
        jdbcT.execute("delete from events");
        jdbcT.execute("delete from users");

        c1 = new Category();
        c1.setCategoryName("Power Rangers");

        assetDao.addCategory(c1);

        at1 = new AssetType();
        at1.setCategory(c1);
        at1.setName("Jason");

        assetDao.addAssetType(at1);

        a1 = new Asset();
        a1.setAssetType(at1);
        a1.setInStock(true);
        a1.setSerialNumber("456");
        a1.setDamageStatus("Good");

        a2 = new Asset();
        a2.setAssetType(at1);
        a2.setInStock(true);
        a2.setSerialNumber("789");
        a2.setDamageStatus("Bad");

        assetDao.addAsset(a1);
        assetDao.addAsset(a2);

        u1 = new User();
        u1.setGoodStanding(true);
        u1.setName("Name");
        u1.setPassword("password");
        u1.setUserName("userName");

        e1 = new Event();
        e1.setUser(u1);
        e1.setEventName("Party");
        Date date1out = new Date(1997, 07, 24);
        Date date1in = new Date(1997, 07, 26);
        e1.setCheckOutDate(date1out);
        e1.setDueDate(date1in);
        e1.setOpen(true);
        Set<Asset> assetSet = new HashSet<>();
        assetSet.add(a1);
        assetSet.add(a2);
        e1.setAssets(assetSet);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addGetDeleteUpdateEventTest() {
        //System.out.println(dao.getEventByEventId(5).getEventName());
        userDao.addUser(u1);
        eventDao.addEvent(e1);
        assertEquals(eventDao.getEventByEventId(e1.getEventId()).getEventName(), e1.getEventName());

        e1.setEventName("Hysteria");
        eventDao.editEvent(e1);
        assertEquals(eventDao.getEventByEventId(e1.getEventId()).getEventName(), e1.getEventName());

        eventDao.deleteEvent(e1);
        assertNull(eventDao.getEventByEventId(e1.getEventId()));
    }

    @Test
    public void getEventsByUserIdTest() {
        userDao.addUser(u1);
        eventDao.addEvent(e1);
        assertEquals(eventDao.getEventsByUserId(u1.getUserId()).size(), 1);
    }

    @Test
    public void getAllEventsTest() {
        userDao.addUser(u1);
        eventDao.addEvent(e1);
        assertEquals(eventDao.getAllEvents().size(), 1);
    }

    @Test
    public void getAllAssetsForEventTest() {
        userDao.addUser(u1);
        eventDao.addEvent(e1);

        assertEquals(eventDao.getAllAssetsForEvent(e1).size(), 2);
    }

    @Test
    public void closeEventTest() {
        userDao.addUser(u1);
        eventDao.addEvent(e1);

        eventDao.closeEvent(e1);

        assertFalse(e1.isOpen());
    }

    @Test
    public void addGetEventNote() {
        userDao.addUser(u1);
        eventDao.addEvent(e1);

        String note = "One item was damaged";

        eventDao.addNoteToEvent(note, e1.getEventId()); //THIS WORKS WITH JDBC FINALLY GEEEEEZ
        List<EventNote> getNote = eventDao.getEventNote(e1.getEventId());
        assertEquals(getNote.size(), 1);
    }

}
