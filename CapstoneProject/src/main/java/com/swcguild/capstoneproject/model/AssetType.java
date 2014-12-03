/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author apprentice
 */
@Entity
@Table(name="asset_types")
public class AssetType {
    @Id
    @GeneratedValue
    @Column(name="asset_type_id")
    private int assetTypeId;
    
    @Column(name="name")
    @Size(min =3, max = 30, message="Asset type name must be between 3 and 30 characters")
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")//I think this is right
    private Category category;
    
    @Column(name="image_path")
    @Size(min = 4, max =35, message="The image path must be between 4 and 35 characters")
    private String imagePath;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="assetType")
    @JsonIgnore
    //@JsonManagedReference
    private Set<Asset> assets;

    public int getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(int assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.assetTypeId;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.category);
        hash = 61 * hash + Objects.hashCode(this.imagePath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssetType other = (AssetType) obj;
        if (this.assetTypeId != other.assetTypeId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        return true;
    }

    

    
}
