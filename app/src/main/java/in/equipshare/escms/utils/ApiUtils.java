package in.equipshare.escms.utils;

import in.equipshare.escms.rest.APIService;
import in.equipshare.escms.rest.RetrofitClient;

public class ApiUtils {


    private ApiUtils() {}

    public static final String BASE_URL = "http://c96ba8fd.ngrok.io/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
