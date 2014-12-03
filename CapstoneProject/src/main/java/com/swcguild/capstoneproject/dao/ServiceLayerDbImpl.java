/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.dao.interfaces.ServiceLayerInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Event;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ServiceLayerDbImpl implements ServiceLayerInterface {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    AssetInterface assetDao;
    EventInterface eventDao;

    @Inject //setter injection
    public void setAssetInterfaceImpl(AssetInterface assetInterfaceImpl) {
        this.assetDao = assetInterfaceImpl;
    }

    @Inject //setter injection
    public void setEventInterfaceImpl(EventInterface eventInterfaceImpl) {
        this.eventDao = eventInterfaceImpl;
    }

    //Prepare the Java 7 date object
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //Prepared Statements
    public static final String SQL_CHECK_OUT_ASSETS_EVENTS
            = "insert into assets_events(asset_id, event_id) values(?,?)";
    public static final String SQL_CHECK_IN_ASSETS_EVENTS
            = "update assets_events set return_date = ? where asset_id = ? and event_id = ?";

    //Method implementations
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) //this should all work, or none of it should
    public void addAssetToEvent(int assetTypeId, int eventId) {
        //get an available asset of a particular AssetType
        //set it to checked out
        //write it to the assets_events table

        //Get the event
        Event currentEvent = eventDao.getEventByEventId(eventId);

        //Get an available asset based on an asset type
        AssetType assetTypeToGet = assetDao.getAssetTypeById(assetTypeId);
        Asset assetToCheckOut = assetDao.getAnyAvailableAssetByAssetType(assetTypeToGet);
        assetToCheckOut.setInStock(false);

        //Add asset to Event
        currentEvent.getAssets().add(assetToCheckOut);

        //write to the database
        jdbcTemplate.update(SQL_CHECK_OUT_ASSETS_EVENTS, assetToCheckOut.getAssetId(), currentEvent.getEventId());

        //we can return the event of the asset here...whatever works
    }

    @Override
    public void checkInAsset(int assetId, int eventId) {
        //set in_stock to true for a particular item for a particular event
        Asset assetToCheckIn = assetDao.getAssetById(assetId);
        assetToCheckIn.setInStock(true);

        //update the correct row in the database
        jdbcTemplate.update(SQL_CHECK_IN_ASSETS_EVENTS, dateFormat.format(new Date()), assetId, eventId);

    }

    @Override
    public void retireAsset(Asset asset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
