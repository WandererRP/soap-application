package com.roland.soap.endpoints;

import com.roland.soap.dto.accounts.AccountDetails;
import com.roland.soap.dto.accounts.GetAccountDetailsRequest;
import com.roland.soap.dto.accounts.GetAccountDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Roland Pilpani 28.12.2022
 */
@Endpoint
public class AccountDetailsEndpoint {

    @PayloadRoot(namespace = "http://soap.roland.com/dto/accounts",
            localPart = "GetAccountDetailsRequest")
    @ResponsePayload
    public GetAccountDetailsResponse processAccountDetailsRequest
            (@RequestPayload GetAccountDetailsRequest request){
        GetAccountDetailsResponse response = new GetAccountDetailsResponse();

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(request.getId());
        accountDetails.setName("Roland Pilpani");
        accountDetails.setSum(1000.5);

        response.setAccountDetails(accountDetails);
        return response;

    }

}
