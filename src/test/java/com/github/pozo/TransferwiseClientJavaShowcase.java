package com.github.pozo;

import com.github.pozo.client.TransferwiseClient;
import com.github.pozo.domain.Account;
import com.github.pozo.domain.Profile;
import com.github.pozo.properties.ConfigurationProvider;
import com.github.pozo.properties.LocalProperties;
import kotlin.Unit;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TransferwiseClientJavaShowcase implements DemonstrateTransferwiseClient {

    private TransferwiseClient client;

    TransferwiseClientJavaShowcase() {
        final String token = LocalProperties.INSTANCE.getTransferwiseApiToken();
        client = new TransferwiseClient(ConfigurationProvider.INSTANCE.production(token));
    }

    public static void main(String[] args) {
        TransferwiseClientJavaShowcase showcase = new TransferwiseClientJavaShowcase();
        showcase.getAllAccountsBalanceAsync();
    }

    @Override
    public void getAllProfiles() {
        client.getProfiles().fold(profiles -> {
            profiles.forEach(System.out::println);
            return profiles;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.emptyList();
        });
    }

    @Override
    public void getAllAccounts() {
        client.getProfiles().fold(profiles -> {
            profiles.forEach(System.out::println);
            return profiles;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Profile>emptyList();
        }).forEach(profile -> client.getAccounts(profile.getId()).fold(accounts -> {
            accounts.forEach(System.out::println);
            return accounts;
        }, fuelError -> {
            return Collections.<Account>emptyList();
        }));
    }

    @Override
    public void getAllAccountsBalance() {
        client.getProfiles().fold(profiles -> {
            profiles.forEach(System.out::println);
            return profiles;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Profile>emptyList();
        }).forEach(profile -> client.getAccounts(profile.getId()).fold(accounts -> {
            accounts.forEach(System.out::println);
            return accounts;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Account>emptyList();
        }).forEach(account -> {
            account.getBalances().forEach(System.out::println);
        }));
    }

    @Override
    public void getAllAccountsBalanceAsync() {
        client.getProfiles().fold(profiles -> {
            profiles.forEach(System.out::println);
            return profiles;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Profile>emptyList();
        }).forEach(profile -> client.getAccounts(profile.getId(), result -> {
            List<Account> listOfAccounts = result.fold(accounts -> {
                accounts.forEach(System.out::println);
                return accounts;
            }, fuelError -> {
                System.out.println(fuelError.getMessage());
                return Collections.emptyList();
            });
            listOfAccounts.forEach(account -> {
                System.out.println("account.balances = " + account.getBalances());
            });
            return Unit.INSTANCE;
        }));
    }

    @Override
    public void getAllAccountsBalanceAndItsStatementForTheLastMonth() {
        client.getProfiles().fold(profiles -> {
            profiles.forEach(System.out::println);
            return profiles;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Profile>emptyList();
        }).forEach(profile -> client.getAccounts(profile.getId()).fold(accounts -> {
            accounts.forEach(System.out::println);
            return accounts;
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return Collections.<Account>emptyList();
        }).forEach(account -> account.getBalances().forEach(balance -> client.getStatement(
                account.getId(),
                balance.getCurrency(),
                ZonedDateTime.now().minusMonths(1),
                ZonedDateTime.now()
        ).fold(statement -> {
            System.out.println("statement = " + statement);
            return of(statement);
        }, fuelError -> {
            System.out.println(fuelError.getMessage());
            return empty();
        }))));
    }

    @Override
    public void getAllTransfers() {
        client.getTransfers().fold(transfers -> {
            System.out.println("OK = " + transfers);
            return transfers;
        }, fuelError -> {
            System.out.println("ERROR = " + fuelError.getMessage());
            return Collections.emptyList();
        });
    }

    @Override
    public void getAllTransfersAsync() {
        client.getTransfers(result -> {
            result.fold(transfers -> {
                System.out.println("OK = " + transfers);
                return transfers;
            }, fuelError -> {
                System.out.println("ERROR = " + fuelError.getMessage());
                return Collections.emptyList();
            });
            return Unit.INSTANCE;
        });
    }

    @Override
    public void getAllAvailableCurrency() {
        client.getAvailableCurrencies().fold(currencies -> {
            currencies.forEach(System.out::println);
            return currencies;
        }, fuelError -> Collections.emptyList());
    }

    @Override
    public void getExchangeRatesForEUR_HUFPair() {
        client.getExchangeRates("EUR", "HUF").fold(currencies -> {
            currencies.forEach(System.out::println);
            return currencies;
        }, fuelError -> Collections.emptyList());
    }

    @Override
    public void getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December() {
        client.getExchangeRates(
                "EUR",
                "HUF",
                ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
                ExchangeGroup.DAY
        ).fold(currencies -> {
            currencies.forEach(System.out::println);
            return currencies;
        }, fuelError -> Collections.emptyList());
    }
}
