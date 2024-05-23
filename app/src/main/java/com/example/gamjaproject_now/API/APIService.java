package com.example.gamjaproject_now.API;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("application/api/getContentById")
    Call<Content[]> getContentById(
            @Query("tableName") String tableName,
            @Query("id") int id
    );

    @GET("application/api/getContentByIdCount")
    Call<Count[]> getContentByIdCount(
            @Query("tableName") String tableName,
            @Query("id") int id
    );

    @GET("application/api/getContent")
    Call<Content[]> getContent(
            @Query("tableName") String tableName
    );

    @GET("application/api/getContentCount")
    Call<Count[]> getContentCount(
            @Query("tableName") String tableName
    );

    @GET("application/api/getAllGenres")
    Call<Genre[]> getAllGenres(
    );

    @GET("application/api/getContentByGenreId")
    Call<Content[]> getContentByGenreId(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );

    @GET("application/api/getContentByGenreIdCount")
    Call<Count[]> getContentByGenreIdCount(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );

    @GET("application/api/getContentGenreById")
    Call<ContentGenre[]> getContentGenreById(
            @Query("tableName") String tableName,
            @Query("id") int id
    );

    @GET("application/api/getContentGenreByIdCount")
    Call<Count[]> getContentGenreByIdCount(
            @Query("tableName") String tableName,
            @Query("id") int id
    );

    @GET("application/api/getGenreNamesByContentId")
    Call<Genre[]> getGenreNamesByContentId(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );

    @GET("application/api/getGenreNamesByContentIdCount")
    Call<Count[]> getGenreNamesByContentIdCount(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );

    @GET("application/api/getContentsByGenre")
    Call<Content[]> getContentsByGenre(
            @Query("tableName") String tableName
    );
    @GET("application/api/getContentsByGenreCount")
    Call<Count[]> getContentsByGenreCount(
            @Query("tableName") String tableName
    );

    @GET("application/api/getContentsByGenreId")
    Call<Content[]> getContentsByGenreId(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );
    @GET("application/api/getContentsByGenreIdCount")
    Call<Count[]> getContentsByGenreIdCount(
            @Query("tableName") String tableName,
            @Query("genre_id") int genre_id
    );



    @GET("application/api/getPaginatedContents")
    Call<Content[]> getPaginatedContents(
            @Query("tableName") String tableName,
            @Query("page") int page,
            @Query("pagingUnit") int pagingUnit
    );

    @GET("application/api/getPaginatedContentsCount")
    Call<Count[]> getPaginatedContentsCount(
            @Query("tableName") String tableName,
            @Query("page") int page,
            @Query("pagingUnit") int pagingUnit
    );

}
