package api.generic.vehicle;

import api.mappings.Vehicle;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Vehicles.getVehicleById;
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
        assertThat(vehicle.getId(), is(id));
        assertThat(vehicle.getClient(), is(client));
        assertThat(vehicle.getBrand(), is(brand));
        assertThat(vehicle.getModel(), is(model));
        assertThat(vehicle.getYear(), is(year));
        assertThat(vehicle.getType(), is(type));
        assertThat(vehicle.getPlate(), is(plate));
        assertThat(vehicle.getActive(), is(active));
    }
}
