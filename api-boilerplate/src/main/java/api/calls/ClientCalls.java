package api.calls;

import api.mappings.Client;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ClientCalls {

    String CLIENT_ID = "client/{id}";
    String ID = "id";
    String CLIENT = "client";

    @GET(CLIENT_ID)
    Call<Client> getClientById(@Path(ID) Integer clientId);

    @GET(CLIENT)
    Call<List<Client>> getClient();

    @POST(CLIENT)
    Call<Client> createClient(@Body Client client);
}
