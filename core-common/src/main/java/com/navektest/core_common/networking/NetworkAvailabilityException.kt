package com.navektest.core_common.networking

import okhttp3.Interceptor

/**
 * Exception thrown when network is not available
 */
class NetworkAvailabilityException(message: String) : Exception(message)