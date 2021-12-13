package org.example.geoip;

import io.dropwizard.Configuration;

import javax.validation.constraints.Positive;
import java.time.Duration;

public class GeoIpConfiguration extends Configuration {
    @Positive
    private int cacheExpiration;

    public Duration getCacheExpiration() {
        return Duration.ofSeconds(cacheExpiration);
    }
}
