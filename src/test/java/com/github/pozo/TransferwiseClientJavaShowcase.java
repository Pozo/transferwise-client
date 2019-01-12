package com.github.pozo;

import com.github.pozo.client.TransferwiseClient;
import com.github.pozo.properties.ConfigurationProvider;
import com.github.pozo.properties.LocalProperties;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TransferwiseClientJavaShowcase implements DemonstrateTransferwiseClient {

    private TransferwiseClient client;

    TransferwiseClientJavaShowcase() {
        final String token = LocalProperties.INSTANCE.getTransferwiseApiToken();
        client = new TransferwiseClient(ConfigurationProvider.INSTANCE.production(token));
    }

    public static void main(String[] args) {
        TransferwiseClientJavaShowcase showcase = new TransferwiseClientJavaShowcase();
        showcase.getAllAccounts();
    }

    @Override
    public void getAllProfiles() {
        client.getProfiles().forEach(System.out::println);
    }

    @Override
    public void getAllAccounts() {
        client.getProfiles().forEach(profile -> client.getAccounts(profile.getId())
                .forEach(System.out::println));
    }

    @Override
    public void getAllAccountsBalance() {
        client.getProfiles().forEach(profile -> client.getAccounts(profile.getId())
                .forEach(account -> account.getBalances()
                        .forEach(System.out::println)));
    }

    @Override
    public void getAllAccountsBalanceAndItsStatementForTheLastMonth() {
        client.getProfiles().forEach(profile -> client.getAccounts(profile.getId())
                .forEach(account -> account.getBalances()
                        .forEach(balance -> {
                            client.getStatement(
                                    account.getId(),
                                    balance.getCurrency(),
                                    ZonedDateTime.now().minusMonths(1),
                                    ZonedDateTime.now()
                            ).ifPresent(System.out::println);
                        })
                ));
    }

    @Override
    public void getAllTransfers() {
        client.getTransfers().forEach(System.out::println);
    }

    @Override
    public void getAllAvailableCurrency() {
        client.getAvailableCurrencies().forEach(System.out::println);
    }

    @Override
    public void getExchangeRatesForEUR_HUFPair() {
        client.getExchangeRates("EUR", "HUF").forEach(System.out::println);
    }

    @Override
    public void getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December() {
        client.getExchangeRates(
                "EUR",
                "HUF",
                ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
                ExchangeGroup.DAY
        ).forEach(System.out::println);
    }
}
