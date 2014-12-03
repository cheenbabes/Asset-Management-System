/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.UserNote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {

    private UserInterface userDao;
    private EventInterface eventDao;
    
    @Inject
    public UserController(UserInterface userDao) {
        this.userDao = userDao;
    }
    
    @Inject
    public void setEventDao(EventInterface eventDao) {
        this.eventDao = eventDao;
    }
    
    
    @RequestMapping(value="/viewAllUsers", method = RequestMethod.GET)
    public String viewAllUsers(Model model){
        Set<User> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        return "viewAllUsers";
    }
    
    @RequestMapping(value="/addUser", method = RequestMethod.GET)
    public String displayAddUserForm(Model model){
        model.addAttribute("newUser", new User());
        return "addUser";
    }
    
    @RequestMapping(value="/submitNewUser", method = RequestMethod.POST)
    public String addNewUserToDatabase(@ModelAttribute("newUser") User newUser, Model model, HttpServletRequest request){
        userDao.addUser(newUser);
        String authority = request.getParameter("securityRole");
        userDao.createUserAuthorities(newUser, authority);
        return "redirect:viewAllUsers";
    }
    
    @RequestMapping(value="/deleteUser", method=RequestMethod.GET)
    public String deleteUser(@RequestParam("userId") int userId, Model model, HttpServletRequest req){
        
        userDao.deleteUser(userDao.getUserByUserId(userId));
        return "redirect:viewAllUsers";
    }
    
    @RequestMapping(value="/viewUserInfo", method = RequestMethod.GET)
    public String viewUserInfo(@RequestParam("userId") int userId, Model model){
        List<UserNote> userNotes = userDao.getUserNotes(userId);
        model.addAttribute("userNoteList", userNotes);
        
        User user = userDao.getUserByUserId(userId);
        model.addAttribute("user", user);
        
        Set<Event> userEvents = eventDao.getEventsByUserId(userId);
       
        model.addAttribute("events", userEvents);
        Map<Event, Set<Asset>> EventAndAssets = new HashMap<>();
        
//        List<Asset> eventAssets = new ArrayList<>();
        //List<Event> events = new ArrayList<>();
        
       // events.addAll(eventDao.getEventsByUserId(userId));
//        
//        for (Event e : events) {
//            eventAssets.addAll(eventDao.getAllAssetsForEvent(e));
//        }
        
        for (Event e : userEvents) {
            EventAndAssets.put(e, eventDao.getAllAssetsForEvent(e));
        }

        model.addAttribute("EventAndAssets", EventAndAssets);
        
        return "viewUserInfo";
    }
    
    @RequestMapping(value="/editUser", method = RequestMethod.GET)
    public String viewEditUserPage(@RequestParam("userId") int userId, Model model){
        User userToEdit = userDao.getUserByUserId(userId);
        model.addAttribute("user", userToEdit);
        return "editUser";
    }
    
    @RequestMapping(value="/submitEditUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, Model model){
        userDao.editUser(user);
        return "redirect:viewAllUsers";
    }
    
    @RequestMapping(value="/userAddNote", method = RequestMethod.GET)
    public String displayUserAddNotePage(@RequestParam("userId") int userId, Model model){
        UserNote userNote = new UserNote();
        User user = userDao.getUserByUserId(userId);
        model.addAttribute("user", user);
        userNote.setUserId(userId);
        model.addAttribute(userNote);
        List<UserNote> userNotes = userDao.getUserNotes(userId);
        model.addAttribute("userNoteList", userNotes);
        
        return "userAddNote";
    }
    
    @RequestMapping(value="/submitNewUserNote", method = RequestMethod.POST)
    public String writeUserNoteToDatabase(@ModelAttribute("userNote") UserNote userNote, Model model){
        userDao.addNoteToUser(userNote.getNote(), userNote.getUserId());
        return "redirect:userAddNote?userId=" + userNote.getUserId();
    }
    
    @RequestMapping(value="/reports", method = RequestMethod.GET)
    public String getReports(Model model){
        return "jQueryChart";
    }
}
