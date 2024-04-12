package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import com.stripe.exception.StripeException;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "update-account-workflow")
public class UpdateAccountWorkflowImpl implements UpdateAccountWorkflow {

  private final Logger logger = Workflow.getLogger(UpdateAccountWorkflowImpl.class);

  private final AccountActivity accountActivity =
      Workflow.newActivityStub(
          AccountActivity.class,
          ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

  @Override
  public Account updateAccount(UUID accountId, Account details) throws StripeException {
    return accountActivity.updatePaymentAccount(accountActivity.updateAccount(accountId, details));
  }
}
