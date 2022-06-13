package api.generic.client;

import api.mappings.Client;
import api.mappings.Vehicle;
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
        assertThat(client.getId(), is(id));
        assertThat(client.getFirstName(), is(firstName));
        assertThat(client.getLastName(), is(lastName));
        assertThat(client.getAddress(), is(address));
        assertThat(client.getPostalCode(), is(postalCode));
        assertThat(client.getCity(), is(city));
        assertThat(client.getCountry(), is(country));
        assertThat(client.getPhoneNumber(), is(phoneNumber));
        assertThat(client.getNif(), is(nif));
        assertThat(client.getBirthDate(), is(birthDate));
        assertThat(client.getClientDate(), is(clientDate));
        assertThat(client.getVehicles(), is(vehicles));
    }
}
