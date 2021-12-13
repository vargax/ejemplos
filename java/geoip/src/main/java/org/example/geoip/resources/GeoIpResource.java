package org.example.geoip.resources;

import com.codahale.metrics.annotation.Timed;
import org.example.geoip.api.GeoIp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;

@Path("/ip/{ipAddress}")
@Produces(MediaType.APPLICATION_JSON)
public class GeoIpResource {

    public GeoIpResource() {

    }

    @GET
    @Timed
    public GeoIp query(@PathParam("ipAddress") String ipAddress) {
        return new GeoIp(ipAddress);
    }
}
