package com.example.accounts.service;

import com.example.accounts.dto.CustomerDto;

public interface IAccountsService {

    public void CreateAccount(CustomerDto customerDto);

    public CustomerDto fetchCustomer(String mobileNumber);

    public boolean updateCustomer(CustomerDto customerDto);

    public  boolean deleteAccount(CustomerDto customerDto);
}
