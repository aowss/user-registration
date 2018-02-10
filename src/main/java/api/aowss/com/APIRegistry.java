package api.aowss.com;

import java.util.HashMap;
import java.util.Map;

public class APIRegistry {


    public static final Map<String, String> registry;
    static {
        registry = new HashMap<>();

        registry.put("account",                 "/customer/accounts/{accountId}");
        registry.put("subscription",            "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}");
        registry.put("line-settings",           "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}/lines/{lineId}/settings");
        registry.put("plan-details",            "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}/lines/{lineId}/plan");
        registry.put("usage",                   "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}/usage?type={usageType}");
        registry.put("top-ups",                 "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}/top-ups");
        registry.put("subscription-settings",   "/customer/accounts/{accountId}/services/{serviceType}/{serviceId}/settings");

        registry.put("tasks",                   "/task/{taskId}");

        //registry.put("plan-details",            "/reference-data/plans/{planCode}");
        registry.put("top-up-details",          "/reference-data/top-ups/{topUpCode}");
    }

}
