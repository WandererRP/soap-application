package com.roland.soap.endpoints;

import com.roland.soap.dto.accounts.*;
import com.roland.soap.models.Account;
import com.roland.soap.services.AccountService;
import com.roland.soap.util.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * @author Roland Pilpani 28.12.2022
 */
@Endpoint
public class AccountDetailsEndpoint {

    private final AccountService accountService;

    @Autowired
    public AccountDetailsEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @PayloadRoot(namespace = "http://soap.roland.com/dto/accounts",
            localPart = "GetAccountDetailsRequest")
    @ResponsePayload
    public GetAccountDetailsResponse processAccountDetailsRequest
            (@RequestPayload GetAccountDetailsRequest request) {

        Account account = accountService.findById(request.getId());

        if (account == null) {
            throw new AccountNotFoundException("Invalid Account Id " + request.getId());
        }

        return mapCourseDetails(account);
    }



    @PayloadRoot(namespace = "http://soap.roland.com/dto/accounts",
            localPart = "GetAllAccountDetailsRequest")
    @ResponsePayload
    public GetAllAccountDetailsResponse processAllAccountDetailsRequest
            (@RequestPayload GetAllAccountDetailsRequest request) {

        List<Account> accounts = accountService.findAll();

        return mapAllCourseDetails(accounts);
    }

    private GetAllAccountDetailsResponse mapAllCourseDetails(List<Account> accounts) {
        GetAllAccountDetailsResponse response = new GetAllAccountDetailsResponse();

        for (Account account : accounts) {
            response.getAccountDetails().add(mapAccount(account));
        }

        return response;
    }

    private GetAccountDetailsResponse mapCourseDetails(Account account) {

        GetAccountDetailsResponse response = new GetAccountDetailsResponse();
        response.setAccountDetails(mapAccount(account));
        return response;
    }

    private AccountDetails mapAccount(Account account) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setSum(account.getSum());
        return accountDetails;
    }

}
