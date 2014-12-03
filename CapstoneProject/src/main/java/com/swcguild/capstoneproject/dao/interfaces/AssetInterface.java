/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao.interfaces;

import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import java.util.List;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface AssetInterface {
    //This interface contains all the methods necessary for managing the inventory
    //This includes CRUD on Assets, AssetTypes, and Categories

    //Assets
    public void addAsset(Asset asset);

    public void editAsset(Asset asset);

    public void deleteAsset(Asset asset);

    public Asset getAssetById(int assetId);

    public Asset getAnyAvailableAssetByAssetType(AssetType assetType);

    public Set<Asset> getAllAssets();

    //some basic filtering methods
    public Set<Asset> getAllAvailableAssets();

    public Set<Asset> getAllAssetsByAssetType(AssetType assetType);

    public Set<Asset> getAllAvailableAssetsByAssetType(AssetType assetType);

    public void changeAssetDamageStatus(Asset asset, String damageStatus);

    //Asset Types
    public void addAssetType(AssetType assetType);

    public void editAssetType(AssetType assetType);

    public void deleteAssetType(AssetType assetType);

    public AssetType getAssetTypeById(int assetTypeId);
    
    public Set<AssetType> getAllAssetTypes();

    public Set<AssetType> getAssetTypeByCategory(Category category);

    //Categories
    public void addCategory(Category category);

    public void editCategory(Category category);

    public void deleteCategory(Category category);

    public Category getCategoryById(int categoryId);

    public Set<Category> getAllCategories();

    //notes
    public void addNoteToAsset(int assetId, String note, String category);

    public List<AssetNote> getAssetNotes(int assetId);
}
