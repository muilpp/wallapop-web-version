package model;

import static model.Constants.CATEGORY_ID;
import static model.Constants.DEF_CATEGORY_ID;
import static model.Constants.DEF_FULL_ADDRESS;
import static model.Constants.DEF_INSTALLATION_TYPE;
import static model.Constants.DEF_KM_ERROR;
import static model.Constants.DEF_LATITUDE;
import static model.Constants.DEF_LOCATION_ID;
import static model.Constants.DEF_LONGITUDE;
import static model.Constants.DEF_NUM_PICTURES;
import static model.Constants.DEF_SALE_CURRENCY;
import static model.Constants.DESCRIPTION;
import static model.Constants.EMAIL_ADDRESS;
import static model.Constants.ERROR_ADDING_ELEMENT;
import static model.Constants.ERROR_EDITING_ELEMENT;
import static model.Constants.EXCHANGE_ALLOWED;
import static model.Constants.FALSE;
import static model.Constants.FIX_PRICE;
import static model.Constants.FULL_ADDRESS;
import static model.Constants.IMAGE_BASE_64;
import static model.Constants.INSTALLATION_TYPE;
import static model.Constants.KM_ERROR;
import static model.Constants.LATITUDE;
import static model.Constants.LOCATION_ID;
import static model.Constants.LONGITUDE;
import static model.Constants.NUM_PICTURES;
import static model.Constants.PASSWORD;
import static model.Constants.SALE_CURRENCY;
import static model.Constants.SALE_PRICE;
import static model.Constants.SHIPPING_ALLOWED;
import static model.Constants.TITLE;
import static model.Endpoints.buildDeleteItemURI;
import static model.Endpoints.buildDoReviewURI;
import static model.Endpoints.buildEditItemURI;
import static model.Endpoints.buildGenerateReviewURI;
import static model.Endpoints.buildLoginURI;
import static model.Endpoints.buildNewItemURI;
import static model.Endpoints.buildUserItemsURI;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import model.interceptors.BearerHeaderInterceptor;
import model.interceptors.DeviceTokenHeaderInterceptor;
import webservice_data.Item;
import webservice_data.Review;
import webservice_data.Token;
import webservice_data.User;

@Service
public class WallapopAPIImpl implements WallapopAPI {
    private final Logger LOGGER = Logger.getLogger(WallapopAPIImpl.class.getName());

    @Override
    public Token login(User user) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add(EMAIL_ADDRESS, user.getEmailAddress());
        map.add(PASSWORD, user.getPassword());
        map.add(INSTALLATION_TYPE, DEF_INSTALLATION_TYPE);
        ResponseEntity<Token> response = restTemplate.postForEntity(buildLoginURI(), map, Token.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            LOGGER.info(response.getStatusCode().getReasonPhrase());
        }

        return new Token();
    }

    @Override
    public Item[] getAllUserItems(String bearer) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerHeaderInterceptor(bearer));

        ResponseEntity<Item[]> response = restTemplate.getForEntity(buildUserItemsURI(), Item[].class);

        if (response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        else
            LOGGER.severe(response.getStatusCode().getReasonPhrase());

        return new Item[0];
    }

    @Override
    public String editItem(String bearer, String itemId, String title, String description, String salePrice) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerHeaderInterceptor(bearer));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add(TITLE, title);
        map.add(DESCRIPTION, description);
        map.add(SALE_PRICE, salePrice);
        map.add(SALE_CURRENCY, DEF_SALE_CURRENCY);
        ResponseEntity<Item> response = restTemplate.postForEntity(buildEditItemURI(itemId), map, Item.class);

        if (response.getStatusCode() == HttpStatus.OK)
            return response.getBody().getItemId();
        else
            LOGGER.severe(response.getStatusCode().getReasonPhrase());

        return ERROR_EDITING_ELEMENT + title;
    }

    @Override
    public String addNewItem(String bearer, String deviceToken, String imageBase64, String title, String description,
            String salePrice) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerHeaderInterceptor(bearer));
        restTemplate.getInterceptors().add(new DeviceTokenHeaderInterceptor(deviceToken));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add(TITLE, title);
        map.add(DESCRIPTION, description);
        map.add(SALE_PRICE, salePrice);
        map.add(SALE_CURRENCY, DEF_SALE_CURRENCY);
        map.add(CATEGORY_ID, DEF_CATEGORY_ID);
        map.add(LATITUDE, DEF_LATITUDE);
        map.add(LONGITUDE, DEF_LONGITUDE);
        map.add(LOCATION_ID, DEF_LOCATION_ID);
        map.add(NUM_PICTURES, DEF_NUM_PICTURES);
        map.add(IMAGE_BASE_64, imageBase64);
        map.add(KM_ERROR, DEF_KM_ERROR);
        map.add(FULL_ADDRESS, DEF_FULL_ADDRESS);
        map.add(FIX_PRICE, FALSE);
        map.add(EXCHANGE_ALLOWED, FALSE);
        map.add(SHIPPING_ALLOWED, FALSE);

        ResponseEntity<Item> response = restTemplate.postForEntity(buildNewItemURI(), map, Item.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getItemId();
        } else {
            LOGGER.severe(response.getStatusCode().getReasonPhrase());
        }

        return ERROR_ADDING_ELEMENT + title;
    }

    @Override
    public void deleteItem(String bearer, String deviceToken, String itemId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerHeaderInterceptor(bearer));
        restTemplate.getInterceptors().add(new DeviceTokenHeaderInterceptor(deviceToken));

        restTemplate.delete(buildDeleteItemURI(itemId));
    }

    @Override
    public void sellItem(String bearer, String deviceToken, String itemId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerHeaderInterceptor(bearer));
        restTemplate.getInterceptors().add(new DeviceTokenHeaderInterceptor(deviceToken));

        ResponseEntity<String> response = restTemplate.getForEntity(buildGenerateReviewURI(itemId), String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Review review = new Review(itemId, 5, 0, 0, false);
            restTemplate.put(buildDoReviewURI(), review);
        } else {
            LOGGER.severe(response.getStatusCode().getReasonPhrase());
        }
    }

    @Override
    public void getAllUserConversations() {
        // TODO Auto-generated method stub

    }

    @Override
    public void getUserItem(String itemId) {
        // TODO Auto-generated method stub

    }
}