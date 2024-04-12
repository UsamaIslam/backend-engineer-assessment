package com.midas.app.activities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.models.Account;
import com.midas.app.providers.external.stripe.StripePaymentProvider;
import com.midas.app.repositories.AccountRepository;
import com.stripe.exception.StripeException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AccountActivityImplTest {

  @Mock private AccountRepository accountRepository;

  @Mock private StripePaymentProvider stripePaymentProvider;

  @InjectMocks private AccountActivityImpl accountActivity;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void saveAccount() {
    Account account = new Account();
    account.setEmail("test@example.com");

    when(accountRepository.save(account)).thenReturn(account);

    Account savedAccount = accountActivity.saveAccount(account);

    assertEquals(account, savedAccount);
    verify(accountRepository, times(1)).save(account);
  }

  @Test
  void updateAccount() {
    UUID accountId = UUID.randomUUID();
    Account existingAccount = new Account();
    existingAccount.setId(accountId);
    existingAccount.setEmail("existing@example.com");

    Account updatedAccount = new Account();
    updatedAccount.setId(accountId);
    updatedAccount.setEmail("updated@example.com");

    when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));
    when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);

    Account result = accountActivity.updateAccount(accountId, updatedAccount);

    assertEquals(updatedAccount, result);
    assertEquals("updated@example.com", result.getEmail());
    verify(accountRepository, times(1)).findById(accountId);
    verify(accountRepository, times(1)).save(existingAccount);
  }

  @Test
  void updateAccount_NotFound() {
    UUID accountId = UUID.randomUUID();
    Account updatedAccount = new Account();

    when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

    assertThrows(
        ResourceNotFoundException.class,
        () -> accountActivity.updateAccount(accountId, updatedAccount));
  }

  @Test
  void createPaymentAccount() throws StripeException {
    Account account = new Account();
    account.setEmail("test@example.com");

    Account createdAccount = new Account();
    createdAccount.setEmail("created@example.com");

    when(stripePaymentProvider.createAccount(any())).thenReturn(createdAccount);

    Account result = accountActivity.createPaymentAccount(account);

    assertEquals(createdAccount, result);
    verify(stripePaymentProvider, times(1)).createAccount(any());
  }

  @Test
  void updatePaymentAccount() throws StripeException {
    Account account = new Account();
    account.setEmail("test@example.com");

    Account updatedAccount = new Account();
    updatedAccount.setEmail("updated@example.com");

    when(stripePaymentProvider.updateAccount(account)).thenReturn(updatedAccount);

    Account result = accountActivity.updatePaymentAccount(account);

    assertEquals(updatedAccount, result);
    verify(stripePaymentProvider, times(1)).updateAccount(account);
  }

  @Test
  void updatePaymentAccount_NullAccount() {
    assertThrows(ResourceNotFoundException.class, () -> accountActivity.updatePaymentAccount(null));
  }
}
