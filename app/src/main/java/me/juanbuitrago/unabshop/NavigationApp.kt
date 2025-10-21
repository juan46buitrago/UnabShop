package me.juanbuitrago.unabshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun NavigationApp(){
    val myNavController = rememberNavController()
    var myStartDestination: String ="login"

    val  auth = Firebase.auth
    var currentUser = auth.currentUser

    if (currentUser != null){
        myStartDestination="home"
    }else{
        myStartDestination="login"
    }

    NavHost(
        navController = myNavController,
        startDestination = myStartDestination
    ){
composable("login") {
    LoginScreen (onClickRegister = {
      myNavController.navigate("register")
    }, onSuccessfullogin = {
        myNavController.navigate("home"){
            popUpTo("login"){inclusive = true}
        }
    })
}
composable("register") {
    RegisterScreen(onClickBack={
        myNavController.popBackStack()
    }, onSuccesfulRegister = {
        myNavController.navigate("home"){
          popUpTo(0)
        }
    })
}
composable("home") {
    HomeScreen(onClickLogout = {
        myNavController.navigate("login"){
            popUpTo(0)
        }
    })
}
    }

    }











