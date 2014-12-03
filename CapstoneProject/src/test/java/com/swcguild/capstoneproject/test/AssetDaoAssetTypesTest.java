/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.test;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class AssetDaoAssetTypesTest {
    JdbcTemplate jdbcTemplate;
    AssetInterface assetDao;
    
    Category catA;
    Category catB;
    
    AssetType t1;
    AssetType t2;
    AssetType t3;
    
    public AssetDaoAssetTypesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        assetDao = ctx.getBean("assetDao", AssetInterface.class);
        
        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        jdbcTemplate.execute("delete from assets_events");
        jdbcTemplate.execute("delete from assets");
        jdbcTemplate.execute("delete from asset_types");
        jdbcTemplate.execute("delete from categories");
        jdbcTemplate.execute("delete from events");
        jdbcTemplate.execute("delete from users");
        
        catA = new Category();
        catA.setCategoryName("Alpha");
        
        catB = new Category();
        catB.setCategoryName("Beta");
        
        t1 = new AssetType();
        t1.setCategory(catA);
        t1.setName("one");
        t1.setImagePath("../image1.jpg");
        
        t2 = new AssetType();
        t2.setCategory(catA);
        t2.setName("two");
        t2.setImagePath("../image2.jpg");
        
        t3 = new AssetType();
        t3.setCategory(catB);
        t3.setName("three");
        t3.setImagePath("../image3.jpg");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addGetEditDeleteAssetTypeTest(){
        AssetType storedType;
        Set<AssetType> typesExp = new HashSet();
        Set<AssetType> typesAct;
        
        boolean failure;
        
        //addCategories???
        assetDao.addCategory(catA);
        assetDao.addCategory(catB);
        
        //add retrieve asset types
        assetDao.addAssetType(t1);
        storedType = assetDao.getAssetTypeById(t1.getAssetTypeId());
        assertEquals(t1, storedType);
        
        assetDao.addAssetType(t2);
        storedType = assetDao.getAssetTypeById(t2.getAssetTypeId());
        assertEquals(t2, storedType);
        
        assetDao.addAssetType(t3);
        storedType = assetDao.getAssetTypeById(t3.getAssetTypeId());
        assertEquals(t3, storedType);
        
        //retrieve asset types by category
        typesExp.add(t1);
        typesExp.add(t2);
        
        typesAct = assetDao.getAssetTypeByCategory(catA);
        assertTrue(typesAct.containsAll(typesExp));
        
        //confirm exclusion of non-matching asset type
        assertFalse(typesAct.contains(t3));
        
        //delete asset type and confirm removal
        assetDao.deleteAssetType(t1);
        storedType = assetDao.getAssetTypeById(t1.getAssetTypeId());
        assertTrue(storedType == null);
        
        //edit asset type and confirm changes
        t1.setAssetTypeId(t2.getAssetTypeId());
        assetDao.editAssetType(t1);
        storedType = assetDao.getAssetTypeById(t2.getAssetTypeId());
        assertEquals(t1, storedType);
        
        //attempt editing non-extant asset type and confirm failure
        assetDao.deleteAssetType(t3);
        try{
            assetDao.editAssetType(t3);
            failure = false;
        }
        catch(Exception e){
            failure = true;
        }
        
        assertTrue(failure);
        
    }
    
    @Test
    public void getAllAssetTypesTest() {
        assetDao.addCategory(catA);
        assetDao.addCategory(catB);
        assetDao.addAssetType(t1);
        assetDao.addAssetType(t2);
        assetDao.addAssetType(t3);
        
        Set<AssetType> assetTypeSet = new HashSet<>();
        assetTypeSet.add(t1);
        assetTypeSet.add(t2);
        assetTypeSet.add(t3);
        
        assertEquals(assetDao.getAllAssetTypes().size(), assetTypeSet.size());
    }
}
