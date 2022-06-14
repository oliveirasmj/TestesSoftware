package api.integration.vehicle.positives;

import api.mappings.Vehicle;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Vehicles.createVehicle;
import static api.retrofit.Client.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVehiclePositiveTest {

    @Test(description = "create vehicle with success")
    public void createVehicleTest() {

        String brand = "VW";
        String model = "Golf 4";
        Integer year = 1999;
        String type = "Hatchback";
        String plate = "PI-88-60";
        Boolean active = true;

        Vehicle request = Vehicle.builder()
                .brand(brand)
                .model(model)
                .year(year)
                .type(type)
                .plate(plate)
                .active(active)
                .build();

        Response<Vehicle> response = createVehicle(request);
        assertCreated(response);
        assertThat("Body should not be null", response.body(), notNullValue());

        //Criar cliente com os dados enviados
        Vehicle vehicle = response.body();

        assertThat("id should not be nul", vehicle.getId(), notNullValue());
        Response<Vehicle> response2 = getVehicleById(vehicle.getId());
        assertOk(response2);
        assertThat("Body should not be null", response2.body(), notNullValue());

        //Criar cliente 2 com os dados da BD
        Vehicle vehicle2 = response.body();

        assertThat(vehicle2.getId(), is(vehicle.getId()));
        assertThat(vehicle2.getBrand(), is(vehicle.getBrand()));
        assertThat(vehicle2.getModel(), is(vehicle.getModel()));
        assertThat(vehicle2.getYear(), is(vehicle.getYear()));
        assertThat(vehicle2.getType(), is(vehicle.getType()));
        assertThat(vehicle2.getPlate(), is(vehicle.getPlate()));
        assertThat(vehicle2.getActive(), is(vehicle.getActive()));
    }
}
