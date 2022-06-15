package api.integration.client.positives;

import api.mappings.Client;
import api.mappings.Vehicle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static api.retrofit.Client.Clients.getClientById;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClientByIdPositiveTest {

    @Test(description = "Get client by Id")
    public void getClientByIdTest() {

        Integer id = 1;
        String firstName = "Afonso";
        String lastName = "Silva";
        String address = "Rua da Esquina";
        String postalCode = "3810-700";
        String city = "Aveiro";
        String country = "Portugal";
        Integer phoneNumber = 966111222;
        Integer nif = 212212212;
        LocalDate birthDate = LocalDate.parse("1983-01-01");
        LocalDate clientDate = LocalDate.parse("2001-05-05");
        List<Vehicle> vehicles = new ArrayList<>();

        //Veiculo 1
        Integer idVeiculo1 = 1;
        Integer client1 = 1;
        String brand1 = "Fiat";
        String model1 = "Punto";
        Integer year1 = 1999;
        String type1 = "Hatchback";
        String plate1 = "ZE-10-20";
        Boolean active1 = true;
        Vehicle v1 = new Vehicle(idVeiculo1, client1, brand1, model1, year1, type1, plate1, active1);

        //Veiculo 2
        Integer idVeiculo2 = 2;
        Integer client2 = 1;
        String brand2 = "Opel";
        String model2 = "Corsa";
        Integer year2 = 2002;
        String type2 = "Hatchback";
        String plate2 = "34-CB-45";
        Boolean active2 = true;
        Vehicle v2 = new Vehicle(idVeiculo2, client2, brand2, model2, year2, type2, plate2, active2);

        //Adicionar veiculos à lista
        Collections.addAll(vehicles, v1, v2);

        Response<Client> response = getClientById(id);
        assertOk(response);
        assertThat("Body should not be null", response.body(), notNullValue());

        Client client = response.body();
        assertThat("id should be the same of the request", client.getId(), is(id));
        assertThat("firstname is not the expected", client.getFirstName(), is(firstName));
        assertThat("lastname is not the expected",client.getLastName(), is(lastName));
        assertThat("address is not the expected", client.getAddress(), is(address));
        assertThat("postalCode is not the expected", client.getPostalCode(), is(postalCode));
        assertThat("city is not the expected", client.getCity(), is(city));
        assertThat("country is not the expected", client.getCountry(), is(country));
        assertThat("phone number  is not the expected", client.getPhoneNumber(), is(phoneNumber));
        assertThat("nif is not the expected", client.getNif(), is(nif));
        assertThat("birthdate is not the expected", client.getBirthDate(), is(birthDate));
        assertThat("clientDate is not the expected", client.getClientDate(), is(clientDate));
        assertThat("vehicles is not the expected", client.getVehicles(), is(vehicles));
    }
}
