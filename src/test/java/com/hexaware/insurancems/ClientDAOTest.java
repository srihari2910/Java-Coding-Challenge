package com.hexaware.insurancems;

import java.util.List;

import org.junit.Test;

import com.hexaware.insurancems.beans.Client;
import com.hexaware.insurancems.beans.Policy;
import com.hexaware.insurancems.dao.IClientDAO;

import junit.framework.TestCase;

public class ClientDAOTest extends TestCase {
	private static IClientDAO clientDAO;

    @Test
    void testRegisterClient() {
        Policy policy = new Policy();
        policy.setPolicyId(1); // assumes policy with ID 1 exists

        Client client = new Client(0, "Test Client", "test@example.com", policy);
        boolean result = clientDAO.registerClient(client);
        assertTrue(result);
    }

    @Test
    void testGetAllClients() {
        List<Client> clients = clientDAO.getAllClients();
        assertNotNull(clients);
    }
}
