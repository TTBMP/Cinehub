package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.user.User;

/**
 * @author Fabio Buracchi
 */
public class UserProxy extends User {

    private final CreditCardRepository creditCardRepository;

    private boolean isCreditCardLoaded = false;

    public UserProxy(String id, String name, String surname, String email, CreditCardRepository creditCardRepository) {
        super(id, name, surname, email, null);
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard getCreditCard() {
        if (!isCreditCardLoaded) {
            setCreditCard(creditCardRepository.getCreditCard(this));
        }
        return super.getCreditCard();
    }

    @Override
    public void setCreditCard(CreditCard creditCard) {
        isCreditCardLoaded = true;
        super.setCreditCard(creditCard);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
