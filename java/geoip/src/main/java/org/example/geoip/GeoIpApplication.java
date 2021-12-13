package org.example.geoip;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.example.geoip.resources.GeoIpResource;

public class GeoIpApplication extends Application<GeoIpConfiguration> {
    public static void main(String[] args) throws Exception {
        new GeoIpApplication().run(args);
    }

    @Override
    public void run(GeoIpConfiguration c, Environment e) {
        final GeoIpResource r = new GeoIpResource();
        e.jersey().register(r);
    }
}
