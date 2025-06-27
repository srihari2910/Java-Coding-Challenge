package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Client;
import java.util.List;

public interface IClientDAO {
    boolean registerClient(Client client);
    List<Client> getAllClients();
    Client getClientById(int clientId);
    boolean updateClient(Client client);
    boolean deleteClient(int clientId);
}
