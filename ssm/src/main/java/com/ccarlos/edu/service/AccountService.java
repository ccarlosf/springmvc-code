package com.ccarlos.edu.service;


import com.ccarlos.edu.pojo.Account;

import java.util.List;

public interface AccountService {
    List<Account> queryAccountList() throws Exception;
}
