/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.dao.interfaces.AssetCheckInServiceInterface;
import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.model.Asset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
@Repository
public class AssetCheckInService implements AssetCheckInServiceInterface {

//    private static final String SQL_CHECK_IN_ASSET = "update assets_events set return_date = ? where event_id = ? and asset_id = ?;";
    private static final String SQL_CHECK_IN_ASSET_WITH_DAMAGE = "update assets_events set return_date = ?, damage_loss_theft = ? where event_id = ? and asset_id = ?;";
    private static final String SQL_SELECT_RETURN_DATE = "select return_date from assets_events where event_id = ? and asset_id = ?;";

    private List<String> statList;
    private JdbcTemplate jdbcTemplate;
    private AssetInterface assetDao;

    public AssetCheckInService() {
        this.statList = new ArrayList<>();
        statList.add("none");
//        statList.add("Late");
        statList.add("Damage: 1");
        statList.add("Damage: 2");
        statList.add("Damage: 3");
        statList.add("Damage: 4");
        statList.add("Damage: 5");
        statList.add("Lost");
        statList.add("Stolen");
    }

    
    
    @Inject
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Inject
    @Override
    public void setAssetDao(AssetInterface assetDao) {
        this.assetDao = assetDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Date checkInAsset(int eventId, int assetId, String damageStatus) {
        Asset asset;
        Date today = new Date();
        Date existingDate = getReturnDate(eventId, assetId);

        if (existingDate == null) {
            jdbcTemplate.update(SQL_CHECK_IN_ASSET_WITH_DAMAGE, today, damageStatus, eventId, assetId);
//            jdbcTemplate.update(SQL_CHECK_IN_ASSET, today, eventId, assetId);
            asset = assetDao.getAssetById(assetId);
            asset.setInStock(true);
            if(damageIncreased(asset, damageStatus)){
                asset.setDamageStatus(damageStatus);
            }
            assetDao.editAsset(asset);
            return today;
        } else {
            return existingDate;
        }

    }

    @Override
    public Date getReturnDate(int eventId, int assetId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_RETURN_DATE, Date.class, eventId, assetId);
    }
    
    private boolean damageIncreased(Asset asset, String damageStatus){
        int oldRank=statList.size();
        int currentRank=0;
        for(int i = 0; i<statList.size();i++){
            if(damageStatus.equalsIgnoreCase(statList.get(i))){
                currentRank=i;
            }
            if(asset.getDamageStatus().equalsIgnoreCase(statList.get(i))){
                oldRank=i;
            }
        }
        
        return currentRank > oldRank;
    }
}
