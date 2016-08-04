package webservice_data;

public class Review {
    private String itemId;
    private boolean isPaymentPlatformReview;
    private int score, notificationId, toUserId;

    public Review() {}
    
    public Review(String itemId, int score, int notificationId, int toUserId, boolean isPaymentPlatformReview) {
        this.score = score;
        this.itemId = itemId;
        this.notificationId = notificationId;
        this.toUserId = toUserId;
        this.isPaymentPlatformReview = isPaymentPlatformReview;
    }
    
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean isPaymentPlatformReview() {
        return isPaymentPlatformReview;
    }

    public void setPaymentPlatformReview(boolean isPaymentPlatformReview) {
        this.isPaymentPlatformReview = isPaymentPlatformReview;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }
}