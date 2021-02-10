import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myanmarpeople.trustus.model.AppException
import com.myanmarpeople.trustus.model.NetWorkResourceModel
import com.myanmarpeople.trustus.util.ExceptionUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.asLiveData(): LiveData<NetWorkResourceModel<T>> {
    val result = MutableLiveData<NetWorkResourceModel<T>>()
    result.postValue(NetWorkResourceModel.loading())
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            result.postValue(NetWorkResourceModel.error(AppException(t)))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                call.request()
                result.postValue(NetWorkResourceModel.Success(response.body()))
            } else {
                // FirebaseCrashReporter.log(FirebaseLogModel(call.request().body().bodyToString(), url = request().url().toString(), responseBody = response.errorBody()))
                onFailure(call, Throwable(ExceptionUtil.getErrorMessageFromResponse(response).message))
            }


        }

    })
    return result

}