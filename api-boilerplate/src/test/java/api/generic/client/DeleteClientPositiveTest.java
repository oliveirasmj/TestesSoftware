package api.generic.client;

import api.mappings.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;

import static api.retrofit.Client.Clients.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class DeleteClientPositiveTest {

    @Test(description = "Delete client by Id")
    public void deleteClientTest() {

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
        Client client = response.body();

        assertThat("id should not be nul", client.getId(), notNullValue());
        Response<Client> response2 = getClientById(client.getId());
        assertOk(response2);
        assertThat("Body should not be null", response2.body(), notNullValue());


        //Eliminar cliente
        //System.out.println(response.body().getId());
        Response<Client> response3 = deleteClient(response.body().getId());
        //assertOk(response3);
        assertThat("Body be null", response3.body(), nullValue()); //tem de ser nulo
    }
}
