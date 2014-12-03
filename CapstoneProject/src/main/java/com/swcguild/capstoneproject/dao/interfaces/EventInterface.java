/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao.interfaces;

import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.notes.EventNote;
import java.util.List;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface EventInterface {

    public void addEvent(Event event);

    public void editEvent(Event event);

    public void deleteEvent(Event event);

    public Event getEventByEventId(int eventId);

    public Set<Event> getEventsByUserId(int userId);

    public Set<Event> getAllEvents();

    public Set<Asset> getAllAssetsForEvent(Event event);

    public void closeEvent(Event event);
    
    public void openEvent(Event event);

    public void addNoteToEvent(String note, int eventId);

    public List<EventNote> getEventNote(int eventId);

}
