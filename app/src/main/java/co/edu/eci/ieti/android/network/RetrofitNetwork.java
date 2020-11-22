package co.edu.eci.ieti.android.network;

import co.edu.eci.ieti.android.network.service.AuthService;
import co.edu.eci.ieti.android.network.service.TaskService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Santiago Carrillo
 * 4/23/19.
 */
public class RetrofitNetwork
{

    private AuthService authService;

    private static String BASE_URL = "http:/10.0.2.2:8080/";

    private TaskService taskService;


    public RetrofitNetwork()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( BASE_URL ) //localhost for emulator
            .addConverterFactory( GsonConverterFactory.create() ).build();

        authService = retrofit.create( AuthService.class );
    }

    public RetrofitNetwork(String token){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor((chain)->{
            Request original = chain.request();
            Request request = original.newBuilder().header( "Accept", "application/json" ).header( "Authorization",
                    "Bearer "
                            + token ).method(original.method(), original.body()).build();
            return chain.proceed( request );
        });
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl( BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).client(
                        httpClient.build() ).build();
        taskService = retrofit.create(TaskService.class);
    }

    public AuthService getAuthService()
    {
        return authService;
    }
    public  TaskService getTaskService(){
        return taskService;
    }
}
