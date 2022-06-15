package api.integration.client.positives;

import api.mappings.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;

import static api.retrofit.Client.Clients.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateClientPositiveTest {

    private Client client;
    private Client client2;

    @BeforeMethod
    public void setupClient() {

        String firstName = "Miguel";
        String lastName = "Oliveira";
        String address = "Rua da Esquina";
        String postalCode = "3810-700";
        String city = "Aveiro";
        String country = "Portugal";
        Integer phoneNumber = 966111222;
        Integer nif = 212212212;
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
        client = response.body();

        assertThat("id should not be nul", client.getId(), notNullValue());
        Response<Client> response2 = getClientById(client.getId());
        assertOk(response2);
        assertThat("Body should not be null", response2.body(), notNullValue());

        //Criar cliente 2
        client2 = response.body();
    }

    @Test(description = "create client with success")
    public void createClientTest() {
        assertThat(client2.getId(), is(client.getId()));
        assertThat(client2.getFirstName(), is(client.getFirstName()));
        assertThat(client2.getLastName(), is(client.getLastName()));
        assertThat(client2.getAddress(), is(client.getAddress()));
        assertThat(client2.getPostalCode(), is(client.getPostalCode()));
        assertThat(client2.getCity(), is(client.getCity()));
        assertThat(client2.getCountry(), is(client.getCountry()));
        assertThat(client2.getPhoneNumber(), is(client.getPhoneNumber()));
        assertThat(client2.getNif(), is(client.getNif()));
        assertThat(client2.getBirthDate(), is(client.getBirthDate()));
        assertThat(client2.getClientDate(), is(client.getClientDate()));
    }

    @AfterMethod()
    public void cleanUp(){
        deleteClient(client.getId());
        deleteClient(client2.getId());
    }

}
