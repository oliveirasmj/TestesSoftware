package api.integration.client.negatives;

import api.mappings.Client;
import api.mappings.generic.ErrorResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.Client.Clients.getClientById;
import static api.retrofit.Client.Errors.getErrorsResponse;
import static api.validators.ResponseValidator.assertNotFound;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClientByIdNegativeTest {

    @Test(description = "Get client by not existent ID")
    public void getClientNonExistentIdTest() {
        Integer id = 0;

        Response<Client> response = getClientById(id);
        assertNotFound(response);

        ErrorResponse error = getErrorsResponse(response);

        assertThat("Timestamp is not null", error.getTimestamp(), notNullValue());
        assertThat("Status should be 404", error.getStatus(), is(404));
        assertThat("Error should be 'Not Found'", error.getError(), is("Not Found"));
        assertThat("Message should be not found", error.getMessage(), is("Client not found"));
        assertThat("Path should be /client/0", error.getPath(), is("/client/0"));
    }
}
