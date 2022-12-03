# PipouBrushApp

## Setup
1. In your terminal : Clone this repo with : 
```sh
git clone git@github.com:Illiouchine/PipouBrushApp.git
```
2. Initialises submodules with :
```sh
git submodule update --init --recursive
```
3. Open the project in Android Studio
4. Do a gradle sync
5. Now you should be able to build and deploy
6. Enjoy <3

## Submodules
This project use the MVI (Model View Intent) architecture and use a custom mvi library : 
[https://github.com/Illiouchine/mvi-library](https://github.com/Illiouchine/mvi-library)


## Used Libraries
- Mvi architecture with [mvi-library](https://github.com/Illiouchine/mvi-library)
- Ui Rendering with [compose](https://developer.android.com/jetpack/compose)
- Ui Design with [compose-material3](https://developer.android.com/jetpack/androidx/releases/compose-material3?hl=en)
- Data Storage with [room](https://developer.android.com/training/data-storage/room)
- Dependencies injection with [hilt-android](https://developer.android.com/training/dependency-injection/hilt-android)
- Testing with [mockito](https://developer.android.com/training/testing/local-tests)
- Asynchronous with [Coroutine and Flow](https://developer.android.com/kotlin/flow)

## TODO
- [x] user can start a 3:00 countdown
- [x] user can reset countdown to 3:00
- [x] make the countdown prettier
- [x] make total countdown dynamic
- [x] count the number of use to anticipate achievement
- [x] save Brush Duration in shared pref
- [x] setup achievement ? 
- [ ] setup reminder of brushing : 
  - For Alarm -> see "Set a repeating alarm"
  - For notification -> 
- [ ] setup Mini game when brushing
