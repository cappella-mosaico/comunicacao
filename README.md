# this service
this service is responsible for consuming different events being published by the other services and take specific communication actions based on those events. Currently it only supports sending push messages via one signal but a natural next step would be to have it sending emails as well. 

# prepare yourself

# build and run
`export ONE_SIGNAL_APP_ID="97bc067c-2344-4a86-a6b1-0206f51df4e9" && export ONE_SIGNAL_APP_KEY="NTgwOTNjYmEtNGZjYi00ZGYyLThmMTktMGM5MzdiNzRkZDkz" && mvn clean install`
