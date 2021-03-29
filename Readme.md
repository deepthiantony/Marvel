# Marvel
A simple service API written in Spring Boot Java which uses Marvel API (see http://developer.marvel.com/) to build a Characters API.

## Usage
There are two api calls implemented:
1. HTTP get method call marvel-api-service/v1/characters that returns all the Marvel character ids only, in a JSON array of numbers.
EHCache is used for caching the data since Marvel API only returns max 100 records per request.

2. HTTP get method call marvel-api-service/v1/characters/{characterId} to get details like id, name, description, thumbnail of the given characterId.

## Requirements
   This application uses Maven as the build tool and requires JDK 14.0.2.
   
## License
© Deepthi Antony K
Licensed under [MIT License](LICENSE)
