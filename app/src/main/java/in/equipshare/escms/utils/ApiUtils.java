package in.equipshare.escms.utils;

import in.equipshare.escms.rest.APIService;
import in.equipshare.escms.rest.RetrofitClient;

public class ApiUtils {


    private ApiUtils() {}

    public static final String BASE_URL = "http://4014e30b.ngrok.io/signup2/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
