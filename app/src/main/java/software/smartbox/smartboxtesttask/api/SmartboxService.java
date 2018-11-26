package software.smartbox.smartboxtesttask.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import software.smartbox.smartboxtesttask.data.Location;

public interface SmartboxService {
    @GET("/tt/TT.json")
    Call<List<Location>> downloadFileWithFixedUrl();
}
