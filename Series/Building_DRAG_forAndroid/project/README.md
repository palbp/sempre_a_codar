# DRaw And Guess (DRAW) design notes

* The application architecture is based on the [official reference architecture](https://developer.android.com/jetpack/guide), 
  with small variations whenever it makes sense   
* Similarly to Activity and ViewModel, LiveData is a UI related concept: it will only be used to push 
  notifications from the ViewModel to the Activity 