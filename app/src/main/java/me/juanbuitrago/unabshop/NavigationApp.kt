package me.juanbuitrago.unabshop

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationApp(){
    val myNavController = rememberNavController()
    val myStartDestination: String ="Login"

    NavHost(
        navcontroller = myNavController,
        startDestination = myStartDestination
    ){
        composable("login"){
            LoginScreen (onClickRegister = {
                myNavController.navigate("register")
            }, onSuccessfullogin = {
                myNavController.navigate("home")
            })
        }
    composable("register"){
        RegisterScreen(onClickBack ={
            myNavController.popBackStack()
        })
    }



    }









}

