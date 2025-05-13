package com.example.dib;

public class TransactionItem {
    private int imageResId;
    private String title;

    public TransactionItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
