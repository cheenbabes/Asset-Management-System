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
@Table(name="event_notes")
public class EventNote {
    @Id
    @GeneratedValue
    @Column(name="event_note_id")
    private int eventNoteId;
    
    @Column(name="event_id")
    private int eventId;
    
    @Column(name="note_detail")
    private String note;
    
    @Column(name="note_date")
    private Date noteDate;

    public int getEventNoteId() {
        return eventNoteId;
    }

    public void setEventNoteId(int eventNoteId) {
        this.eventNoteId = eventNoteId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
        int hash = 3;
        hash = 31 * hash + this.eventNoteId;
        hash = 31 * hash + this.eventId;
        hash = 31 * hash + Objects.hashCode(this.note);
        hash = 31 * hash + Objects.hashCode(this.noteDate);
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
        final EventNote other = (EventNote) obj;
        if (this.eventNoteId != other.eventNoteId) {
            return false;
        }
        if (this.eventId != other.eventId) {
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
