import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import com.ttbmp.cinehub.service.payment.stripe.ServiceRequestStripe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ServiceRequestStripeTest {

    @Test
    void register_returnsValidCustomer_withCorrectElement() throws StripeException {
        String email = "valid@email.ok";
        String number="4242424242424242";
        String name="prova";
        ServiceRequestStripe serviceRequestStripe = new ServiceRequestStripe();
        Customer customer = serviceRequestStripe.register(email,number,name);
        assertNotNull(customer);
    }

}
