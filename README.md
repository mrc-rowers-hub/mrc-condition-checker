# Condition Checker 

todo 

## How to run
- Install the Env file plugin https://plugins.jetbrains.com/plugin/7861-envfile 
- Add your api key to the .env file - you need to sign up for a free OpenWeather account for this https://home.openweathermap.org/api_keys 
- Edit the run configurations for ConditionCheckerApplication -> edit configurations -> enable env file -> select the .env file 
- Run the app 

## testing 
Prerequisites:
- Docker Desktop

Instructions:
- Run `docker-compose -f dockercompose.yaml ` (this starts the wiremock container)