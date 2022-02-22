package com.example.testing2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.altbeacon.beacon.Identifier;

import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

public class Config {

    //public static Beacon mBeacon;

    @SerializedName("scan_parameters")
    @Expose
    private ScanParameters scanParameters;
    @SerializedName("beacons")
    @Expose
    private List<Beacon> beacons = null;

    public ScanParameters getScanParameters() {
        return scanParameters;
    }

    public void setScanParameters(ScanParameters scanParameters) {
        this.scanParameters = scanParameters;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public class ScanParameters {

        @SerializedName("scan_duration")
        @Expose
        private Integer scanDuration;
        @SerializedName("interval_between_scans")
        @Expose
        private Integer intervalBetweenScans;
        @SerializedName("perform_beacon_ranging")
        @Expose
        private Boolean performBeaconRanging;
        @SerializedName("meters_to_trigger_promotion")
        @Expose
        private Integer metersToTriggerPromotion;
        @SerializedName("limit_promotions_to_once_per_trip")
        @Expose
        private Boolean limitPromotions;
        @SerializedName("beacon_layouts")
        @Expose
        private List<String> beaconLayouts;

        public Integer getScanDuration() {
            return scanDuration;
        }

        public void setScanDuration(Integer scanDuration) {
            this.scanDuration = scanDuration;
        }

        public Integer getIntervalBetweenScans() {
            return intervalBetweenScans;
        }

        public void setIntervalBetweenScans(Integer intervalBetweenScans) {
            this.intervalBetweenScans = intervalBetweenScans;
        }
        public Boolean getPerformBeaconRanging() {
            return performBeaconRanging;
        }

        public void setPerformBeaconRanging(Boolean performBeaconRanging) {
            this.performBeaconRanging = performBeaconRanging;
        }

        public Integer getMetersToTriggerPromotion() {
            return metersToTriggerPromotion;
        }

        public void setMetersToTriggerPromotion(Integer metersToTriggerPromotion) {
            this.metersToTriggerPromotion = metersToTriggerPromotion;
        }

        public Boolean getLimitPromotions() {
            return limitPromotions;
        }

        public void setLimitPromotions(Boolean limitPromotions) {
            this.limitPromotions = limitPromotions;
        }

        public List<String> getBeaconLayouts() {
            return beaconLayouts;
        }

        public void setBeaconLayouts(List<String> beaconLayouts) {
            this.beaconLayouts = beaconLayouts;
        }



    }

    public class Beacon {



        @SerializedName("beacon_mac")
        @Expose
        private String beaconMac;
        @SerializedName("beacon_name")
        @Expose
        private String beaconName;

        public class Uuid{
            @SerializedName("id_1")
            @Expose
            private String id1;
            @SerializedName("id_2")
            @Expose
            private String id2;
            @SerializedName("id_3")
            @Expose
            private String id3;

            public String getId1(){return id1;}
            public void setId1(String id1){this.id1 = id1;}

            public String getId2(){return id2;}
            public void setId2(String id2){this.id2 = id2;}

            public String getId3(){return id3;}
            public void setId3(String id3){this.id3 = id3;}

        }
        @SerializedName("promotion_title")
        @Expose
        private String promotionTitle;
        @SerializedName("promotion_image")
        @Expose
        private String promotionImage;
        @SerializedName("promotion_message")
        @Expose
        private String promotionMessage;
        @SerializedName("promotion_splash")
        @Expose
        private String promotionSplash;
        @SerializedName("TTS_enabled")
        @Expose
        private Boolean tTSEnabled;
        @SerializedName("TTS")
        @Expose
        private String tTS;

        public String getBeaconMac() {
            return beaconMac;
        }

        public void setBeaconMac(String beaconMac) {
            this.beaconMac = beaconMac;
        }


        @SerializedName("uuids")
        @Expose
        private List<Uuid> uuids = null;

        public List<Uuid> getUuids() {
            return uuids;
        }

        public void setUuids(List<Uuid> uuids) {
            this.uuids = uuids;
        }


        public String getPromotionTitle() {
            return promotionTitle;
        }

        public void setPromotionTitle(String promotionTitle) {
            this.promotionTitle = promotionTitle;
        }

        public String getPromotionImage() {
            return promotionImage;
        }

        public void setPromotionImage(String promotionImage) {
            this.promotionImage = promotionImage;
        }

        public String getPromotionMessage() {
            return promotionMessage;
        }

        public void setPromotionMessage(String promotionMessage) {
            this.promotionMessage = promotionMessage;
        }

        public String getPromotionSplash() {
            return promotionSplash;
        }

        public void setPromotionSplash(String promotionSplash) {
            this.promotionSplash = promotionSplash;
        }

        public Boolean getTTSEnabled() {
            return tTSEnabled;
        }

        public void setTTSEnabled(Boolean tTSEnabled) {
            this.tTSEnabled = tTSEnabled;
        }

        public String getTTS() {
            return tTS;
        }

        public void setTTS(String tTS) {
            this.tTS = tTS;
        }

    }

}
