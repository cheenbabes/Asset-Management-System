/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao.interfaces;

import com.swcguild.capstoneproject.model.Asset;

/**
 *
 * @author apprentice
 */
public interface ServiceLayerInterface {

    //Contains all the methods that require multiple operations and span multiple tables.
    //Most of these relate to the create new event and check-in/check-out assets business functions
   
    public void addAssetToEvent(int assetTypeId, int eventId);
    //This is how you check Items out for events. See impl details on interface impl
    
    public void checkInAsset(int assetId, int eventId);
    //takes an asset Id and event Id and then sets the return date and makes the partocular item available again
    
    public void retireAsset(Asset Asset);

    

}
