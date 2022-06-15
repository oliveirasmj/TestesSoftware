package api.integration.client.negatives;

import api.mappings.Client;
import api.mappings.generic.ErrorResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;

import static api.retrofit.Client.Clients.createClient;
import static api.retrofit.Client.Clients.updateClient;
import static api.retrofit.Client.Errors.getErrorsResponse;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateClientNegativeTest {

    @Test(description = "create client with invalid nif")
    public void createClientErrorNifTest() {

        String firstName = "Miguel";
        String lastName = "Oliveira";
        String address = "Rua da Esquina";
        String postalCode = "3810-700";
        String city = "Aveiro";
        String country = "Portugal";
        Integer phoneNumber = 912746327;
        Integer nif = 123456789;
        LocalDate birthDate = LocalDate.parse("1983-01-01");
        LocalDate clientDate = LocalDate.parse("2001-05-05");

        Client request = Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .postalCode(postalCode)
                .city(city)
                .country(country)
                .phoneNumber(phoneNumber)
                .nif(nif)
                .birthDate(birthDate)
                .clientDate(clientDate)
                .build();

        Response<Client> response = createClient(request);
        assertCreated(response);

        //Fazer o update
        request.setPhoneNumber(9);
        Response<Client> response2 = updateClient(request);
        ErrorResponse error = getErrorsResponse(response2);

        assertThat("Timestamp should not be null", error.getTimestamp(), notNullValue());
        assertThat("Status should not be null", error.getStatus(), is(400));
        assertThat("Error should be 'Not found'", error.getError(), is("Bad Request"));
        assertThat("Message should be 'Food not found'", error.getMessage(), is("Invalid Phone Number"));
        assertThat("Path should be '/client", error.getPath(), is("/client/"+ response.body().getId()));

    }
}
