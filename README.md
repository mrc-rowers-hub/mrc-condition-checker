# Condition Checker 
# 🛠️ WIP 🛠️
An application to check the weather conditions of Mersey Rowing Club at the 6am and 6pm (for morning and evening sessions) to see if boats can go ahead. 

You can view the swagger docs at http://localhost:8080/v3/api-docs 

## How to run
- Install the Env file plugin https://plugins.jetbrains.com/plugin/7861-envfile 
- Add your api key to the .env file - you need to sign up for a free OpenWeather account for this https://home.openweathermap.org/api_keys 
- Edit the run configurations for ConditionCheckerApplication -> edit configurations -> enable env file -> select the .env file 
- Run the app 

### Testing 
Prerequisites:
- Docker Desktop

Instructions:
- Run `docker-compose -f dockercompose.yaml ` (this starts the wiremock container)
- Run the maven unit tests 

# Application Logic and Reasoning
Currently, we are working from a [Miro](https://miro.com/app/board/uXjVPMF8Djc=/?moveToWidget=3458764584603444169&cot=14) (_currently private, please request access if necessary_) page, designed by Adelaide Baron. Additionally, work is being prioritised through the GitHub Project associated with this repository. 

### Conditions
Currently, the conditions are rough estimations of the limits at Mersey Rowing Club, but data such as the max temp has been obtained from external resources: https://plus.britishrowing.org/2023/06/08/beat-the-heat/ 

The conditions are set in the application.yml. 

### Reasons for this application
This process is currently a manual check done by a single member of the rowing club. This app, once complete, will enable crews to check this themselves to see if boats are going ahead. 