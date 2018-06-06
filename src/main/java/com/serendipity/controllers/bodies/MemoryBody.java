package com.serendipity.controllers.bodies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemoryBody {

    @NotNull
    @NotBlank
    long hotspotId;

    @NotNull
    @NotBlank
    String message;

    String base64Image;

    public long getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(long hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
