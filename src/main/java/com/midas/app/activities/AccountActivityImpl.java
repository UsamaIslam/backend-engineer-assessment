package com.midas.app.activities;

import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.mappers.Mapper;
import com.midas.app.models.Account;
import com.midas.app.providers.external.stripe.StripePaymentProvider;
import com.midas.app.repositories.AccountRepository;
import com.stripe.exception.StripeException;
import io.temporal.spring.boot.ActivityImpl;
import io.temporal.workflow.Workflow;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@ActivityImpl(taskQueues = {"create-account-workflow","update-account-workflow"})
public class AccountActivityImpl implements AccountActivity {
  private final Logger logger = Workflow.getLogger(AccountActivityImpl.class);

  private final AccountRepository accountRepository;

  private final StripePaymentProvider stripePaymentProvider;

  @Override
  public Account saveAccount(Account account) {
    logger.info("initiating saverepo {}", account.getEmail());

    return accountRepository.save(account);
  }

  @Override
  public Account updateAccount(UUID accountId, Account account) {
    logger.info("Initiating updateAccount for account with ID {}", accountId);

    // Retrieve the existing account from the repository
    Account existingAccount = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));
    if(existingAccount != null) {
      Mapper.updateAccount(existingAccount, account);
      return accountRepository.save(existingAccount);
    }
    return null;
  }

  @Override
  public Account createPaymentAccount(Account account) throws StripeException {
    logger.info("initiating createPaymentAccount {}", account.getEmail());

    return stripePaymentProvider.createAccount(Mapper.toCreateAccount(account));
  }

  @Override
  public Account updatePaymentAccount(Account account) throws StripeException {
    if(account == null){
      throw new ResourceNotFoundException("Account is Null");
    }
    return stripePaymentProvider.updateAccount(account);

  }
}
