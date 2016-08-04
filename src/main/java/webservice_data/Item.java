package webservice_data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String itemId, title, description, salePrice, saleCurrency, originalSalePrice, publishDate;
    private List<Images> imagesList;
    private ItemCounter itemCounter;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOriginalSalePrice() {
        return originalSalePrice;
    }

    public void setOriginalSalePrice(String originalSalePrice) {
        this.originalSalePrice = originalSalePrice;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleCurrency() {
        return saleCurrency;
    }

    public void setSaleCurrency(String saleCurrency) {
        this.saleCurrency = saleCurrency;
    }

    @JsonProperty("images")
    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }
    
    public static class Images {
        private String pictureId, smallURL;

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public String getSmallURL() {
            return smallURL;
        }

        public void setSmallURL(String smallURL) {
            this.smallURL = smallURL;
        }
    }
    
    @JsonProperty("itemCounters")
    public ItemCounter getItemCounter() {
        return itemCounter;
    }
    
    public void setItemCounter(ItemCounter itemCounter) {
        this.itemCounter = itemCounter;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ItemCounter {
        private String views, conversations;

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getConversations() {
            return conversations;
        }

        public void setConversations(String conversations) {
            this.conversations = conversations;
        }
    }
}