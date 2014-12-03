/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
@Repository
@Transactional
public class AssetHibernateDaoImpl implements AssetInterface {

    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Inject //setter injection
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAsset(Asset asset) {
        currentSession().save(asset);
    }

    @Override
    public void editAsset(Asset asset) {
        currentSession().update(asset);
    }

    @Override
    public void deleteAsset(Asset asset) {
        currentSession().delete(asset);
    }

    @Override
    public Asset getAssetById(int assetId) {
        return (Asset) currentSession().get(Asset.class, assetId);
    }

    @Override
    public Asset getAnyAvailableAssetByAssetType(AssetType assetType) {
        List<Asset> assetToGet = currentSession()
                .createSQLQuery("select * from assets where asset_type_id = " + assetType.getAssetTypeId() + " and in_stock = 1 limit 1")
                .addEntity(Asset.class).list();

        if (assetToGet.isEmpty()) {
            return null;
        } else {

            return assetToGet.get(0);
        }
    }

    @Override
    public Set<Asset> getAllAssets() {
        List<Asset> getAllAssetsList = currentSession().createCriteria(Asset.class).list();
        return new HashSet<>(getAllAssetsList);
    }

    @Override
    public Set<Asset> getAllAvailableAssets() {
        List<Asset> getAllAvailableAssetsList = currentSession().createSQLQuery("select * from assets where in_stock = 1").addEntity(Asset.class).list();
        return new HashSet<>(getAllAvailableAssetsList);
    }

    @Override
    public Set<Asset> getAllAssetsByAssetType(AssetType assetType) {
        List<Asset> getAllAssetsByAssetTypeList = currentSession().createSQLQuery("select * from assets where asset_type_id = " + assetType.getAssetTypeId())
                .addEntity(Asset.class).list();
        return new HashSet<>(getAllAssetsByAssetTypeList);
    }

    @Override
    public Set<Asset> getAllAvailableAssetsByAssetType(AssetType assetType) {
        List<Asset> getAllAvailableAssetsByAssetTypeList = currentSession()
                .createSQLQuery("select * from assets where asset_type_id = " + assetType.getAssetTypeId() + " and in_stock = 1")
                .addEntity(Asset.class).list();
        return new HashSet<>(getAllAvailableAssetsByAssetTypeList);
    }

    @Override
    public void changeAssetDamageStatus(Asset asset, String damageStatus) {
        asset.setDamageStatus(damageStatus);
        currentSession().update(asset);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAssetType(AssetType assetType) {
        currentSession().save(assetType);
    }

    @Override
    public void editAssetType(AssetType assetType) {
        currentSession().update(assetType);
    }

    @Override
    public void deleteAssetType(AssetType assetType) {
        currentSession().delete(assetType);
    }

    @Override
    public AssetType getAssetTypeById(int assetTypeId) {
        return (AssetType) currentSession().get(AssetType.class, assetTypeId);
    }

    public Set<AssetType> getAllAssetTypes() {
        List<AssetType> assetTypeList = currentSession()
                .createSQLQuery("select * from asset_types")
                .addEntity(AssetType.class).list();
        return new HashSet(assetTypeList);
    }

    @Override
    public Set<AssetType> getAssetTypeByCategory(Category category) {
        List<AssetType> assetTypeList = currentSession()
                .createSQLQuery("select * from asset_types where category_id =" + category.getCategoryId())
                .addEntity(AssetType.class).list();
        return new HashSet(assetTypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCategory(Category category) {
        currentSession().save(category);
    }

    @Override
    public void editCategory(Category category) {
        currentSession().update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        currentSession().delete(category);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return (Category) currentSession().get(Category.class, categoryId);
    }

    @Override
    public Set<Category> getAllCategories() {
        List<Category> getAllCategoriesList = currentSession().createCriteria(Category.class).list();
        return new HashSet<>(getAllCategoriesList);
    }

    //Notes...these will be hacked together with the JDBC Template. 
    private static final String SQL_INSERT_ASSET_NOTE
            = "insert into asset_notes (asset_id, note_detail, note_category) values (?, ?, ?)";
    private static final String SQL_GET_ALL_NOTES_FOR_ASSET
            = "select * from asset_notes where asset_id = ?";

    @Override
    public void addNoteToAsset(int assetId, String note, String category) {
        jdbcTemplate.update(SQL_INSERT_ASSET_NOTE, assetId, note, category);
    }

    @Override
    public List<AssetNote> getAssetNotes(int assetId) {
        return jdbcTemplate.query(SQL_GET_ALL_NOTES_FOR_ASSET, new AssetNoteMapper(), assetId);
    }

    private static final class AssetNoteMapper implements RowMapper<AssetNote> {

        @Override
        public AssetNote mapRow(ResultSet rs, int i) throws SQLException {
            AssetNote n = new AssetNote();
            n.setAssetNoteId(rs.getInt("asset_note_id"));
            n.setAssetId(rs.getInt("asset_id"));
            n.setCategory(rs.getString("note_category"));
            n.setNote(rs.getString("note_detail"));
            //some really ugly Java 7 Date stuff ughhh
            String dateStr = rs.getString("note_date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                n.setNoteDate(formatter.parse(dateStr));
            } catch (ParseException pe) {
                System.out.println("OOPS YOU SCREWED UP--your date is WRONG");
            }
            return n;

        }

    }

//    The ruins of some kind of Hibernate hack implementation, uncomment at your own risk
//    
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public void addNoteToAsset(int assetId, String note, String category) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("insert into asset_notes (asset_id, note_detail, note_category) values");
//        String secondLine = " (" + assetId + ", " + "\"" + note + "\", " + "\"" + category + "\"" + ")";
//        sb.append(secondLine);
//        currentSession().createSQLQuery(sb.toString());
//    }
//
//    @Override
//    public List<String> getAssetNotes(int assetId) {
//        return (List<String>) currentSession()
//                .createSQLQuery("select * from asset_notes where asset_id = " + assetId)
//                .list();
//    }
}
