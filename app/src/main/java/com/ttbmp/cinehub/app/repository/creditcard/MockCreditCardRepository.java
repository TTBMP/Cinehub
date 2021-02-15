package com.ttbmp.cinehub.app.repository.creditcard;

import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockCreditCardRepository implements CreditCardRepository {

    private static final List<CreditCardData> CREDIT_CARD_DATA_LIST = new ArrayList<>();



    static {
        String numberCard ="4242424242424242";
        String expirationDate ="22/24";
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(0, numberCard, 354, expirationDate, "0"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(1, numberCard, 354, expirationDate, "1"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(2, numberCard, 354, expirationDate, "2"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(3, numberCard, 354, expirationDate, "3"));
        CREDIT_CARD_DATA_LIST.add(new CreditCardData(4, numberCard, 354, expirationDate, "4"));
    }

    public static List<CreditCardData> getCreditCardDataList() {
        return CREDIT_CARD_DATA_LIST;
    }

    @Override
    public CreditCard getCreditCard(User user) {
        return CREDIT_CARD_DATA_LIST.stream()
                .filter(d -> d.userId.equals(user.getId()))
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
