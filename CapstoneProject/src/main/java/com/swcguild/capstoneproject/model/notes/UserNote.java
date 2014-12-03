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
@Table(name="user_notes")
public class UserNote {
    @Id
    @GeneratedValue
    @Column(name="user_note_id")
    private int userNoteId;
    
    @Column(name="user_id")
    private int userId;
    
    @Column(name="note_detail")
    private String note;
    
    @Column(name ="note_date")
    private Date noteDate;

    public int getUserNoteId() {
        return userNoteId;
    }

    public void setUserNoteId(int userNoteId) {
        this.userNoteId = userNoteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.userNoteId;
        hash = 41 * hash + this.userId;
        hash = 41 * hash + Objects.hashCode(this.note);
        hash = 41 * hash + Objects.hashCode(this.noteDate);
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
        final UserNote other = (UserNote) obj;
        if (this.userNoteId != other.userNoteId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.noteDate, other.noteDate)) {
            return false;
        }
        return true;
    }

  
    
    
}
