package com.hexaware.insurancems.dao;

import com.hexaware.insurancems.beans.Claim;
import java.util.List;

public interface IClaimDAO {
    boolean fileClaim(Claim claim);
    boolean updateClaimStatus(int claimId, String status);
    List<Claim> getClaimsByClientId(int clientId);
    Claim getClaimById(int claimId);
}
