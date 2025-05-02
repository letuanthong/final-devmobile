package com.example.dib;

public class TransactionItem {
    private int imageResId;
    private String title;
    private String tag;

    public TransactionItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
        this.tag = tag;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }


}
