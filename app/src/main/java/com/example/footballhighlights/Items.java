package com.example.footballhighlights;

public class Items {
    private String thumbnailUrl;
    private String gameTitle;
    private String videoUrl;
    private String competiton;

    public Items(String thumbnailUrl, String gameTitle, String videoUrl, String competiton) {
        this.thumbnailUrl = thumbnailUrl;
        this.gameTitle = gameTitle;
        this.videoUrl = videoUrl;
        this.competiton = competiton;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getCompetiton() {
        return competiton;
    }
}
