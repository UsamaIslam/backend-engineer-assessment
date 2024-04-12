package com.midas.app.mappers;

import com.midas.app.models.Account;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.generated.model.AccountDto;
import lombok.NonNull;

public class Mapper {
  // Prevent instantiation
  private Mapper() {}

  /**
   * toAccountDto maps an account to an account dto.
   *
   * @param account is the account to be mapped
   * @return AccountDto
   */
  public static AccountDto toAccountDto(@NonNull Account account) {
    var accountDto = new AccountDto();

    accountDto
        .id(account.getId())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .email(account.getEmail())
        .providerId(account.getProviderId())
        .providerType(AccountDto.ProviderTypeEnum.STRIPE)
        .createdAt(account.getCreatedAt())
        .updatedAt(account.getUpdatedAt());

    return accountDto;
  }

  public static CreateAccount toCreateAccount(@NonNull Account account) {
    return CreateAccount.builder()
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .email(account.getEmail())
        .build();
  }

  public static void updateAccount(Account updateAccount, Account account) {
    if (account.getFirstName() != null) {
      updateAccount.setFirstName(account.getFirstName());
    }
    if (account.getLastName() != null) {
      updateAccount.setLastName(account.getLastName());
    }
    if (account.getEmail() != null) {
      updateAccount.setEmail(account.getEmail());
    }
    // Add other fields as needed
  }

  public static void updateAccountFromDTO(Account updateAccount, AccountDto account) {
    if (account.getFirstName() != null) {
      updateAccount.setFirstName(account.getFirstName());
    }
    if (account.getLastName() != null) {
      updateAccount.setLastName(account.getLastName());
    }
    if (account.getEmail() != null) {
      updateAccount.setEmail(account.getEmail());
    }
    // Add other fields as needed
  }
}
