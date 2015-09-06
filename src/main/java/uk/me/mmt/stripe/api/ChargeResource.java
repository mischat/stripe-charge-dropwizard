package uk.me.mmt.stripe.api;

import com.codahale.metrics.annotation.Timed;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/charge")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class ChargeResource {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String stripeSecretKey;

    public ChargeResource(String stripeSecretKey) {
        this.stripeSecretKey = stripeSecretKey;
    }

    @POST
    @Timed
    public Response makeStripeCharge(@FormParam("token") String token, @FormParam("email") String email, @FormParam("amount") String amount) throws Exception {

        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "gbp");
        chargeParams.put("source", token); // obtained with Stripe.js
        chargeParams.put("description", email);

        try {
            Charge.create(chargeParams);
            logger.info("Success: '"+email+"' just made a payment of '"+amount+"' in pence with token '"+token+"'");
            return Response.ok().build();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
