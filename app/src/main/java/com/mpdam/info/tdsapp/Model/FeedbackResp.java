package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/3/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Feedbacks")
    @Expose
    private List<Feedback> feedbacks = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}