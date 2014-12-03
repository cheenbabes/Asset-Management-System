/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.test;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class AssetDaoTest {

    AssetInterface dao;
    JdbcTemplate jdbcTemplate;

    Category Tents;
    AssetType t1;
    Asset a;
    Asset c;
    Asset d;

    public AssetDaoTest() {
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
        dao = (AssetInterface) ctx.getBean("assetDao");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

        jdbcTemplate.execute("delete from assets_events");
        jdbcTemplate.execute("delete from assets");
        jdbcTemplate.execute("delete from asset_types");
        jdbcTemplate.execute("delete from categories");
        jdbcTemplate.execute("delete from events");
        jdbcTemplate.execute("delete from users");

        Tents = new Category();
        Tents.setCategoryName("Tents");

        t1 = new AssetType();
        t1.setName("Tent");
        t1.setCategory(Tents);
        t1.setImagePath("tentImage.jpg");

        a = new Asset();
        a.setAssetType(t1);
        a.setSerialNumber("111");
        a.setInStock(true);
        a.setDamageStatus("");

        c = new Asset();
        c.setAssetType(t1);
        c.setSerialNumber("222");
        c.setInStock(false);
        c.setDamageStatus("Damaged");

        d = new Asset();
        d.setAssetType(t1);
        d.setSerialNumber("333");
        d.setInStock(true);
        d.setDamageStatus("");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetCategory() {
        dao.addCategory(Tents);
        Category someOtherCat = dao.getCategoryById(Tents.getCategoryId());
        assertEquals(Tents, someOtherCat);

    }

    @Test
    public void editCategory() {
        dao.addCategory(Tents);
        Tents.setCategoryName("Sleeping Bags");
        dao.editCategory(Tents);
        Category toCheck = dao.getCategoryById(Tents.getCategoryId());
        assertEquals(toCheck.getCategoryName(), "Sleeping Bags");
    }

    @Test
    public void deleteCategory() {
        dao.addCategory(Tents);
        dao.deleteCategory(Tents);
        Category toCheck = dao.getCategoryById(Tents.getCategoryId());
        assertNull(toCheck);
    }

    @Test
    public void getAllCategories() {
        dao.addCategory(Tents);
        Set<Category> categorySet = dao.getAllCategories();
        assertTrue(categorySet.contains(Tents));
    }

    //Assets
    @Test
    public void addGetAssetTest() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        Asset b = dao.getAssetById(a.getAssetId());
        assertEquals(a, b);
    }

    @Test
    public void editAsset() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        a.setDamageStatus("Damaged");
        dao.editAsset(a);
        assertEquals(a.getDamageStatus(), "Damaged");
    }

    @Test
    public void deleteAsset() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.deleteAsset(a);
        Asset toCheck = dao.getAssetById(a.getAssetId());
        assertNull(toCheck);
    }

    @Test
    public void getAllAssets() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Set<Asset> assetSet = dao.getAllAssets();
        assertEquals(assetSet.size(), 3);

        assertTrue(assetSet.contains(a));
        assertTrue(assetSet.contains(c));
        assertTrue(assetSet.contains(d));
    }

    @Test
    public void getAnyAvailableAssetByAssetTypeTest() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Asset toCheck = dao.getAnyAvailableAssetByAssetType(t1);
        assertTrue(toCheck.isInStock());
    }

    //What about when you want to get an available asset but there is none?
    @Test
    public void getAnUnavailableAssetTest() {
        
    }

    @Test
    public void getAllAvailableAssetsTest() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Set<Asset> checkedInAssets = dao.getAllAvailableAssets();

        assertEquals(checkedInAssets.size(), 2);
        assertTrue(checkedInAssets.contains(a));
        assertTrue(checkedInAssets.contains(d));
    }

    @Test
    public void getAllAssetsByAssetTypeTest() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Set<Asset> assets = dao.getAllAssetsByAssetType(t1);

        assertEquals(assets.size(), 3);
    }

    @Test
    public void getAllAvailableAssetsByAssetType() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Set<Asset> assets = dao.getAllAvailableAssetsByAssetType(t1);

        assertEquals(assets.size(), 2);
    }

    @Test
    public void changeDamageStatusForAnAssetTest() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        dao.addAsset(c);
        dao.addAsset(d);

        Asset changeThisOne = dao.getAssetById(a.getAssetId());
        dao.changeAssetDamageStatus(a, "Damaged");

        assertEquals(a.getDamageStatus(), "Damaged");

    }

    //Asset notes
    @Test
    public void addGetAssetNote() {
        dao.addCategory(Tents);
        dao.addAssetType(t1);
        dao.addAsset(a);
        
        String note = "This item is damaged";
        String category = "Damage";
        
        dao.addNoteToAsset(a.getAssetId(), note, category); //THIS WORKS WITH JDBC FINALLY GEEEEEZ
        List<AssetNote> getNote = dao.getAssetNotes(a.getAssetId());
        assertEquals(getNote.size(), 1);
        

    }

}
