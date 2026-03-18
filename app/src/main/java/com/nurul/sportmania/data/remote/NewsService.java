package com.nurul.sportmania.data.remote;

import com.nurul.sportmania.core.AppConstants;
import com.nurul.sportmania.data.model.Emojies;
import com.nurul.sportmania.data.model.Details;
import com.nurul.sportmania.data.model.Login;
import com.nurul.sportmania.data.model.Settings;
import com.nurul.sportmania.data.model.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> getNews(@Query("limit") String limit);

    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> loadNews(@Query("id") String id,@Query("limit") String limit);

    @GET(AppConstants.NEWS_URL)
    Call<NewsResponse> loadCatNews(@Query("id") String id,@Query("limit") String limit,@Query("offset") String offset);

    @GET(AppConstants.NEWS_BY_CATEGORY_URL)
    Call<NewsResponse> getNewsByCategory(@Query("id") String id,@Query("limit") String limit,@Query("offset") String offset);

    @GET(AppConstants.DETAIL_URL)
    Call<Details> getDetail(@Query("id") String id);

    @GET(AppConstants.CATEGORY_URL)
    Call<CategoryResponse> getCategory();

    @GET(AppConstants.CHECK_USER_URL)
    Call<Login> checkUser(@Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(AppConstants.REGISTER_USER_URL)
    Call<Users> registerUser(@Query("id") String id,@Query("username") String username, @Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(AppConstants.REGISTER_USER_URL)
    Call<Users> updateUser(@Query("id") String id, @Query("username") String username, @Query("email") String email, @Query("password") String password, @Query("token") String token);

    @GET(AppConstants.SETTINGS_URL)
    Call<Settings> getSettings();

    @GET(AppConstants.EMOJI_URL)
    Call<Emojies> getEmojies(@Query("nid") String nid, @Query("lol") String lol, @Query("loved") String loved, @Query("omg") String omg, @Query("funny") String funny, @Query("fail") String fail);

    @GET(AppConstants.NEWS_SEARCH_URL)
    Call<NewsResponse> getSearchNews(@Query("limit") String limit,@Query("query") String query);

    @GET(AppConstants.NEWS_SEARCH_URL)
    Call<NewsResponse> loadSearchNews(@Query("limit") String limit,@Query("id") String id,@Query("query") String query);

}
