package com.midas.app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.midas.app.models.Account;
import com.midas.app.services.AccountService;
import com.midas.generated.model.AccountDto;
import com.midas.generated.model.CreateAccountDto;
import com.stripe.exception.StripeException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AccountControllerTest {

  @Mock private AccountService accountService;

  @InjectMocks private AccountController accountController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createUserAccount() throws StripeException {
    CreateAccountDto createAccountDto = new CreateAccountDto();
    createAccountDto.setEmail("test@example.com");
    createAccountDto.setFirstName("John");
    createAccountDto.setLastName("Doe");

    Account createdAccount = new Account();
    createdAccount.setEmail("test@example.com");
    createdAccount.setFirstName("John");
    createdAccount.setLastName("Doe");

    when(accountService.createAccount(any())).thenReturn(createdAccount);

    ResponseEntity<AccountDto> responseEntity =
        accountController.createUserAccount(createAccountDto);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("test@example.com", responseEntity.getBody().getEmail());
    assertEquals("John", responseEntity.getBody().getFirstName());
    assertEquals("Doe", responseEntity.getBody().getLastName());
  }

  @Test
  void getUserAccounts() {
    Account account = new Account();
    account.setEmail("test@example.com");
    account.setFirstName("John");
    account.setLastName("Doe");

    when(accountService.getAccounts()).thenReturn(Collections.singletonList(account));

    ResponseEntity<List<AccountDto>> responseEntity = accountController.getUserAccounts();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(1, responseEntity.getBody().size());
    assertEquals("test@example.com", responseEntity.getBody().get(0).getEmail());
    assertEquals("John", responseEntity.getBody().get(0).getFirstName());
    assertEquals("Doe", responseEntity.getBody().get(0).getLastName());
  }

  @Test
  void updateUserAccount() throws StripeException {
    UUID accountId = UUID.randomUUID();
    AccountDto accountDto = new AccountDto();
    accountDto.setEmail("updated@example.com");

    Account updatedAccount = new Account();
    updatedAccount.setEmail("updated@example.com");

    when(accountService.updateAccount(any(), any())).thenReturn(updatedAccount);

    ResponseEntity<AccountDto> responseEntity =
        accountController.updateUserAccount(accountId, accountDto);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("updated@example.com", responseEntity.getBody().getEmail());
  }
}
