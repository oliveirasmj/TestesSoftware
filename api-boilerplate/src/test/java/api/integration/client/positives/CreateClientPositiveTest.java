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

    @Test(description = "Create Client with success")
    public void CreateClientPositiveTest() {

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

        //Create client
        Response<Client> response = createClient(request);
        assertCreated(response);
        client = response.body();

        //Get client by id
        Response<Client> idClient = getClientById(client.getId());
        assertOk(idClient);
        client = idClient.body();


        assertThat("id should be the same of the request", client.getId(), notNullValue());
        assertThat("firstname is not the expected", client.getFirstName(), is(firstName));
        assertThat("lastname is not the expected", client.getLastName(), is(lastName));
        assertThat("address is not the expected", client.getAddress(), is(address));
        assertThat("postalCode is not the expected", client.getPostalCode(), is(postalCode));
        assertThat("city is not the expected", client.getCity(), is(city));
        assertThat("country is not the expected", client.getCountry(), is(country));
        assertThat("phone number  is not the expected", client.getPhoneNumber(), is(phoneNumber));
        assertThat("nif is not the expected", client.getNif(), is(nif));
        assertThat("birthdate is not the expected", client.getBirthDate(), is(birthDate));
        assertThat("clientDate is not the expected", client.getClientDate(), is(clientDate));
    }

    @AfterMethod()
    public void cleanUp(){
        deleteClient(client.getId());
    }

}
