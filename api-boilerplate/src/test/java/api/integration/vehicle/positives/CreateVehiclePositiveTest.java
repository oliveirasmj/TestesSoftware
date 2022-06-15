package api.integration.vehicle.positives;

import api.mappings.Vehicle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Clients.deleteClient;
import static api.retrofit.Vehicle.Vehicles.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVehiclePositiveTest {

    private Vehicle vehicle;
    private Vehicle vehicle2;

    @BeforeMethod
    public void setupClient() {
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

        Response<Vehicle> response = createVehicle(request);
        assertCreated(response);
        assertThat("Body should not be null", response.body(), notNullValue());

        //Criar carro com os dados enviados
        vehicle = response.body();

        assertThat("id should not be nul", vehicle.getId(), notNullValue());
        Response<Vehicle> response2 = getVehicleById(vehicle.getId());
        assertOk(response2);
        assertThat("Body should not be null", response2.body(), notNullValue());

        //Criar carro 2 com os dados da BD
        vehicle2 = response.body();
    }

    @Test(description = "create vehicle with success")
    public void createVehicleTest() {
        assertThat(vehicle2.getId(), is(vehicle.getId()));
        assertThat(vehicle2.getBrand(), is(vehicle.getBrand()));
        assertThat(vehicle2.getModel(), is(vehicle.getModel()));
        assertThat(vehicle2.getYear(), is(vehicle.getYear()));
        assertThat(vehicle2.getType(), is(vehicle.getType()));
        assertThat(vehicle2.getPlate(), is(vehicle.getPlate()));
        assertThat(vehicle2.getActive(), is(vehicle.getActive()));
    }

    @AfterMethod()
    public void cleanUp(){
        deleteVehicle(vehicle.getId());
        deleteVehicle(vehicle2.getId());
    }
}
