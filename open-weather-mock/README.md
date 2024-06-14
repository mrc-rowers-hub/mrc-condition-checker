# Open-weather-api mock 

## How to run
Prerequisites:
- Docker Desktop

Instructions:
- Run `docker-compose -f dockercompose.yaml logs` (this starts the wiremock container)

Wiremock is used for mocking the open weather api. We currently have no contract tests in place for this, and manually check this. There is a ticket in place for this. 