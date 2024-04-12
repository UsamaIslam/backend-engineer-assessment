// package com.midas.app.providers.external.stripe;
//
// import com.midas.app.models.Account;
// import com.midas.app.providers.payment.CreateAccount;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.Customer;
// import com.stripe.param.CustomerCreateParams;
// import com.stripe.param.CustomerUpdateParams;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Value;
//
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// class StripePaymentProviderTest {
//
//    @Value("${stripe.api-key}")
//    private String apiKey;
//    @Mock
//    private Customer customer;
//
//    @InjectMocks
//    private StripePaymentProvider stripePaymentProvider;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        Stripe.apiKey = apiKey; // Set a test API key
//    }
//
//    @Test
//    void createAccount() throws StripeException {
//        CreateAccount createAccount = new CreateAccount();
//        createAccount.setFirstName("John");
//        createAccount.setLastName("Doe");
//        createAccount.setEmail("test@example.com");
//
//        CustomerCreateParams expectedParams = CustomerCreateParams.builder()
//                .setName("John Doe")
//                .setEmail("test@example.com")
//                .build();
//
//        when(Customer.create(expectedParams)).thenReturn(customer);
//
//        Account result = stripePaymentProvider.createAccount(createAccount);
//
//        assertNotNull(result);
//        assertEquals("John", result.getFirstName());
//        assertEquals("Doe", result.getLastName());
//        assertEquals("test@example.com", result.getEmail());
//    }
//
//    @Test
//    void updateAccount() throws StripeException {
//        Account account = new Account();
//        account.setProviderId("test_provider_id");
//        account.setFirstName("John");
//        account.setLastName("Doe");
//        account.setEmail("updated@example.com");
//
//        CustomerUpdateParams expectedParams = CustomerUpdateParams.builder()
//                .setName("John Doe")
//                .setEmail("updated@example.com")
//                .build();
//
//        when(Customer.retrieve("test_provider_id")).thenReturn(customer);
//
//        Account result = stripePaymentProvider.updateAccount(account);
//
//        assertNotNull(result);
//        assertEquals("John", result.getFirstName());
//        assertEquals("Doe", result.getLastName());
//        assertEquals("updated@example.com", result.getEmail());
//    }
// }
