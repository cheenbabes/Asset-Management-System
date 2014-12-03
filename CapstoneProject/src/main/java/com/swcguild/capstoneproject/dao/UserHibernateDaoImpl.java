/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.UserNote;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
@Transactional
public class UserHibernateDaoImpl implements UserInterface {

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
        currentSession().save(user);
    }

    @Override
    public void editUser(User user) {
        currentSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        currentSession().delete(user);
    }

    @Override
    public User getUserByUserId(int userId) {
        return (User) currentSession().get(User.class, userId);
    }

    @Override
    public void resetPassword(User user) {
        user.setPassword(user.getUserName());
        currentSession().update(user);
    }

    @Override
    public void changeUserPassword(User user, String password) { //This probably has some kind of database hashing security along with it
        user.setPassword(password);
        currentSession().update(user);
    }

    @Override
    public Set<User> getAllUsers() {
        List<User> userList = currentSession().createCriteria(User.class).list();
        return new HashSet<>(userList);
    }

    private static final String SQL_ADD_USER_NOTE
            = "insert into user_notes(user_id, note_detail) values (?, ?)";
    private static final String SQL_GET_USER_NOTES
            = "select * from user_notes where user_id = ?";
    private static final String SQL_INSERT_USER_AUTHORITIES
            = "insert into authorities(username, authority) values (?, ?)";
    private static final String SQL_ENABLE_USER
            = "update users set enabled = 1 where user_id = ?";

    @Override
    public void addNoteToUser(String note, int userId) {
        jdbcTemplate.update(SQL_ADD_USER_NOTE, userId, note);
    }

    @Override
    public List<UserNote> getUserNotes(int userId) {
        return jdbcTemplate.query(SQL_GET_USER_NOTES, new UserNoteMapper(), userId);
    }

    private static final class UserNoteMapper implements RowMapper<UserNote> {

        @Override
        public UserNote mapRow(ResultSet rs, int i) throws SQLException {
            UserNote u = new UserNote();
            u.setUserNoteId(rs.getInt("user_note_id"));
            u.setUserId(rs.getInt("user_id"));
            u.setNote(rs.getString("note_detail"));
            String dateStr = rs.getString("note_date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                u.setNoteDate(formatter.parse(dateStr));
            } catch (ParseException pe) {
                System.out.println("OOPS YOU SCREWED UP--your date is WRONG");
            }
            return u;
        }

    }

    @Override
    public void createUserAuthorities(User user, String authority) {
        String userName = user.getUserName();
        switch (authority) {
            case "admin": {
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_ADMIN");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_MANAGER");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_RETAIL");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_USER");
                jdbcTemplate.update(SQL_ENABLE_USER, user.getUserId());
            }
            break;
            case "manager": {
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_MANAGER");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_RETAIL");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_USER");
                jdbcTemplate.update(SQL_ENABLE_USER, user.getUserId());
            }
            break;
            case "retail": {
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_RETAIL");
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_USER");
                jdbcTemplate.update(SQL_ENABLE_USER, user.getUserId());
            }
            break;
            case "user":{
                jdbcTemplate.update(SQL_INSERT_USER_AUTHORITIES, userName, "ROLE_USER");
                jdbcTemplate.update(SQL_ENABLE_USER, user.getUserId());
            }
            break;
        }
    }
//    HIBERNATE GARBAGE, TREAD WITH CARE    

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public void addNoteToUser(String note, int userId) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("insert into user_notes(user_id, note_detail) values");
//        sb.append("(").append(userId).append(",").append(note).append(")");
//        currentSession().createSQLQuery(sb.toString());
//    }
//
//    @Override
//    public List<UserNote> getUserNotes(int userId) {
//        return (List<String>) currentSession()
//                .createSQLQuery("select * from user_notes where user_id = " + userId)
//                .addEntity(String.class).list();
//    }
}
