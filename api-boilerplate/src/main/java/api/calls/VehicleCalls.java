package api.calls;

import api.mappings.Client;
import api.mappings.Vehicle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface VehicleCalls {

    String VEHICLE_ID = "vehicle/{id}";
    String ID = "id";
    String VEHICLE = "vehicle";

    @GET(VEHICLE_ID)
    Call<Vehicle> getVehicleById(@Path(ID) Integer vehicleId);

    @GET(VEHICLE)
    Call<List<Vehicle>> getVehicle();

    @POST(VEHICLE)
    Call<Vehicle> createVehicle(@Body Vehicle vehicle);
}
