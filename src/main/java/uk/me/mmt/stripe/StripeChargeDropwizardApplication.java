package uk.me.mmt.stripe;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import uk.me.mmt.stripe.api.ChargeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class StripeChargeDropwizardApplication extends Application<StripeChargeDropwizardConfiguration> {

    public static void main(String[] args) throws Exception {
        new StripeChargeDropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "stripe-charge-dropwizard";
    }

    @Override
    public void initialize(Bootstrap<StripeChargeDropwizardConfiguration> bootstrap) {
    }

    @Override
    public void run(StripeChargeDropwizardConfiguration configuration, Environment environment) {

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", configuration.getCoreAllowedOrigin());
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        final ChargeResource resource = new ChargeResource(configuration.getStripeSecretKey());

        final StripeAPIHealthCheck healthCheck = new StripeAPIHealthCheck();
        environment.healthChecks().register("stripeApi", healthCheck);

        environment.jersey().register(resource);
    }

}
