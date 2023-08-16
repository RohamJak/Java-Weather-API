// import org.json.JSONArray;
// import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Call {
    public static void main(String[] args) {
        String apiKey = "d908669f484525330f748c49c5a11dd7";
        String unit = "imperial";
        String lang = "en";
        String cityName = "Austin";

        // Create the URL for the API call
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&units=" + unit + "&lang=" + lang + "&appid=" + apiKey;
        System.out.println(url);

        // Create an HTTP client
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // Send the HTTP request and receive the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {
                //JSONArray JSONArray = new JSONArray(response.body());
                JSONObject jsonObject = new JSONObject(response.body());
                //Get the first JSON Object from the array
                // JSONObject jsonObject = JSONArray.getJSONObject(0);
                
                // Print the response body
                System.out.println(response.body());
                
                JSONObject mainObject = new JSONObject(jsonObject, "main");
                // String temp = mainObject.getString("temp");
                System.out.println(mainObject);
                double temperature = mainObject.getJSONObject("main").getDouble("temp");
                System.out.println(temperature);
            } else {
                System.out.println("Request failed with status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
