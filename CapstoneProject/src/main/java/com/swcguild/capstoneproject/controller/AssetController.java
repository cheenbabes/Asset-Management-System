package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.interfaces.AssetInterface;
import com.swcguild.capstoneproject.dao.interfaces.EventInterface;
import com.swcguild.capstoneproject.model.Asset;
import com.swcguild.capstoneproject.model.AssetType;
import com.swcguild.capstoneproject.model.Category;
import com.swcguild.capstoneproject.model.Event;
import com.swcguild.capstoneproject.model.notes.AssetNote;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AssetController {

    private static final String BAD_CATEGORY_ERROR_MESSAGE = "Oops! Invalid category ID. Must supply category ID as an integer. Category ID must refer to an existing category.";
    private static final String BAD_ASSET_TYPE_ERROR_MESSAGE = "Oops! Invalid asset type ID. Must supply asset type ID as an integer. Asset type ID must refer to an existing asset type.";
    private static final String BAD_ASSET_ERROR_MESSAGE = "Oops! Invalid asset ID. Must supply asset ID as an integer. Asset ID must refer to an existing asset.";

    private AssetInterface assetDao;
    
    @Inject
    public AssetController(AssetInterface assetDao) {
        this.assetDao = assetDao;
    }

    //List Pages
    @RequestMapping(value = {"/assets"}, method = RequestMethod.GET)
    public String displayBrowseAssets(Model model, HttpServletRequest request) {
        Set<AssetType> types = getSelectedAssetTypes(request.getParameter("selectCategory"));
        model.addAttribute("categoryList", assetDao.getAllCategories());
        model.addAttribute("assetTypeList", types);

        return "browseAssets";
    }
    
    @RequestMapping(value = {"/fileUploadForm"}, method = RequestMethod.GET)
    public String fileUpload(Model model, HttpServletRequest request) {
        Set<AssetType> types = getSelectedAssetTypes(request.getParameter("selectCategory"));
        model.addAttribute("categoryList", assetDao.getAllCategories());
        model.addAttribute("assetTypeList", types);

        int typeId;
        
        try{
            typeId =Integer.parseInt(request.getParameter("typeId"));
        }
        catch(NumberFormatException e){
            typeId = 0;
        }
        
        if(assetDao.getAssetTypeById(typeId) == null){
            model.addAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }
        
        model.addAttribute("typeId", typeId);
        
        return "fileUploadForm";
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest req, 
            Model model, 
            @RequestParam("name") String name, //also want asset type id (or put on model) do ??
            @RequestParam("uploadFile") MultipartFile file) {
        int typeId;
        
        try{
            typeId =Integer.parseInt(req.getParameter("typeId"));
        }
        catch(NumberFormatException e){
            typeId = 0;
        }
        
        if(assetDao.getAssetTypeById(typeId) == null){
            model.addAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }
        
        model.addAttribute("typeId", typeId);
        
        // only save the file if the user actually uploaded something
        if (!file.isEmpty()) {
            try {
                // add the filename to the model so we can display it on the success page
                model.addAttribute("fileName", name);
                // we want to put the uploaded image into the 'images' folder of our application
                String savePath = req.getSession().getServletContext().getRealPath("/") + "img/";
                File dir = new File(savePath);
                // if 'images' directory is not there, go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // transfer the contents of the uploaded file to the server
                file.transferTo(new File(savePath + name));
                
                // the success page uses this value in the src attribute of the 
                // img tag so it can display the newly uploaded file
                model.addAttribute("imageRef", "img/" + name);
                return "redirect:updateAssetType";
            } catch (Exception e) {
                // if we encounter an exception, add the error message to the model
                // and return back to the file upload form page
                model.addAttribute("errorMsg", "File upload failed: " + e.getMessage());
                return "fileUploadForm";
            }
        } else {
            // if the user didn't upload anything, add the error message to the model
            // and return back to the file upload form page
            model.addAttribute("errorMsg", "Please specify a non-empty file.");
            return "fileUploadForm";
        }
    }

    @RequestMapping(value = {"/manage_assets"}, method = RequestMethod.GET)
    public String displayManageAssets(Model model, HttpServletRequest request) {
        Set<AssetType> types = getSelectedAssetTypes(request.getParameter("selectCategory"));

        //error messages
        //String badCategoryError = request.getParameter("badCategoryError");
        model.addAttribute("badCategoryError", request.getParameter("badCategoryError"));

        //String badAssetTypeError = request.getParameter("badAssetTypeError");
        model.addAttribute("badAssetTypeError", request.getParameter("badAssetTypeError"));

        //String badAssetError = request.getParameter("badAssetError");
        model.addAttribute("badAssetError", request.getParameter("badAssetError"));

        //populating display
        model.addAttribute("categoryList", assetDao.getAllCategories());
        model.addAttribute("assetTypeList", types);
        model.addAttribute("category", new Category());
        return "manageAssets";
    }

    @RequestMapping(value = {"/listAssets"}, method = RequestMethod.GET)
    public String displayAssetListingForType( Model model, HttpServletRequest request) {
        int typeId;
        AssetType assetType;
        
        try{
            typeId = Integer.parseInt(request.getParameter("typeId"));
        }
        catch(NumberFormatException e){
            typeId = 0;
        }
        
        assetType = assetDao.getAssetTypeById(typeId);

        if(assetType == null){
            model.addAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }
        
        model.addAttribute("assetList", assetDao.getAllAssetsByAssetType(assetType));

        return "assetsByType";
    }

    @RequestMapping(value = {"/listAssetNotes"}, method = RequestMethod.GET)
    public String listAssetNotes(Model model, HttpServletRequest request) {
        int assetId;

        try {
            assetId = Integer.parseInt(request.getParameter("assetId"));
        } catch (Exception e) {
            assetId = 0;

        }
        
        Asset asset = assetDao.getAssetById(assetId);

        if (asset == null) {
            request.setAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        model.addAttribute("asset", asset);
        model.addAttribute("assetNotes", assetDao.getAssetNotes(assetId));

        return "viewAssetNotes";
    }

    //Asset Forms
    @RequestMapping(value = {"/addAsset"}, method = RequestMethod.GET)
    public String displayAddAsset(Model model, HttpServletRequest request) {
        //error message
        model.addAttribute("badAssetTypeError", request.getParameter("badAssetTypeError"));
        
        //populate drop-down
        model.addAttribute("assetTypes", assetDao.getAllAssetTypes());

        //supply empty asset
        model.addAttribute("newAsset", new Asset());

        return "addAsset";
    }

    @RequestMapping(value = {"/updateAsset"}, method = RequestMethod.GET)
    public String displayEditAsset(Model model, HttpServletRequest request) {
        int assetId;

        try {
            assetId = Integer.parseInt(request.getParameter("assetId"));

        } catch (Exception e) {
            request.setAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        model.addAttribute("assetTypes", assetDao.getAllAssetTypes());

        model.addAttribute("asset", assetDao.getAssetById(assetId));

        return "editAsset";
    }

    //Asset CRUD
    @RequestMapping(value = {"/submitNewAsset"}, method = RequestMethod.POST)
    public String submitNewAsset(@Valid Asset newAsset, BindingResult result, Model model, HttpServletRequest request) {
        int typeId;
        boolean nullType;
        try {
            typeId = Integer.parseInt(request.getParameter("typeId"));
        } catch (NumberFormatException e) {
            typeId = 0;
        }

        nullType = assetDao.getAssetTypeById(typeId) == null;
        if (result.hasErrors() || nullType) {
            if (nullType) {
                model.addAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            }
            return "redirect:addAsset";
        }

        newAsset.setAssetType(assetDao.getAssetTypeById(typeId));

        assetDao.addAsset(newAsset);

        model.addAttribute("typeId", typeId);
        return "redirect:listAssets";
    }

    @RequestMapping(value = {"/submitAssetUpdate"}, method = RequestMethod.POST)
    public String submitAssetUpdate(@Valid Asset asset, BindingResult result, Model model, HttpServletRequest request) {
        //get assetTypeId associated with asset
        //asset type not passed as part of asset on model
        int typeId = assetDao.getAssetById(asset.getAssetId()).getAssetType().getAssetTypeId();
        model.addAttribute("typeId", typeId);

        if (result.hasErrors()) {
            return "redirect:updateAsset";
        }

        assetDao.editAsset(asset);

        return "redirect:listAssets";
    }

    @RequestMapping(value = {"/removeAsset"}, method = RequestMethod.GET)
    public String deleteAsset(Model model, HttpServletRequest request) {
        int assetId;
        Asset asset;

        try {
            assetId = Integer.parseInt(request.getParameter("assetId"));
        } catch (NumberFormatException e) {
            assetId = 0;
        }

        asset = assetDao.getAssetById(assetId);

        if (asset == null) {
            request.setAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        assetDao.deleteAsset(asset);
        return "redirect:manage_assets";
    }

    //AssetType Forms
    @RequestMapping(value = {"/addAssetType"}, method = RequestMethod.GET)
    public String displayAddAssetType(Model model, HttpServletRequest request) {
        //error message
        //String badCategoryError = request.getParameter("badCategoryError");
        model.addAttribute("badCategoryError", request.getParameter("badCategoryError"));

        //populating drop-down menu
        model.addAttribute("categoryList", assetDao.getAllCategories());

        //supplying empty asset type
        model.addAttribute("newAssetType", new AssetType());

        return "addAssetType";
    }

    @RequestMapping(value = {"/updateAssetType"}, method = RequestMethod.GET)
    public String displayEditAssetType(Model model, HttpServletRequest request) {
        String imageRef = request.getParameter("imageRef");
        model.addAttribute("imageRef", imageRef);
        int assetTypeId;
        //error message
        //String badCategoryError = request.getParameter("badCategoryError");
        model.addAttribute("badCategoryError", request.getParameter("badCategoryError"));

        //get asset type id
        try {
            assetTypeId = Integer.parseInt(request.getParameter("typeId"));
        } catch (NumberFormatException e) {
            request.setAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        //populate drop-down
        model.addAttribute("categoryList", assetDao.getAllCategories());

        //retrieve asset type to be updated
        model.addAttribute("assetType", assetDao.getAssetTypeById(assetTypeId));

        return "editAssetType";
    }

    //AssetType CRUD
    @RequestMapping(value = {"/submitNewAssetType"}, method = RequestMethod.POST)
    public String submitNewAssetType(@Valid AssetType newAssetType, BindingResult result, Model model, HttpServletRequest request
    ) {
        int categoryId;
        boolean nullCategory;
        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            categoryId = 0;
        }

        nullCategory = assetDao.getCategoryById(categoryId) == null;
        if (result.hasErrors() || nullCategory) {
            if (nullCategory) {
                model.addAttribute("badCategoryError", BAD_CATEGORY_ERROR_MESSAGE);
            }

            return "redirect:addAssetType";
        }

        newAssetType.setCategory(assetDao.getCategoryById(categoryId));
        assetDao.addAssetType(newAssetType);
        return "redirect:manage_assets";
    }

    @RequestMapping(value = {"/submitAssetTypeUpdate"}, method = RequestMethod.POST)
    public String submitAssetTypeUpdate(@Valid AssetType assetType, BindingResult result, Model model, HttpServletRequest request
    ) {
        int categoryId;
        boolean nullCategory;
        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            categoryId = 0;
        }

        nullCategory = assetDao.getCategoryById(categoryId) == null;
        if (result.hasErrors() || nullCategory) {
            if (nullCategory) {
                model.addAttribute("badCategoryError", BAD_CATEGORY_ERROR_MESSAGE);
            }

            model.addAttribute("typeId", assetType.getAssetTypeId());
            return "redirect:updateAssetType";
        }

        assetType.setCategory(assetDao.getCategoryById(categoryId));
        assetDao.editAssetType(assetType);
        return "redirect:manage_assets";
    }

    @RequestMapping(value = {"/removeAssetType"}, method = RequestMethod.GET)
    public String deleteAssetType(Model model, HttpServletRequest request
    ) {
        AssetType assetType;
        int assetTypeId;
        try {
            assetTypeId = Integer.parseInt(request.getParameter("typeId"));
        } catch (NumberFormatException e) {
            assetTypeId = 0;
        }

        assetType = assetDao.getAssetTypeById(assetTypeId);

        if (assetType == null) {
            request.setAttribute("badAssetTypeError", BAD_ASSET_TYPE_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        assetDao.deleteAssetType(assetType);

        return "redirect:manage_assets";
    }

    //Category Form
    @RequestMapping(value = {"/updateCategory"}, method = RequestMethod.GET)
    public String displayEditCategory(Model model, HttpServletRequest request) {
        int categoryId;

        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            model.addAttribute("badCategoryError", BAD_CATEGORY_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        model.addAttribute("category", assetDao.getCategoryById(categoryId));

        return "editCategory";
    }

    //Category CRUD
    @RequestMapping(value = {"/submitNewCategory"}, method = RequestMethod.POST)
    public String submitNewAssetType(@Valid Category category, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "redirect:manage_assets";
        }

        assetDao.addCategory(category);

        return "redirect:manage_assets";
    }

    @RequestMapping(value = {"/submitCategoryUpdate"}, method = RequestMethod.POST)
    public String submitCategoryUpdate(@Valid Category category, BindingResult result, Model model, HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "redirect:manage_assets";
        }

        assetDao.editCategory(category);
        return "redirect:manage_assets";
    }

    @RequestMapping(value = {"/removeCategory"}, method = RequestMethod.GET)
    public String deleteCategory(Model model, HttpServletRequest request
    ) {
        Category category;
        int categoryId;
        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            categoryId = 0;
        }

        category = assetDao.getCategoryById(categoryId);

        if (category == null) {
            model.addAttribute("badCategoryError", BAD_CATEGORY_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        assetDao.deleteCategory(category);
        return "redirect:manage_assets";
    }

    //AssetNote Form
    @RequestMapping(value = {"/assetAddNote"}, method = RequestMethod.GET)
    public String displayAddAssetNote(Model model, HttpServletRequest request) {
        int assetId;
        AssetNote newNote = new AssetNote();
        Asset asset;
        try {
            assetId = Integer.parseInt(request.getParameter("assetId"));
        } catch (Exception e) {
            assetId = 0;
        }

        asset = assetDao.getAssetById(assetId);

        if (asset == null) {
            request.setAttribute("badAssetError", BAD_ASSET_ERROR_MESSAGE);
            return "redirect:manage_assets";
        }

        newNote.setAssetId(assetId);
        model.addAttribute("assetNote", newNote);
        model.addAttribute("asset", asset);
        model.addAttribute("assetNoteList", assetDao.getAssetNotes(assetId));

        return "assetAddNote";
    }

    //AssetNoteCRUD
    @RequestMapping(value = {"/submitNewAssetNote"}, method = RequestMethod.POST)
    public String submitNewAssetNote(@Valid AssetNote newAssetNote, BindingResult result, Model model, HttpServletRequest request) {
        int typeId;
        int assetId;
        if (result.hasErrors()) {
            return "redirect:assetAddNote";
        }

        assetId = newAssetNote.getAssetId();
        typeId = assetDao.getAssetById(assetId).getAssetType().getAssetTypeId();

        assetDao.addNoteToAsset(newAssetNote.getAssetId(), newAssetNote.getNote(), newAssetNote.getCategory());
        model.addAttribute("typeId", typeId);
        return "redirect:listAssets";
    }

    //Helper Methods
    private Set<AssetType> getSelectedAssetTypes(String categoryName) {
        Set<Category> categories = assetDao.getAllCategories();
        Set<AssetType> types = new HashSet<>();
        Category selectedCat = null;
        //String catName = request.getParameter("searchByCategory");

        //find desired category, if selected, and get asset types for category
        if (categoryName != null) {
            for (Category cat : categories) {
                if (cat.getCategoryName().equalsIgnoreCase(categoryName)) {
                    selectedCat = cat;
                    types.addAll(assetDao.getAssetTypeByCategory(cat));
                }
            }
        }

        //get all asset types if no category selected
        if (selectedCat == null /* || catName == null || catName.equalsIgnoreCase("All")*/) {
            for (Category cat : categories) {
                types.addAll(assetDao.getAssetTypeByCategory(cat));
            }
        }

        return types;
    }
}
