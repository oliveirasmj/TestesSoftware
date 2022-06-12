package api.retrofit.Client;

import api.mappings.generic.ErrorResponse;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

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
