/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.model.notes;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author apprentice
 */
@Entity
@Table(name="asset_notes")
public class AssetNote {
    @Id
    @GeneratedValue
    @Column(name="asset_note_id")
    private int assetNoteId;
    
    @Column(name="asset_id")
    private int assetId;
    
    @Column(name="note_detail")
    private String note;
    
    @Column(name="category")
    private String category;
    
    @Column(name="note_date")
    private Date noteDate;

    public int getAssetNoteId() {
        return assetNoteId;
    }

    public void setAssetNoteId(int assetNoteId) {
        this.assetNoteId = assetNoteId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.assetNoteId;
        hash = 37 * hash + this.assetId;
        hash = 37 * hash + Objects.hashCode(this.note);
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.noteDate);
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
        final AssetNote other = (AssetNote) obj;
        if (this.assetNoteId != other.assetNoteId) {
            return false;
        }
        if (this.assetId != other.assetId) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.noteDate, other.noteDate)) {
            return false;
        }
        return true;
    }
    
    
          
}
