package com.github.pozo;

import com.github.pozo.client.TransferwiseClient;
import com.github.pozo.properties.ConfigurationProvider;
import com.github.pozo.properties.LocalProperties;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ExampleApplication {

    public static void main(String[] args) {
        String token = LocalProperties.INSTANCE.getTransferwiseApiToken();
        TransferwiseClient client = new TransferwiseClient(ConfigurationProvider.INSTANCE.production(token));

        client.getTransfers().forEach(System.out::println);
        client.getCurrentlyLoggedInUser().ifPresent(it -> {
            System.out.println(client.getUserById(it.getId()));
        });
        client.getProfiles().forEach(profile -> {
            client.getProfileById(profile.getId());
            client.getAddresses(profile.getId()).forEach(address -> {
                client.getAddressById(address.getId()).ifPresent(System.out::println);
            });
        });

        client.getExchangeRates("EUR", "HUF").forEach(System.out::println);

        client.getExchangeRates(
                "EUR",
                "HUF",
                ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 10, 0, 0), ZoneId.systemDefault())
        ).forEach(System.out::println);

        client.getExchangeRates(
                "EUR",
                "HUF",
                ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
                ExchangeGroup.DAY
        ).forEach(System.out::println);
    }
}
