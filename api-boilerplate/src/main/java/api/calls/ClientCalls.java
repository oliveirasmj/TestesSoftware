package api.calls;

import api.mappings.Client;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClientCalls {

    String CLIENT_ID = "client/{id}";
    String ID = "id";

    @GET(CLIENT_ID)
    Call<Client> getClientById(@Path(ID) Integer clientId);

}
