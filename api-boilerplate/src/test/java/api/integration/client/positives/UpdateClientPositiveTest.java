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


public class UpdateClientPositiveTest {

    private Client client;

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
    }

    @Test(description = "update client with success")
    public void updateFoodNameTest() {

        String name = "Jorge";

        Client request = Client.builder()
                .id(client.getId())
                .firstName(name)
                .build();

        Response<Client> response = updateClient(request);
        assertOk(response);
    }


    @Test(description = "update food with success")
    public void updateFoodTypeTest() {

        Integer nif = 333333333;

        Client request = Client.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .nif(nif)
                .build();

        Response<Client> response = updateClient(request);
        assertOk(response);
    }

    @AfterMethod
    public void cleanUp () {
        deleteClient(client.getId());
    }
}
