package com.navektest.core_common.model


enum class DataState {
    LOADING, SUCCESS, ERROR, IDLE
}

open class StateDataModel<T> protected constructor(
    open val state: DataState,
    open val data: T? = null,
    open val error: Throwable? = null
) {
    fun hasSuccess(): Boolean {
        return data != null
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun isLoading(): Boolean {
        return state == DataState.LOADING
    }

    companion object {
        fun <T> loading(): StateDataModel<T> {
            return StateDataModel(
                DataState.LOADING,
                null,
                null
            )
        }

        fun <T> error(error: Throwable): StateDataModel<T> {
            return StateDataModel(
                DataState.ERROR,
                null,
                error
            )
        }

        fun <T> success(data: T): StateDataModel<T> {
            return StateDataModel(
                DataState.SUCCESS,
                data,
                null
            )
        }

        fun <T> idle(): StateDataModel<T> {
            return StateDataModel(
                DataState.IDLE,
                null,
                null
            )
        }
    }
}