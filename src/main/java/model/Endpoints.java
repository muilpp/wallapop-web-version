package model;

public class Endpoints {

    private final static String BASE_URL = "https://pro2.wallapop.com/shnm-portlet/api/v1";
    private final static String LOGIN_EP = "/access.json/login3";
    private final static String USER_ITEMS_EP = "/item.json/mines2?init=0&end=50&statuses=PUBLISHED";
    private final static String EDIT_ITEM_EP = "/item.json/";
    private final static String ADD_ITEM_EP = "/item.json/new3";
    private final static String DELETE_ITEM_EP = "/item.json/";
    private final static String GENERATE_REVIEW = "/item.json/{itemId}/soldGenerateReview";
    private final static String REVIEW = "/review.json/skip";

    public static String buildLoginURI() {
        return BASE_URL + LOGIN_EP;
    }

    public static String buildUserItemsURI() {
        return BASE_URL + USER_ITEMS_EP;
    }

    public static String buildEditItemURI(String itemId) {
        return BASE_URL + EDIT_ITEM_EP + itemId;
    }

    public static String buildNewItemURI() {
        return BASE_URL + ADD_ITEM_EP;
    }

    public static String buildDeleteItemURI(String itemId) {
        return BASE_URL + DELETE_ITEM_EP + itemId;
    }

    public static String buildGenerateReviewURI(String itemId) {
        return BASE_URL + GENERATE_REVIEW.replace("{itemId}", itemId);
    }

    public static String buildDoReviewURI() {
        return BASE_URL + REVIEW;
    }
}