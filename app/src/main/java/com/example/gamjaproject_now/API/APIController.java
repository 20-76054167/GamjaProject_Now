package com.example.gamjaproject_now.API;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController{
    private static String baseURL = "http://gguip7554.cafe24.com/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static APIService function = retrofit.create(APIService.class);
    //    private static Call<API[]> testCall = function.getData("novel",5,3);
    public static Call<Content[]> getTestCall(String tableName, int page, int pagingUnit) {
        return function.getPaginatedContents(tableName,page,pagingUnit);
    }
    public static Call<Count[]> getCountCall(String tableName) {
        return function.getContentCount(tableName);
    }
}
