# Building DRaw And Guess (DRAG) for Android

Video series where we build from scratch an Android application inspired by the board game [Passa o Desenho](http://www.mebo.pt/index.php/pt/catalog/todos-os-produtos/passa-o-desenho). 

The game is played by at least 5 players. Each player is assigned a drawing pad and the game rounds start with the random assignment of a word to each player. These words are kept secret until the end of the round. 

After assigning the words, each player represents his word through a drawing; you have a maximum of 60 seconds to do so. After that time, the drawing pad is given to another player (e.g. the one on your left). That player, upon receiving the pad, has another 60 seconds to, based on the received drawing, guess the depicted word. Having written the word and passed the block again, the player now has 60 more seconds to make a new drawing, now related to the word that the previous player thinks is being represented in the drawing that only he saw. 

The round ends when the drawing pad returns to its initial owner. At that time, the sequences represented in the drawing pads are observed by all players. Players whose words have survived the distortion score 1 point (actually the most fun in this game is watching the effects of unintended distortions 😉).

In this application the drawing pad is virtual and each player uses his device to participate in the game. Inter device communication is made using the publish-subscribe communication model, supported by [Firebase's Cloud FireStore](https://firebase.google.com/docs/firestore/query-data/listen).

Some considerations regarding this endeavor: 
* The application uses [Jetpack](https://developer.android.com/jetpack/getting-started) 
* The application architecture is based on the [official reference architecture](https://developer.android.com/jetpack/guide), with small variations whenever it makes sense. In particular it will be combined with ideas from the [Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
* Keep in mind that I will use this application as a way for experimenting with *not so* familiar techniques in the context of my continuous learning process, namely:
  * The design favors composition over techniques based on inheritance and polymorphism. The latter will only be used when enforced by the framework
  * The solution favors immutability. Mutations are only used when we are unable to avoid them
  * Try out [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAiA9vOABhBfEiwATCi7GHrub-ix_UboLPlbp8z1ZTSFawUSu4nF2diQkTvVE_8PWr_dajQEghoC7qkQAvD_BwE&gclsrc=aw.ds)
  * Try out [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) in an Android application
  
## Related links
* [Project Board](https://github.com/palbp/sempre_a_codar/projects/1)
* [Playlist on YouTube](https://www.youtube.com/playlist?list=PL8XxoCaL3dBiSG-AWNXkQg3n9Nf4jBoXc)
* [Schedule on Twitch](https://www.twitch.tv/paulo_pereira/schedule)
