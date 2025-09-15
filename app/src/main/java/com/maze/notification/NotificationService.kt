package com.maze.notification

import android.app.Service
//import android.car.Car
//import android.car.drivingstate.CarDrivingStateManager
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log


class NotificationService : Service() {
    private val TAG: String  = "NotificationService"

    override fun onBind(intent: Intent): IBinder? {
        /**"Return the communication channel to the service."**/
        return null
    }

    /**
     * Need access to car dependencies, permissions, properties and relevant APIs.
     * **/
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val car: Car = Car.createCar(applicationContext)
//        val carDriverManager: CarDrivingStateManager =
//            car.getCarManager(Car.CAR_DRIVING_STATE_SERVICE) as CarDrivingStateManager
//
//        var driverStateListener = CarDrivingStateManager.CarDrivingStateEventListener {
//            carDrivingStateEvent ->
//            run {
//                Log.d(TAG, "carDrivingStateEvent: $carDrivingStateEvent");
//                if (carDriverManager.currentCarDrivingState.eventValue == 0) {
//                    Handler().postDelayed({
//                        val notificationHandler = NotificationHandler(applicationContext)
//                        notificationHandler.createNotification(notificationLabel, notificationDescription)
//                    }, 3000)
//                }
//            }
//        }
//        carDriverManager.registerListener(driverStateListener)
//
//        if (carDriverManager.currentCarDrivingState.eventValue == 0) {
//            val notificationHandler = NotificationHandler(applicationContext)
//            notificationHandler.createNotification(notificationLabel, notificationDescription)
//        }
//
//        return super.onStartCommand(intent, flags, startId)
//    }

    companion object {
        const val notificationDescription = "This is a high ticket zone or restricted parking area"
        const val notificationLabel = "Possible Parking Violation Warning"
    }
}