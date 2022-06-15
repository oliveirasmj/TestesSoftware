package api.calls;

import api.mappings.Client;
import retrofit2.Call;
import retrofit2.http.*;

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

    @DELETE(CLIENT_ID)
    Call<Client> deleteClient(@Path(ID) Integer clientId);

    @PUT(CLIENT_ID)
    Call<Client> updateClient(@Path(ID) Integer clientId, @Body Client client);
}
