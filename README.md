# VestiaireWeather
This app will present a 5 days weather forecast.</br>
It's possible to click on header (current weather day) and each forecast weather future days to see extra info.

</br>
![Phone splash](https://raw.github.com/didi4yeah/VestiaireWeather/master/screenshots/VestaireWeather_phone_splash.png "Phone splash")
![Phone home](https://raw.github.com/didi4yeah/VestiaireWeather/master/screenshots/VestaireWeather_phone_home.png "Phone home")
![Phone details](https://raw.github.com/didi4yeah/VestiaireWeather/master/screenshots/VestaireWeather_phone_details.png "Phone details")

####API minimum level: 21

##Design
It seems a little bit gloomy at first glance but I used the same primary color as the Vestiaire Collective Android app. I created a Google Palete and used some of these colors, which suited my style in the end.

![Palette](https://raw.github.com/didi4yeah/VestiaireWeather/master/screenshots/VestaireWeather_palette.png "Palette")

I used ConstraintLayout as a new feature as I've already told I wanted to try this in the interview. It's efficient but many bugs in the layout editor could appear making it a little frustrating to use at the moment.

##Architecture
I made an MVP architecture to decoupled as much as different purposes as possible.
In practice, "View" layer (fragment) will communicate with "Presenter" layer that will transport "commands" to "Model" layer that will get back data for instance. Data will then get back to "Presenter" and "View" layer at last.

##Animations
Forecast list will come from the bottom.
And the weather icon (sun, cloud,...) is a shared element between home and details fragments so icon will move and transfrom to destination position.

##Universal app
App is compatible with handset phones and also tablets.
On tablet, there is only one main screen divided with forecast list pane at the left and the details weather pane at the right.
A click on an item of the left pane will change content in the right pane to get extra info.

![Tablet home](https://raw.github.com/didi4yeah/VestiaireWeather/master/screenshots/VestaireWeather_tablet.png "Tablet home")

##TODO
* List selector in tablet mode to know wich element is selected
* Offline mode (even though app won't crash if no network), was too long to do it but architecture is ready for it since model layer can be used to get back data from database for instance


