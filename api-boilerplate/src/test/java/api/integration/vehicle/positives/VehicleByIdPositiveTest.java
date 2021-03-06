package api.integration.vehicle.positives;

import api.mappings.Vehicle;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Vehicle.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class VehicleByIdPositiveTest {

    @Test(description = "Get vehicle by Id")
    public void getVehicleByIdTest() {

        Integer id = 1;
        Integer client = 1;
        String brand = "Fiat";
        String model = "Punto";
        Integer year = 1999;
        String type = "Hatchback";
        String plate = "ZE-10-20";
        Boolean active = true;

        Response<Vehicle> response = getVehicleById(id);
        assertOk(response);
        assertThat("Body should not be null", response.body(), notNullValue());

        Vehicle vehicle = response.body();
        assertThat("id should be the same of the request", vehicle.getId(), is(id));
        assertThat("client should be the same of the request", vehicle.getClient(), is(client));
        assertThat("brand should be the same of the request", vehicle.getBrand(), is(brand));
        assertThat("model should be the same of the request", vehicle.getModel(), is(model));
        assertThat("year should be the same of the request", vehicle.getYear(), is(year));
        assertThat("type should be the same of the request", vehicle.getType(), is(type));
        assertThat("plate should be the same of the request", vehicle.getPlate(), is(plate));
        assertThat("active should be the same of the request", vehicle.getActive(), is(active));
    }
}
