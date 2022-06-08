package api.retrofit.Food;

import com.expiredfridge.integration.calls.FoodCalls;
import com.expiredfridge.integration.mappings.Food;
import com.expiredfridge.integration.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.util.List;

public class Foods {

    private static final FoodCalls foodCalls = new RetrofitBuilder().getRetrofit().create(FoodCalls.class);

    @SneakyThrows
    public static Response<Food> getFoodById(Integer foodId) {
        return foodCalls.getFoodById(foodId).execute();
    }

    @SneakyThrows
    public static Response<List<Food>> getFood() {
        return foodCalls.getFood().execute();
    }

    @SneakyThrows
    public static Response<Food> createFood(Food food) {
        return foodCalls.createFood(food).execute();
    }

    @SneakyThrows
    public static Response<Food> updateFood(Food food) {
        return foodCalls.updateFood(food).execute();
    }

    @SneakyThrows
    public static Response<ResponseBody> deleteFoodById(Integer foodId) {
        return foodCalls.deleteFoodById(foodId).execute();
    }
}
