package com.nurul.sportmania.Helpers;

import com.nurul.sportmania.Models.Emojies;
import com.nurul.sportmania.Models.Login;
import com.nurul.sportmania.Models.Details;
import com.nurul.sportmania.Models.Settings;
import com.nurul.sportmania.Models.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SESAM on 01.09.2018.
 */


public interface NewsInterface {
    @GET(Constants.NEWS_URL)
    Call<NewsResponse> getNews(@Query("limit") String limit);

    @GET(Constants.NEWS_URL)
    Call<NewsResponse> loadNews(@Query("id") String id,@Query("limit") String limit);

    @GET(Constants.NEWS_URL)
    Call<NewsResponse> loadCatNews(@Query("id") String id,@Query("limit") String limit,@Query("offset") String offset);

    @GET(Constants.NEWS_BY_CATEGORY_URL)
    Call<NewsResponse> getNewsByCategory(@Query("id") String id,@Query("limit") String limit,@Query("offset") String offset);

    @GET(Constants.DETAIL_URL)
    Call<Details> getDetail(@Query("id") String id);

    @GET(Constants.CATEGORY_URL)
    Call<CategoryResponse> getCategory();

    @GET(Constants.CHECK_USER_URL)
    Call<Login> checkUser(@Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(Constants.REGISTER_USER_URL)
    Call<Users> registerUser(@Query("id") String id,@Query("username") String username, @Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(Constants.REGISTER_USER_URL)
    Call<Users> updateUser(@Query("id") String id, @Query("username") String username, @Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(Constants.SETTINGS_URL)
    Call<Settings> getSettings();

    @GET(Constants.EMOJI_URL)
    Call<Emojies> getEmojies(@Query("nid") String nid, @Query("lol") String lol, @Query("loved") String loved, @Query("omg") String omg, @Query("funny") String funny, @Query("fail") String fail);

    @GET(Constants.NEWS_SEARCH_URL)
    Call<NewsResponse> getSearchNews(@Query("limit") String limit,@Query("query") String query);

    @GET(Constants.NEWS_SEARCH_URL)
    Call<NewsResponse> loadSearchNews(@Query("limit") String limit,@Query("id") String id,@Query("query") String query);

}

