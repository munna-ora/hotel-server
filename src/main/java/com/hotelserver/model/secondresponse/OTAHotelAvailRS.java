
package com.hotelserver.model.secondresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTAHotelAvailRS {

    @SerializedName("xmlns")
    @Expose
    public String xmlns;
    @SerializedName("RoomStays")
    @Expose
    public RoomStays roomStays;
    @SerializedName("CorrelationID")
    @Expose
    public String correlationID;
    @SerializedName("PrimaryLangID")
    @Expose
    public String primaryLangID;
    @SerializedName("AltLangID")
    @Expose
    public String altLangID;
    @SerializedName("Success")
    @Expose
    public String success;

}
