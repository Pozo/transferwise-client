## transferwise-client

Kotlin client for the [transferwise api](https://api-docs.transferwise.com)
    
#### Build and run the example application

You can use this library for accessing Transferwise api with Kotlin or Java, however the target language is Kotlin.

#### Quick example

Initiate the client

    private val client = TransferwiseClient(ConfigurationProvider.sandbox())

Retrieving exchange rates without error handling
    
    client.getExchangeRates().map { exchangeRates ->
        exchangeRates.forEach { println(it) }
    }

Retrieving exchange rates with error handling

    when (val result = client.getExchangeRates()) {
        is Result.Failure -> {
            result.error
        }
        is Result.Success -> {
            result.value.forEach { println(it) }
        }
    }

Retrieving exchange rates asynchronously

    client.getExchangeRates {
        it.map { exchangeRates ->
            exchangeRates.forEach { exchangeRate ->
                println(exchangeRate)
            }
        }
    }
    
Check out more advanced examples in the `test` folder.
    
#### Usage

First of all you have to [grab a user token](https://api-docs.transferwise.com/#bank-integrations-guide-get-user-tokens) from Transferwise. Then create a `transferwise.token` file into the `resources` folder with this token.

#### TODO 

 - Writing tests
 - ~~Providing async api~~
 - Error handling
 - Logging
 - Documentation
 - Versioning and release process
 - Adjust data classes field types (right now a lot of field type is String)

#### Licensing 

Please see LICENSE file

#### Contact

Zoltan Polgar - pozo@gmx.com

Please do not hesitate to contact me if you have any further questions.