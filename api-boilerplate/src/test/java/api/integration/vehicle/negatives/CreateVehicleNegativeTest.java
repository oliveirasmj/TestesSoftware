package api.integration.vehicle.negatives;

import api.mappings.Vehicle;
import api.mappings.generic.ErrorResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Errors.getErrorsResponse;
import static api.retrofit.Client.Vehicles.createVehicle;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVehicleNegativeTest {

    @Test(description = "create vehicle with invalid plate")
    public void createVehicleErrorPlateTest() {

        String brand = "VW";
        String model = "Golf 4";
        Integer year = 1999;
        String type = "Hatchback";
        String plate = "PI8860";
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
        assertBadRequest(response);
        ErrorResponse error = getErrorsResponse(response);

        assertThat("Timestamp should not be null", error.getTimestamp(), notNullValue());
        assertThat("Status should not be null", error.getStatus(), is(400));
        assertThat("Error should be 'Not found'", error.getError(), is("Bad Request"));
        assertThat("Message should be 'Food not found'", error.getMessage(), is("Invalid Plate"));
        assertThat("Path should be '/client", error.getPath(), is("/vehicle"));
    }
}
