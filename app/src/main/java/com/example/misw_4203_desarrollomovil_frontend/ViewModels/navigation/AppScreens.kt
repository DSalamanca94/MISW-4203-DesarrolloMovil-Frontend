package com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("first_screen");
    object SecondScreen: AppScreens("second_screen");
    object ThirdScreen: AppScreens("third_screen");
    object FourthScreen: AppScreens("fourth_screen");
    object FifthScreen: AppScreens("fifth_screen");
    object SixthScreen: AppScreens("sixth_screen");
    object SeventhScreen: AppScreens("seventh_screen");
    object EigthScreen: AppScreens("eigth_screen");
}
