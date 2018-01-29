package com.app.android.data.source.remote.core

import com.app.android.data.model.BusEvent
import com.app.android.utils.BaseRxCallAdapterWrapper
import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type
import javax.net.ssl.HttpsURLConnection

/**
 *
 * @author at-hoavo.
 */
class RxCallAdapterWrapper<R>(private val type: Type, private val retrofit: Retrofit, private val wrapped: CallAdapter<R, *>?) : BaseRxCallAdapterWrapper<R>(type, retrofit, wrapped) {

    override fun convertRetrofitExceptionToCustomException(throwable: Throwable, retrofit: Retrofit): Throwable {

        if (throwable is HttpException) {
            val converter: Converter<ResponseBody, ApiException> = retrofit.responseBodyConverter(ApiException::class.java, arrayOfNulls<Annotation>(0))
            val response: Response<*>? = throwable.response()
            when (throwable.response().code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> RxBus.publish(BusEvent())
            //Todo: Handle for another status code
            }
            response?.errorBody()?.let {
                return converter.convert(it)
            }
        }

        return throwable
    }

    override fun createExceptionForSuccessResponse(response: Any?): Throwable? = super.createExceptionForSuccessResponse(response)
}
