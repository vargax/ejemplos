package org.example.geoip.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoIp {

    private String ip;

    public GeoIp() {

    }

    public GeoIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty
    public String getIp() {
        return ip;
    }

}
