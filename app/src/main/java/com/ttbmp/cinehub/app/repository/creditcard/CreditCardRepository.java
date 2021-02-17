package com.ttbmp.cinehub.app.repository.creditcard;

import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.user.User;

/**
 * @author Fabio Buracchi
 */
public interface CreditCardRepository {

    CreditCard getCreditCard(User user);

}
