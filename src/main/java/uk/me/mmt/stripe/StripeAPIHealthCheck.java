package uk.me.mmt.stripe;

import com.codahale.metrics.health.HealthCheck;

import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.Response.Status.*;

public class StripeAPIHealthCheck extends HealthCheck {

    public StripeAPIHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        URL url = new URL("https://api.stripe.com/healthcheck");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();

        if (OK.getStatusCode() != code) {
            return Result.unhealthy("Stripe API is down");
        }
        return Result.healthy();
    }
}
