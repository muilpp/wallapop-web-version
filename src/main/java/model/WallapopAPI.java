package model;

import webservice_data.Item;
import webservice_data.Token;
import webservice_data.User;

public interface WallapopAPI {
    /**
     * Method used to perform the login
     * @param user
     * @return The token if the user exists
     */
    public Token login(User user);
    
    public void getAllUserConversations();

    public void getUserItem(String itemId);

    /**
     * Method used to fetch all the items a user has posted
     * @param bearer
     * @return An array of items if found
     */
    public Item[] getAllUserItems(String bearer);

    /**
     * Method used to edit a user item
     * @param bearer
     * @param itemId
     * @param title
     * @param description
     * @param salePrice
     * @return The id of the item if it was correctly edited.
     */
    public String editItem(String bearer, String itemId, String title, String description, String salePrice);

    /**
     * Method used to add a new item to the user's collection
     * @param bearer
     * @param deviceToken
     * @param imageBase64
     * @param title
     * @param description
     * @param salePrice
     * @return A string containing the itemId if it was correctly added
     */
    public String addNewItem(String bearer, String deviceToken, String imageBase64, String title, String description, String salePrice);

    /**
     * Method used to delete an item from the user's collection
     * @param bearer
     * @param deviceToken
     * @param itemId
     */
    public void deleteItem(String bearer, String deviceToken, String itemId);

    /**
     * Method used to mark an item as sold from the user's collection.
     * @param bearer
     * @param deviceToken
     * @param itemId
     */
    public void sellItem(String bearer, String deviceToken, String itemId);
}