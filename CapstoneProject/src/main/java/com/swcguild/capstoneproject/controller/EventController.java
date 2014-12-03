/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.AssetCheckInService;
import com.swcguild.capstoneproject.dao.interfaces.AssetCheckInServiceInterface;
import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.dao.interfaces.UserInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.User;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import com.swcguild.capstoneproject.model.notes.EventNote;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.concurrent.ThreadLocalRandom.current;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
public class EventController {

    private static final String BAD_ASSET_TYPE_ERROR_MESSAGE = "Oops! Invalid asset type ID. Must supply asset type ID as an integer. Asset type ID must refer to an existing asset type.";
    private static final String BAD_ASSET_ERROR_MESSAGE = "Oops! Invalid asset ID. Must supply asset ID as an integer. Asset ID must refer to an existing asset.";
    private static final String BAD_EVENT_ERROR_MESSAGE = "Oops! Invalid event ID. Must supply event ID as an integer. Event ID must refer to an existing Event.";
    private static final String NO_SUCH_ASSET_IN_EVENT = "Oops! This asset is not associated with this event. Please make sure you have the correct asset Id";
    private static final String NO_SUCH_ASSET_AVAILABLE_PART1 = "I'm sorry, but we currently do not have any ";
    private static final String NO_SUCH_ASSET_AVAILABLE_PART2 = "(s) available.";

    private EventInterface eventDao;
    private UserInterface userDao;
    private AssetInterface assetDao;
    private AssetCheckInServiceInterface checkIn;

    //Setters
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

    @Inject
    public void setCheckIn(AssetCheckInServiceInterface checkIn) {
        this.checkIn = checkIn;
    }

    //Event Creation Methods
    @RequestMapping(value = "/addEvent", method = RequestMethod.GET)
    public String addEventStepOne(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        //Date current = new Date();
        LocalDate current = LocalDate.now();
        current.format(DateTimeFormatter.ISO_LOCAL_DATE);
        model.addAttribute("startDate", current);

        Set<User> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);

        return "addEventStepOne";
    }

    @RequestMapping(value = "/submitAddEventStepOne", method = RequestMethod.POST)
    public String createEventStepOne(Model model, @ModelAttribute("event") Event event, HttpServletRequest req) {
        
        int userId = Integer.parseInt(req.getParameter("userName"));
        User eventUser = userDao.getUserByUserId(userId);

        event.setUser(eventUser);
        event.setOpen(true);

        eventDao.addEvent(event);

        model = supplyModelAttributes(model, event);
        

        return "addEventStepTwo";
    }

    @RequestMapping(value = "addEventAsset", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addEventAsset(Model model, HttpServletRequest request) {
        int typeId;
        Event event;
        AssetType assetType;
        Asset asset;
        boolean nullVal;
        Set<Asset> eventAssets = new HashSet<>();
        Map<Asset, List<AssetNote>> assetNotes = new HashMap<>();

        //attempt to retrieve Event by Id
        event = retrieveEventById(request);

        //resolve invalid eventId
        nullVal = event == null;
        if (nullVal) {
            model.addAttribute("badEventError", BAD_EVENT_ERROR_MESSAGE);

            for (Asset a : eventAssets) {
                assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
            }
            model.addAttribute("assetNotes", assetNotes);
            return "addEventStepTwo";
        }

        //supply model attributes
        model = supplyModelAttributes(model, event);

        //attempt to retrieve AssetType by Id
        try {
            typeId = Integer.parseInt(request.getParameter("typeId"));
        } catch (NumberFormatException e) {
            typeId = 0;
        }

        assetType = assetDao.getAssetTypeById(typeId);

        //resolve invalid assetTypeId
        nullVal = assetType == null;
        if (nullVal) {
            if (nullVal) {
                model.addAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);

                for (Asset a : eventAssets) {
                    assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
                }
                model.addAttribute("assetNotes", assetNotes);
            }
            return "addEventStepTwo";
        }

        //attempt to retrieve available asset by assetType
        asset = assetDao.getAnyAvailableAssetByAssetType(assetType);
        //resolve unavailable asset
        nullVal = asset == null;
        if (nullVal) {
            if (nullVal) {
                model.addAttribute("unavailableAssetError", NO_SUCH_ASSET_AVAILABLE_PART1 + assetType.getName() + NO_SUCH_ASSET_AVAILABLE_PART2);

                eventAssets = eventDao.getAllAssetsForEvent(event);
                for (Asset a : eventAssets) {
                    assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
                }
                model.addAttribute("assetNotes", assetNotes);
            }
            return "addEventStepTwo";
        }
        asset.setInStock(false);
        assetDao.editAsset(asset);

        //retrieve assets associated with event
        eventAssets = event.getAssets();
        if (eventAssets == null) {
            eventAssets = new HashSet<>();
        }

        //assign new asset to event
        eventAssets.add(asset);
        event.setAssets(eventAssets);
        eventDao.editEvent(event);

        for (Asset a : eventAssets) {
            assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
        }
        model.addAttribute("assetNotes", assetNotes);

        return "addEventStepTwo";
    }

    @RequestMapping(value = "removeAssetFromEvent", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String removeAssetFromEvent(Model model, HttpServletRequest request) {
        int assetId;
        Event event = retrieveEventById(request);
        Asset asset;
        Set<Asset> eventAssets;
        Map<Asset, List<AssetNote>> assetNotes = new HashMap<>();

        //resolve invalid eventId
        if (event == null) {
            model.addAttribute("badEventError", BAD_EVENT_ERROR_MESSAGE);
            return "addEventStepTwo";
        }

        //supply model attributes
        model = supplyModelAttributes(model, event);

        //retrieve asset
        try {
            assetId = Integer.parseInt(request.getParameter("assetId"));
        } catch (NumberFormatException e) {
            assetId = 0;
        }

        asset = assetDao.getAssetById(assetId);

        //resolve invalid assetId
        if (asset == null) {
            model.addAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "addEventStepTwo";
        }

        //retrieve assets associated with event
        eventAssets = event.getAssets();
        if (eventAssets == null) {
            eventAssets = new HashSet<>();
        }

        if (!eventAssets.remove(asset)) {
            model.addAttribute("badAssetError", NO_SUCH_ASSET_IN_EVENT);
            return "addEventStepTwo";
        } else {
            event.setAssets(eventAssets);
            eventDao.editEvent(event);
            asset.setInStock(true);
            assetDao.editAsset(asset);
        }

        for (Asset a : eventAssets) {
            assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
        }
        model.addAttribute("assetNotes", assetNotes);

        return "addEventStepTwo";

    }

    //Open/ Close Events
    @RequestMapping(value = "/closeEvent", method = RequestMethod.GET)
    public String closeEvent(Model model, @RequestParam("eventId") int eventId) {
        Event eventToClose = eventDao.getEventByEventId(eventId);
        eventDao.closeEvent(eventToClose);
        return "redirect:index";
    }

    @RequestMapping(value = "/openEvent", method = RequestMethod.GET)
    public String openEvent(Model model, @RequestParam("eventId") int eventId) {
        Event eventToOpen = eventDao.getEventByEventId(eventId);
        eventDao.openEvent(eventToOpen);
        return "redirect:index";
    }

    //View Event Info
    @RequestMapping(value = "/viewEventInfo", method = RequestMethod.GET)
    public String showEventInfo(Model model, @RequestParam("eventId") int eventId) {
        Event event = eventDao.getEventByEventId(eventId);
        Map<Asset, List<AssetNote>> assetNotes = new HashMap<>();
        Set<Asset> eventAssets = eventDao.getAllAssetsForEvent(event);
        for (Asset a : eventAssets) {
            assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
        }
        model.addAttribute("assetNotes", assetNotes);
        
        model.addAttribute("event", event);
        return "viewEventInfo";
    }

    //Add Event Notes
    @RequestMapping(value = "/eventAddNote", method = RequestMethod.GET)
    public String showEventNotes(Model model, @RequestParam("eventId") int eventId) {
        Event event = eventDao.getEventByEventId(eventId);
        model.addAttribute("event", event);

        EventNote eventNote = new EventNote();
        eventNote.setEventId(eventId);
        model.addAttribute("eventNote", eventNote);

        List<EventNote> eventNotes = eventDao.getEventNote(eventId);
        model.addAttribute("eventNoteList", eventNotes);

        return "eventAddNote";
    }

    @RequestMapping(value = "/submitNewEventNote", method = RequestMethod.POST)
    public String addEventNoteToDatabase(Model model, @ModelAttribute("eventNote") EventNote eventNote) {
        eventDao.addNoteToEvent(eventNote.getNote(), eventNote.getEventId());
        return "redirect:eventAddNote?eventId=" + eventNote.getEventId();
    }

    //Event Editing Methods
    @RequestMapping(value = "/editEvent", method = RequestMethod.GET)
    public String showEditEventPage(Model model, HttpServletRequest request) {
        int eventId;
        String badAssetError;
        List<Date> returnDates = new ArrayList<>();
        Event eventToEdit;
        List<Asset> assetsCheckedOutForEvent;
        Set<Category> categoryList;
        Set<AssetType> assetTypeList;
        Set<User> userList;

        //retrieve selected event
        try {
            eventId = Integer.parseInt(request.getParameter("eventId"));
        } catch (NumberFormatException e) {
            eventId = 0;
        }

        eventToEdit = eventDao.getEventByEventId(eventId);

        if (eventToEdit == null) {
            model.addAttribute("badEventError", BAD_EVENT_ERROR_MESSAGE);
            return "redirect:home";
        }
        model.addAttribute("event", eventToEdit);

        //retrieve asset associated with event
        assetsCheckedOutForEvent = new ArrayList<>();
        assetsCheckedOutForEvent.addAll(eventDao.getAllAssetsForEvent(eventToEdit));
        model.addAttribute("assetCheckedOutList", assetsCheckedOutForEvent);
        Map<Asset, List<AssetNote>> assetNotes = new HashMap<>();

        //retrieve return dates (if any) for each asset in order
        for (Asset a : assetsCheckedOutForEvent) {
            returnDates.add(checkIn.getReturnDate(eventId, a.getAssetId()));
        }
        model.addAttribute("returnDates", returnDates);

        //retrieve categories, asset types, and users
        categoryList = assetDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        assetTypeList = assetDao.getAllAssetTypes();
        model.addAttribute("assetTypeList", assetTypeList);
        
        userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);

        //error message
        badAssetError = request.getParameter("badAssetError");
        model.addAttribute("badAssetError", badAssetError);
        
        for (Asset a : assetsCheckedOutForEvent) {
            assetNotes.put(a, assetDao.getAssetNotes(a.getAssetId()));
        }
        model.addAttribute("assetNotes", assetNotes);

        return "editEvent";
    }

    @RequestMapping(value = "/submitEditEvent", method = RequestMethod.POST)
    public String editEventWriteToDatabase(Model model, @ModelAttribute("event") Event event, HttpServletRequest req) {
        Event oldEvent;
        Set<Asset> eventAssets;
        boolean open = Boolean.parseBoolean(req.getParameter("open"));
        int userId = Integer.parseInt(req.getParameter("user.userId"));
        User eventUser = userDao.getUserByUserId(userId);
        event.setUser(eventUser);
        event.setOpen(open);
        Map<Asset, List<AssetNote>> assetNotes = new HashMap<>();

        //retrieve assets from existing event and attach to updated event before writing to DB
        oldEvent = eventDao.getEventByEventId(event.getEventId());
        eventAssets = oldEvent.getAssets();
        event.setAssets(eventAssets);

        //update event in database
        eventDao.editEvent(event);
//        return "redirect:viewEventInfo?eventId=" + event.getEventId();
        
        
        return "redirect:editEvent?eventId=" + event.getEventId();
    }

    @RequestMapping(value = "/checkInAsset", method = RequestMethod.GET)
    public String checkInAsset(Model model, HttpServletRequest req) {
        int eventId;
        int assetId;
        String damageStatus = req.getParameter("damageStatus");

        try {
            eventId = Integer.parseInt(req.getParameter("eventId"));
        } catch (NumberFormatException e) {
            eventId = 0;
        }

        if (eventDao.getEventByEventId(eventId) == null) {
            model.addAttribute("badEventError", BAD_EVENT_ERROR_MESSAGE);
            return "redirect:home";
        }

        try {
            assetId = Integer.parseInt(req.getParameter("assetId"));
        } catch (NumberFormatException e) {
            assetId = 0;
        }

        if (assetDao.getAssetById(assetId) == null) {
            model.addAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "redirect:editEvent?eventId=" + eventId;
        }

        checkIn.checkInAsset(eventId, assetId, damageStatus);
        return "redirect:editEvent?eventId=" + eventId;
    }

    //helper methods
    private Model supplyModelAttributes(Model model, Event event) {
        model.addAttribute("event", event);

        Set<Asset> assetsCheckedOutForEvent = eventDao.getAllAssetsForEvent(event);
        model.addAttribute("assetCheckedOutList", assetsCheckedOutForEvent);

        Set<Category> categoryList = assetDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        Set<AssetType> assetTypeList = assetDao.getAllAssetTypes();
        model.addAttribute("assetTypeList", assetTypeList);

        return model;
    }

    private Event retrieveEventById(HttpServletRequest request) {
        int eventId;
        try {
            eventId = Integer.parseInt(request.getParameter("eventId"));
        } catch (NumberFormatException e) {
            eventId = 0;
        }

        return eventDao.getEventByEventId(eventId);
    }
}
