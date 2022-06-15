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
import static org.hamcrest.Matchers.*;


public class DeleteClientPositiveTest {

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
        assertThat("Body should not be null", response.body(), notNullValue());

        //Criar cliente com os dados enviados
        client = response.body();
    }

    @Test(description = "Delete client by Id")
    public void deleteClientTest() {
        assertThat("id should not be nul", client.getId(), notNullValue());
        Response<Client> idClient = getClientById(client.getId());
        assertOk(idClient);
        assertThat("Body should not be null", idClient.body(), notNullValue());

        //Eliminar cliente
        Response<Client> responseDelete = deleteClient(client.getId());
        assertThat("Body be null", responseDelete.body(), nullValue()); //tem de ser nulo
    }

    @AfterMethod()
    public void cleanUp(){
        deleteClient(client.getId());
    }
}
