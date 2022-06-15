package api.integration.client.positives;

import api.mappings.Client;
import api.mappings.Vehicle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static api.retrofit.Client.Clients.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.AssertJUnit.assertEquals;


public class UpdateClientPositiveTest {

    private Client client;
    private Client client2;

    @Test(description = "Update client by Id")
    public void updateClientTest() {

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
        assertThat("Body should not be null", response.body(), notNullValue());

        //Criar cliente com os dados enviados
        client = response.body();

        assertThat("id should not be nul", client.getId(), notNullValue());
        Response<Client> response2 = getClientById(client.getId());
        assertOk(response2);
        assertThat("Body should not be null", response2.body(), notNullValue());


        //Fazer o update
        String firstName2 = "Mike";
        Client request2 = Client.builder()
                .firstName(firstName2) //Vamos usar um nome diferente
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

        Response<Client> response3 = updateClient(response.body().getId(), request2);
        assertThat("Body should not be null", response3.body(), notNullValue());

        //Comparar Firstnames
        client2 = response3.body();
        assertEquals(request2.getFirstName(), firstName2);
    }

    @AfterMethod()
    public void cleanUp(){
        deleteClient(client.getId());
    }
}