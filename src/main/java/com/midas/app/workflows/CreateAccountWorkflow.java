package com.midas.app.workflows;

import com.midas.app.models.Account;
import com.stripe.exception.StripeException;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

@WorkflowInterface
public interface CreateAccountWorkflow {
  String QUEUE_NAME = "create-account-workflow";
  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @WorkflowMethod
  Account createAccount(Account details) throws StripeException;

}
