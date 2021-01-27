import com.ttbmp.cinehub.core.datamapper.UserDataMapper;
import com.ttbmp.cinehub.core.entity.CreditCard;
import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;
import com.ttbmp.cinehub.service.payment.stripe.StripeServicePaymentFacade;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


class StripeServicePaymentFacadeTest {

    @Test
    void pay_returnsTrue_withCorrectElement()  {
        User user = new User("Ivan", "palm@ciao.cosiss", new CreditCard("22/24", 354, "4242424242424242", "5496"));
        StripeServicePaymentFacade stripeServicePaymentFacade = new StripeServicePaymentFacade();
        PayServiceRequest request = new PayServiceRequest(UserDataMapper.mapToDto(user),5);
        assertTrue(stripeServicePaymentFacade.pay(request));
    }

    @Test
     void pay_returnsFalse_withIncorrectUser()  {
        StripeServicePaymentFacade stripeServicePaymentFacade = new StripeServicePaymentFacade();
        PayServiceRequest request = new PayServiceRequest(null,5);
        assertFalse(stripeServicePaymentFacade.pay(request));
    }

    @Test
    void pay_returnsFalse_withIncorrectUserCreditCard()  {
        User user = new User("Ivan", "palm@ciao.cosiss", null);
        StripeServicePaymentFacade stripeServicePaymentFacade = new StripeServicePaymentFacade();
        PayServiceRequest request = new PayServiceRequest(UserDataMapper.mapToDto(user),5);
        assertFalse(stripeServicePaymentFacade.pay(request));
    }


}
