package api.integration.vehicle.negatives;

import api.mappings.Vehicle;
import api.mappings.generic.ErrorResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Errors.getErrorsResponse;
import static api.retrofit.Client.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.assertNotFound;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class VehicleByIdNegativeTest {

    @Test(description = "Get vehicle by Id")
    public void getVehicleNonExistentIdTest() {
        Integer id = 0;

        Response<Vehicle> response = getVehicleById(id);
        assertNotFound(response);

        ErrorResponse error = getErrorsResponse(response);

        assertThat("Timestamp is not null", error.getTimestamp(), notNullValue());
        assertThat("Status should be 404", error.getStatus(), is(404));
        assertThat("Error should be 'Not Found'", error.getError(), is("Not Found"));
        assertThat("Message should be not found", error.getMessage(), is("Vehicle not found"));
        assertThat("Path should be /client/0", error.getPath(), is("/vehicle/0"));
    }
}
