package com.millet.z_basic.ui.eventbus;

public class EPreviewDeleteImage {

    private int position;

    public EPreviewDeleteImage(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
