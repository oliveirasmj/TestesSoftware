package api.integration.vehicle.positives;

import api.mappings.Vehicle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Vehicle.Vehicles.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVehiclePositiveTest {

    private Vehicle vehicle;

    @Test(description = "Create Vehicle with success")
    public void CreateVehiclePositiveTest() {
        String brand = "VW";
        String model = "Golf 8";
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

        //Create vehicle
        Response<Vehicle> response = createVehicle(request);
        assertCreated(response);
        vehicle = response.body();

        //Get vehicle by id
        Response<Vehicle> idVehicle = getVehicleById(vehicle.getId());
        assertOk(idVehicle);
        vehicle = idVehicle.body();


        assertThat("id should be the same of the request", vehicle.getId(), notNullValue());
        assertThat("brand should be the same of the request", vehicle.getBrand(), is(brand));
        assertThat("model should be the same of the request", vehicle.getModel(), is(model));
        assertThat("year should be the same of the request", vehicle.getYear(), is(year));
        assertThat("type should be the same of the request", vehicle.getType(), is(type));
        assertThat("plate should be the same of the request", vehicle.getPlate(), is(plate));
        assertThat("active should be the same of the request", vehicle.getActive(), is(active));
    }

    @AfterMethod()
    public void cleanUp(){
        deleteVehicle(vehicle.getId());
    }
}
