package com.taghavi.forecastmvvmtest.internal

import java.io.IOException

class NoConnectivityException : IOException()
class LocationPermissionNotGrantedException : Exception()
class DateNotFoundException : Exception()