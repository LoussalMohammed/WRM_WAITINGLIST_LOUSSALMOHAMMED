package org.wrmList.waitingList.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// App Config
@Component
@ConfigurationProperties(prefix = "default")
public class AppConfig {

    private int capacity;
    private String orderingStrategy;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOrderingStrategy() {
        return orderingStrategy;
    }

    public void setOrderingStrategy(String orderingStrategy) {
        this.orderingStrategy = orderingStrategy;
    }
}
