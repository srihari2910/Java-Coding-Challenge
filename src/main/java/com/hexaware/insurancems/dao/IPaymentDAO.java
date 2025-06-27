package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Payment;
import java.util.List;

public interface IPaymentDAO {
    boolean addPayment(Payment payment);
    List<Payment> getPaymentsByClientId(int clientId);
}
