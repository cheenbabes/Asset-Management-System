/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao.interfaces;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import java.util.Date;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface AssetCheckInServiceInterface {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Date checkInAsset(int eventId, int assetId, String damageStatus);

    public Date getReturnDate(int eventId, int assetId);

    @Inject
    public void setAssetDao(AssetInterface assetDao);

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    
}
