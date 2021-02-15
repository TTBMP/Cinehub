package com.ttbmp.cinehub.app.repository.creditcard;

import com.ttbmp.cinehub.domain.CreditCard;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditCardProxy extends CreditCard {

    public CreditCardProxy(int id, String number, int cvv, String expirationDate) {
        super(id, number, cvv, expirationDate);
    }

}
