package controller;

import static model.Constants.DEVICE_TOKEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.WallapopAPI;
import webservice_data.Item;
import webservice_data.Token;
import webservice_data.User;

@CrossOrigin(methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
public class WallapopController {
    @Autowired
    private WallapopAPI wallapopAPI;

    @PostMapping(value = "/login")
    public Token login(@RequestBody User user){
        return wallapopAPI.login(user);
    }

    @GetMapping("/userItems/{userToken}")
    public Item[] getUserItems(@PathVariable("userToken") String userToken){
        return wallapopAPI.getAllUserItems(userToken);
    }

    @PostMapping(value = "/item/{itemId}/{title}/{description}/{salePrice}")
    public String editItem(@RequestBody String userToken, @PathVariable("itemId") String itemId, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("salePrice") String salePrice){
        return wallapopAPI.editItem(userToken, itemId, title, description, salePrice);
    }

    @PutMapping(value = "/item/{userToken}/{title}/{description}/{salePrice}")
    public String addNewItem(@RequestBody String imageBase64, @PathVariable("userToken") String userToken, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("salePrice") String salePrice){
        return wallapopAPI.addNewItem(userToken, DEVICE_TOKEN, imageBase64, title, description, salePrice);
    }

    @PutMapping(value = "/item/{itemId}")
    public void sellItem(@RequestBody String userToken, @PathVariable("itemId") String itemId){
        wallapopAPI.sellItem(userToken, DEVICE_TOKEN, itemId);
    }

    @DeleteMapping(value = "/item/{itemId}")
    public void deleteItem(@RequestBody String userToken, @PathVariable("itemId") String itemId){
        wallapopAPI.deleteItem(userToken, DEVICE_TOKEN, itemId);
    }
}