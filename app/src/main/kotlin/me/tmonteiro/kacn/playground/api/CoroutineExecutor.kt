package me.tmonteiro.kacn.playground.api

import br.com.arch.toolkit.livedata.response.MutableResponseLiveData
import br.com.arch.toolkit.livedata.response.ResponseLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private val job = SupervisorJob()
private val scope = CoroutineScope(Dispatchers.IO + job)

/**
 * Execute some function on the WorkerThread
 *
 * @param func Block to be executed in background
 */
fun <T> async(func: suspend () -> T) {
    scope.launch { func.invoke() }
}

/**
 * Create a ResponseLiveData Instance posting Loading, Success or Error
 * All parameters are executes on the WorkerThread
 *
 * @param errorTransformer To invoke the regular Exception caused by the param "func"
 * @param func Return the success value on the ResponseLiveData
 */
fun <T> makeAsyncOperation(
    errorTransformer: (Throwable) -> Throwable,
    func: suspend () -> T
): ResponseLiveData<T> {
    val liveData = MutableResponseLiveData<T>()
    async {
        try {
            liveData.postLoading()
            liveData.postData(func.invoke())
        } catch (error: Throwable) {
            liveData.postError(errorTransformer.invoke(error))
        }
    }
    return liveData
}

/**

 * Create a ResponseLiveData Instance posting Loading, Success or Error
 * With a default ErrorTransformer with returns the actual "func" block exception
 *
 * @param func Return the success value on the ResponseLiveData
 */
fun <T> makeAsyncOperation(func: suspend () -> T): ResponseLiveData<T> {
    return makeAsyncOperation(errorTransformer = { error -> error }, func = func)
}