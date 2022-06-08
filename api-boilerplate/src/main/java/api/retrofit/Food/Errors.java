package api.retrofit.Food;


import com.expiredfridge.integration.mappings.ErrorResponse;
import com.expiredfridge.integration.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Objects;

public class Errors {

    private static final Retrofit retrofit = new RetrofitBuilder().getRetrofit();

    @SneakyThrows
    public static ErrorResponse getErrorsResponse(Response response) {
        Converter<ResponseBody, ErrorResponse> errorConverter =
                retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        return errorConverter.convert(Objects.requireNonNull(response.errorBody()));
    }
}
