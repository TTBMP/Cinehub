package com.ttbmp.cinehub.app.repository.creditcard;

import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockCreditCardRepository implements CreditCardRepository {

    private static final List<CreditCardData> CREDIT_CARD_DATA_LIST = new ArrayList<>();

    static {
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(0, "4242424242424242", 354, "22/24", "0"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(1, "4242424242424242", 354, "22/24", "1"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(2, "4242424242424242", 354, "22/24", "2"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(3, "4242424242424242", 354, "22/24", "3"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(4, "4242424242424242", 354, "22/24", "4"));
    }

    public static List<CreditCardData> getCreditCardDataList() {
        return CREDIT_CARD_DATA_LIST;
    }

    @Override
    public CreditCard getCreditCard(Customer customer) {
        return CREDIT_CARD_DATA_LIST.stream()
                .filter(d -> d.userId.equals(customer.getId()))
                .map(d -> new CreditCardProxy(d.id, d.number, d.cvv, d.expirationDate))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class CreditCardData {

        private int id;
        private String number;
        private Integer cvv;
        private String expirationDate;
        private String userId;

        public CreditCardData(int id, String number, Integer cvv, String expirationDate, String userId) {
            this.id = id;
            this.number = number;
            this.cvv = cvv;
            this.expirationDate = expirationDate;
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Integer getCvv() {
            return cvv;
        }

        public void setCvv(Integer cvv) {
            this.cvv = cvv;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

}
