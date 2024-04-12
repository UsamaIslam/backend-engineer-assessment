package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import com.stripe.exception.StripeException;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "create-account-workflow")
public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private final Logger logger = Workflow.getLogger(CreateAccountWorkflowImpl.class);

  private final AccountActivity accountActivity =
      Workflow.newActivityStub(
          AccountActivity.class,
          ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

  @Override
  public Account createAccount(Account details) throws StripeException {
    logger.info("initiating workflow inside to create account for email: {}", details.getEmail());
    return accountActivity.saveAccount(accountActivity.createPaymentAccount(details));
  }
}
