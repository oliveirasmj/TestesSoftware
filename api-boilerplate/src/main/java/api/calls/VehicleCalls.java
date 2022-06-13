package api.calls;

import api.mappings.Client;
import api.mappings.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleCalls {

    String VEHICLE_ID = "vehicle/{id}";
    String ID = "id";

    @GET(VEHICLE_ID)
    Call<Vehicle> getVehicleById(@Path(ID) Integer vehicleId);

}
