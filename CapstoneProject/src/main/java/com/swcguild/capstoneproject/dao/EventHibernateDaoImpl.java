/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.notes.EventNote;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
@Transactional
public class EventHibernateDaoImpl implements EventInterface {

    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Inject //setter injection
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    @Override
    public void addEvent(Event event) {
        currentSession().save(event);
    }

    @Override
    public void editEvent(Event event) {
        currentSession().update(event);
    }

    @Override
    public void deleteEvent(Event event) {
        currentSession().delete(event);
    }

    @Override
    public Event getEventByEventId(int eventId) {
        return (Event) currentSession().get(Event.class, eventId);
    }

    @Override
    public Set<Event> getEventsByUserId(int userId) {
        List<Event> getEventsByUserIdList = currentSession()
                .createSQLQuery("select * from events where user_id = " + userId)
                .addEntity(Event.class).list();
        return new HashSet<>(getEventsByUserIdList);
    }

    @Override
    public Set<Event> getAllEvents() {
        List<Event> getAllEventsList = currentSession().createCriteria(Event.class).list();
        return new HashSet<>(getAllEventsList);
        
    }

    @Override
    public Set<Asset> getAllAssetsForEvent(Event event) {
        return event.getAssets();
    }

    @Override
    public void closeEvent(Event event) {
        //currentSession().createSQLQuery("update events set is_open = 0 where event_id = " + event.getEventId());
        event.setOpen(false);
        currentSession().update(event);
    }
    
    @Override
    public void openEvent(Event event){
        event.setOpen(true);
        currentSession().update(event);
    }



//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public void addNoteToEvent(String note, int eventId) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("insert into event_notes(event_id, note_detail) values");
//        sb.append("(").append(eventId).append(",").append(note).append(")");
//        currentSession().createSQLQuery(sb.toString());
//    }
//    
//    @Override
//    public List<String> getEventNote(int eventId) {
//        return (List<String>) currentSession()
//                .createSQLQuery("select * from event_notes where event_id =  " + eventId)
//                .addEntity(String.class).list();
//    }
    
    private static final String SQL_ADD_EVENT_NOTE
            = "insert into event_notes (event_id, note_detail) values (?, ?)";
    private static final String SQL_GET_ALL_EVENT_NOTES
            = "select * from event_notes where event_id = ?";

    @Override
    public void addNoteToEvent(String note, int eventId) {
        jdbcTemplate.update(SQL_ADD_EVENT_NOTE, eventId, note);
    }

    @Override
    public List<EventNote> getEventNote(int eventId) {
        return jdbcTemplate.query(SQL_GET_ALL_EVENT_NOTES, new EventNoteMapper(), eventId);
    }
    
    private static final class EventNoteMapper implements RowMapper<EventNote>{

        @Override
        public EventNote mapRow(ResultSet rs, int i) throws SQLException {
            EventNote e = new EventNote();
            e.setEventNoteId(rs.getInt("event_note_id"));
            e.setEventId(rs.getInt("event_id"));
            e.setNote(rs.getString("note_detail"));
            String dateStr = rs.getString("note_date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                e.setNoteDate(formatter.parse(dateStr));
            } catch (ParseException pe) {
                System.out.println("OOPS YOU SCREWED UP--your date is WRONG");
            }
            return e;
        }
        
    }
}
