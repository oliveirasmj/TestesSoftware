package api.calls;

import com.expiredfridge.integration.mappings.Food;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface FoodCalls {

    String FOOD_ID = "food/{id}";
    String ID = "id";
    String FOOD = "food";


    @GET(FOOD_ID)
    Call<Food> getFoodById(@Path(ID) Integer foodId);

    @GET(FOOD)
    Call<List<Food>> getFood();

    @POST(FOOD)
    Call<Food> createFood(@Body Food food);

    @PUT(FOOD)
    Call<Food> updateFood(@Body Food food);

    @DELETE(FOOD_ID)
    Call<ResponseBody> deleteFoodById(@Path(ID) Integer foodId);

}
