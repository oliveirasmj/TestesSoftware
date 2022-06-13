package api.retrofit.Client;

import api.calls.ClientCalls;
import api.calls.VehicleCalls;
import api.mappings.Client;
import api.mappings.Vehicle;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import retrofit2.Response;

public class Vehicles {

    private static final VehicleCalls vehicleCalls = new RetrofitBuilder().getRetrofit().create(VehicleCalls.class);

    @SneakyThrows
    public static Response<Vehicle> getVehicleById(Integer vehicleId) {
        return vehicleCalls.getVehicleById(vehicleId).execute();
    }
}
