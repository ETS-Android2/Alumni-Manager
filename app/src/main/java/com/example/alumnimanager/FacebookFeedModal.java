package com.example.alumnimanager;

public class FacebookFeedModal {
    // variables for storing our author image,
    // author name, postDate,postDescription,
    // post image,post likes,post comments.
    private String pId;
    private String uName;
    private String authorName;
    private String postDate;
    private String postDescription;
    private String postIV;
    private String postLikes;
    private String postComments;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    // creating getter and setter methods.
    public String getuName() {
        return uName;
    }

    public void setuName(String authorImage) {
        this.uName = authorImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostIV() {
        return postIV;
    }

    public void setPostIV(String postIV) {
        this.postIV = postIV;
    }

    public String getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(String postLikes) {
        this.postLikes = postLikes;
    }

    public String getPostComments() {
        return postComments;
    }

    public void setPostComments(String postComments) {
        this.postComments = postComments;
    }

    // creating a constructor class.
    public FacebookFeedModal(String pid, String authorImage, String authorName, String postDate,
                             String postDescription, String postIV, String postLikes,
                             String postComments) {
        this.pId = pid;
        this.uName = authorImage;
        this.authorName = authorName;
        this.postDate = postDate;
        this.postDescription = postDescription;
        this.postIV = postIV;
        this.postLikes = postLikes;
        this.postComments = postComments;
    }

    public FacebookFeedModal(String pid, String uName, String FName, String postDate,
                             String postDescription, String postLikes,
                             String postComments) {
        this.pId = pid;
        this.uName = uName;
        this.authorName = FName;
        this.postDate = postDate;
        this.postDescription = postDescription;
        this.postLikes = postLikes;
        this.postComments = postComments;
    }
}