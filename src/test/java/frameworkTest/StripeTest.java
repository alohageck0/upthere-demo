package frameworkTest;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class StripeTest {

   @Test
   public void test() {
      Stripe.apiKey = "sk_test_FaGYmLqCZvd4ws2CAAZiEChw";
//      RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("sk_test_FaGYmLqCZvd4ws2CAAZiEChw:").build();
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("customer", "cus_AnPVB4xwmquqse");
      Map<String, Object> invoiceParams = new HashMap<String, Object>();
      invoiceParams.put("customer", "cus_AtkBNQpBSULZqz");
      invoiceParams.put("limit", 1);

      try {
//         SubscriptionCollection subscriptions = Subscription.list(params);
////         Customer cus = Customer.retrieve("cus_B4GamQnzgZLWSB");
////         Charge charge = Charge.create(chargeMap, requestOptions);
////         CustomerSubscriptionCollection sub = cus.getSubscriptions();
//         List<Subscription> list = subscriptions.getData();
         System.out.println(Invoice.list(invoiceParams)
         );
      } catch (StripeException e) {
         e.printStackTrace();
      }
   }
}
