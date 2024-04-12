package com.midas.app.workflows;

import com.midas.app.models.Account;
import com.stripe.exception.StripeException;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

@WorkflowInterface
public interface UpdateAccountWorkflow {
  String UPDATE_QUEUE_NAME = "update-account-workflow";
  /**
   * updateAccount creates a new account in the system or provider.
   *
   * @param accountId is the id of the account to be updated.
   * @param details is the details of the account to be updated.
   * @return Account
   */
  @WorkflowMethod
  Account updateAccount(UUID accountId, Account details) throws StripeException;
}
