package api.retrofit.Client;

import api.calls.ClientCalls;
import api.mappings.Client;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import retrofit2.Response;

public class Clients {

    private static final ClientCalls clientCalls = new RetrofitBuilder().getRetrofit().create(ClientCalls.class);

    @SneakyThrows
    public static Response<Client> getClientById(Integer clientId) {
        return clientCalls.getClientById(clientId).execute();
    }
}
