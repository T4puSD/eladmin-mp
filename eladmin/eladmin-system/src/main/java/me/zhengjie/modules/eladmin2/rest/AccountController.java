package me.zhengjie.modules.eladmin2.rest;

import me.zhengjie.modules.eladmin2.domain.Account;
import me.zhengjie.modules.mappers.secondary.eladmin2.AccountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountMapper accountMapper;

    public AccountController(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountMapper.findAll();
    }
}
