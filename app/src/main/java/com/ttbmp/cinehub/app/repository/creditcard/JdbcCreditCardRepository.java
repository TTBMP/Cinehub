package com.ttbmp.cinehub.app.repository.creditcard;

import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.User;

public class JdbcCreditCardRepository implements CreditCardRepository {
    @Override
    public CreditCard getCreditCard(User user) {
        //TODO: probabilmente da eliminare poiche non è presente la query
        return null;
    }
}
