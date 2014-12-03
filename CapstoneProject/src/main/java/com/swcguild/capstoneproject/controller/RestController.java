/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping("/rest")
public class RestController {

    private EventInterface eventDao;
    private UserInterface userDao;
    private AssetInterface assetDao;

    @Inject
    public void setEventDao(EventInterface eventDao) {
        this.eventDao = eventDao;
    }

    @Inject
    public void setUserDao(UserInterface userDao) {
        this.userDao = userDao;
    }

    @Inject
    public void setAssetDao(AssetInterface assetDao) {
        this.assetDao = assetDao;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model, HttpServletRequest request) {

        Set<Asset> allAssets = assetDao.getAllAssets();
        Set<Asset> checkedOut = new HashSet();
        Set<Asset> checkedIn = new HashSet();
        for (Asset a : allAssets) {
            if (a.isInStock() == false) {
                checkedOut.add(a);
            } else {
                checkedIn.add(a);
            }
        }
        model.addAttribute("checkedOut", checkedOut);
        model.addAttribute("checkedIn", checkedIn);

        return "test";
    }

    @RequestMapping(value = "/assets", method = RequestMethod.GET)
    @ResponseBody
    public Set<Asset> getAllAssets() {

        return assetDao.getAllAvailableAssets();

    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Asset getAssetInfo(@PathVariable("id") int id) {
        return assetDao.getAssetById(id);
    }
//
//    @RequestMapping(value = "/test/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void checkOutInAsset(@PathVariable("id") int id) {
//        Asset asset = assetDao.getAssetById(id);
//        if (asset.isInStock()) {
//            asset.setInStock(false);
//        }
//        else {
//            asset.setInStock(true);
//        }
//        
//        assetDao.editAsset(asset);
//    }

    public Asset createAsset(Asset asset) {
        //rest template like jdbctemplates. hide details we need for ajax calls
        RestTemplate rt = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJacksonHttpMessageConverter());
        //the thing that helps us from java to json for us
        rt.setMessageConverters(converters);
        return rt.postForObject("http://localhost:8080/CapstoneProject/rest/assets", asset, Asset.class);
    }

    @RequestMapping(value = "addEventAssetTest", method = RequestMethod.PUT)
    public void addEventAsset(@PathVariable("id") int id, HttpServletRequest req) {
        Event event = eventDao.getEventByEventId(Integer.parseInt(req.getParameter("eventId")));
        Set<Asset> assetsForEvent = new HashSet();
        assetsForEvent.add(assetDao.getAssetById(id));
        event.setAssets(assetsForEvent);
        eventDao.editEvent(event);
    }

    public AssetNote createAssetNotes(AssetNote assetNote) {
        //rest template like jdbctemplates. hide details we need for ajax calls
        RestTemplate rt = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJacksonHttpMessageConverter());
        //the thing that helps us from java to json for us
        rt.setMessageConverters(converters);
        return rt.postForObject("http://localhost:8080/CapstoneProject/rest/assetNotes", assetNote, AssetNote.class);
    }
    
    @RequestMapping(value = "/assetNotes", method = RequestMethod.GET)
    @ResponseBody
    public List<AssetNote> getAllAssetNotes() {

        Set<Asset> allAssets = assetDao.getAllAssets();

        List<AssetNote> allAssetNotes = new ArrayList<>();

        for (Asset a : allAssets) {
            allAssetNotes.addAll(assetDao.getAssetNotes(a.getAssetId()));
        }

        return allAssetNotes;

    }
    
//    @RequestMapping(value = "/assetNote/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public List<AssetNote> getNotesForAsset( @PathVariable("id") int id) {
//
//        return assetDao.getAssetNotes(id);
//        
//    }
    
    @RequestMapping(value="/assetNote/{id}", method=RequestMethod.GET)
    @ResponseBody public List<AssetNote> getNotes(@PathVariable("id") int id) {
        return assetDao.getAssetNotes(id);
    }
//    
//    @RequestMapping(value = "/findAssetNotes", method = RequestMethod.GET)
//    @ResponseBody
//    public String viewAssetNotesRest(Model model, int assetId, int assetTypeId, int eventId) {
//        //int assetId = Integer.parseInt(req.getParameter("assetId"));
//        
//        //int typeId = Integer.parseInt(req.getParameter("assetTypeId"));
//        //int eventId = Integer.parseInt(req.getParameter("eventId"));
//        List<AssetNote> notes = assetDao.getAssetNotes(assetId);
//        model.addAttribute("notes", notes);
//        return "include/manageEventAssets?typeId=" + assetTypeId + "&eventId=" + eventId + "#viewAssetNotes";
//    }
    
//    @RequestMapping(value = "/assetNote", method = RequestMethod.GET)
//    public String assetNote() {
//
//
//        return "include/manageEventAssets";
//    }

//    @RequestMapping(value = "/assetNote/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<Asset, List<AssetNote>> getAllAssetNotesForEvent(Event event, @PathVariable("id") int id) {
//
//        Map<Asset, List<AssetNote>> noteMap = new HashMap();
//
//        Set<Asset> allAssets = eventDao.getAllAssetsForEvent(eventDao.getEventByEventId(id));
//
//        Set<AssetNote> allAssetNotes = new HashSet<>();
//
//        for (Asset a : allAssets) {
//            noteMap.put(a, assetDao.getAssetNotes(a.getAssetId()));
//        }
//        return noteMap;
//    }

}
