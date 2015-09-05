package uk.me.mmt.stripe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class StripeChargeDropwizardConfiguration extends Configuration {
    @NotEmpty
    final private String coreAllowedOrigin;

    @NotEmpty
    final private String stripeSecretKey;

    @JsonCreator
    public StripeChargeDropwizardConfiguration(@JsonProperty("coreAllowedOrigin") String coreAllowedOrigin, @JsonProperty("stripeSecretKey") String stripeSecretKey) {
        this.coreAllowedOrigin = coreAllowedOrigin;
        this.stripeSecretKey = stripeSecretKey;
    }

    @JsonProperty
    public String getCoreAllowedOrigin() {
        return coreAllowedOrigin;
    }

    @JsonProperty
    public String getStripeSecretKey() {
        return stripeSecretKey;
    }
}
