/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao.interfaces;

import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.UserNote;
import java.util.List;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface UserInterface {

    public void addUser(User user);

    public void editUser(User user);

    public void deleteUser(User user);

    public User getUserByUserId(int userId);

    public Set<User> getAllUsers();

    public void resetPassword(User user);

    public void changeUserPassword(User user, String password);

    public void addNoteToUser(String note, int userId);

    public List<UserNote> getUserNotes(int userId);

    public void createUserAuthorities(User user, String authority);
}
