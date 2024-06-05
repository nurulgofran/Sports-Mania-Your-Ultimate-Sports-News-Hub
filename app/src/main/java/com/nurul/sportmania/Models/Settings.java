package com.nurul.sportmania.Models;

/**
 * Created by SESAM on 06.09.2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("settings")
    @Expose
    private List<Setting> settings = null;

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public class Setting {

        @SerializedName("app_name")
        @Expose
        private String appName;
        @SerializedName("app_email")
        @Expose
        private String appEmail;
        @SerializedName("app_website")
        @Expose
        private String appWebsite;
        @SerializedName("app_description")
        @Expose
        private String appDescription;
        @SerializedName("app_terms")
        @Expose
        private String appTerms;
        @SerializedName("app_privacy")
        @Expose
        private String appPrivacy;
        @SerializedName("app_token")
        @Expose
        private String appToken;
        @SerializedName("admob_status")
        @Expose
        private String admobStatus;
        @SerializedName("admob_banner_key")
        @Expose
        private String admobBannerKey;
        @SerializedName("admob_interstial_key")
        @Expose
        private String admobInterstialKey;
        @SerializedName("title_home")
        @Expose
        private String titleHome;
        @SerializedName("title_category")
        @Expose
        private String titleCategory;
        @SerializedName("title_video")
        @Expose
        private String titleVideo;
        @SerializedName("title_favorite")
        @Expose
        private String titleFavorite;
        @SerializedName("title_profile")
        @Expose
        private String titleProfile;
        @SerializedName("title_settings")
        @Expose
        private String titleSettings;
        @SerializedName("title_login")
        @Expose
        private String titleLogin;
        @SerializedName("title_register")
        @Expose
        private String titleRegister;
        @SerializedName("title_about")
        @Expose
        private String titleAbout;
        @SerializedName("action_settings")
        @Expose
        private String actionSettings;
        @SerializedName("title_activiy_news_detail")
        @Expose
        private String titleActiviyNewsDetail;
        @SerializedName("search")
        @Expose
        private String search;
        @SerializedName("search_here")
        @Expose
        private String searchHere;
        @SerializedName("timeout_error")
        @Expose
        private String timeoutError;
        @SerializedName("hint_email")
        @Expose
        private String hintEmail;
        @SerializedName("settings_saved")
        @Expose
        private String settingsSaved;
        @SerializedName("succesfull")
        @Expose
        private String succesfull;
        @SerializedName("error")
        @Expose
        private String error;
        @SerializedName("hint_password")
        @Expose
        private String hintPassword;
        @SerializedName("hint_name")
        @Expose
        private String hintName;
        @SerializedName("you_hava_rated")
        @Expose
        private String youHavaRated;
        @SerializedName("btn_login")
        @Expose
        private String btnLogin;
        @SerializedName("btn_register")
        @Expose
        private String btnRegister;
        @SerializedName("btn_link_to_register")
        @Expose
        private String btnLinkToRegister;
        @SerializedName("btn_link_to_login")
        @Expose
        private String btnLinkToLogin;
        @SerializedName("welcome")
        @Expose
        private String welcome;
        @SerializedName("btn_logout")
        @Expose
        private String btnLogout;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("not_login_favorite_message")
        @Expose
        private String notLoginFavoriteMessage;
        @SerializedName("please_fill_the_fields")
        @Expose
        private String pleaseFillTheFields;
        @SerializedName("please_fill_credentials")
        @Expose
        private String pleaseFillCredentials;
        @SerializedName("you_must_sign_to_favorites")
        @Expose
        private String youMustSignToFavorites;
        @SerializedName("valid_email_adres")
        @Expose
        private String validEmailAdres;
        @SerializedName("latest_news")
        @Expose
        private String latestNews;
        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("update_profile")
        @Expose
        private String updateProfile;
        @SerializedName("terms_conditions")
        @Expose
        private String termsConditions;
        @SerializedName("title_terms")
        @Expose
        private String titleTerms;
        @SerializedName("saving")
        @Expose
        private String saving;
        @SerializedName("you_must_accept_terms")
        @Expose
        private String youMustAcceptTerms;
        @SerializedName("username_cannot_be_less")
        @Expose
        private String usernameCannotBeLess;
        @SerializedName("query_cannot_be_less")
        @Expose
        private String queryCannotBeLess;
        @SerializedName("password_cannot_be_less")
        @Expose
        private String passwordCannotBeLess;
        @SerializedName("logging_in")
        @Expose
        private String loggingIn;
        @SerializedName("updating")
        @Expose
        private String updating;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("website_")
        @Expose
        private String website;
        @SerializedName("emai")
        @Expose
        private String emai;
        @SerializedName("name_about")
        @Expose
        private String nameAbout;
        @SerializedName("categori_title")
        @Expose
        private String categoriTitle;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("internet_error")
        @Expose
        private String internetError;
        @SerializedName("app_logo")
        @Expose
        private String appLogo;
        @SerializedName("app_name_text")
        @Expose
        private String app_name_text;

        @SerializedName("btn_update")
        @Expose
        private String btn_update;

        @SerializedName("enable_dark_mode")
        @Expose
        private String enable_dark_mode;

        @SerializedName("night_mode")
        @Expose
        private String night_mode;

        @SerializedName("enable_cardview_layout")
        @Expose
        private String enable_cardview_layout;

        @SerializedName("cardview_layout")
        @Expose
        private String cardview_layout;

        @SerializedName("enable_saving_data")
        @Expose
        private String enable_saving_data;

        @SerializedName("saving_data")
        @Expose
        private String saving_data;

        public String getEnable_dark_mode() {
            return enable_dark_mode;
        }

        public void setEnable_dark_mode(String enable_dark_mode) {
            this.enable_dark_mode = enable_dark_mode;
        }

        public String getNight_mode() {
            return night_mode;
        }

        public void setNight_mode(String night_mode) {
            this.night_mode = night_mode;
        }

        public String getEnable_cardview_layout() {
            return enable_cardview_layout;
        }

        public void setEnable_cardview_layout(String enable_cardview_layout) {
            this.enable_cardview_layout = enable_cardview_layout;
        }

        public String getCardview_layout() {
            return cardview_layout;
        }

        public void setCardview_layout(String cardview_layout) {
            this.cardview_layout = cardview_layout;
        }

        public String getEnable_saving_data() {
            return enable_saving_data;
        }

        public void setEnable_saving_data(String enable_saving_data) {
            this.enable_saving_data = enable_saving_data;
        }

        public String getSaving_data() {
            return saving_data;
        }

        public void setSaving_data(String saving_data) {
            this.saving_data = saving_data;
        }

        public String getBtn_update() {
            return btn_update;
        }

        public void setBtn_update(String btn_update) {
            this.btn_update = btn_update;
        }

        public String getApp_name_text() {
            return app_name_text;
        }

        public void setApp_name_text(String app_name_text) {
            this.app_name_text = app_name_text;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppEmail() {
            return appEmail;
        }

        public void setAppEmail(String appEmail) {
            this.appEmail = appEmail;
        }

        public String getAppWebsite() {
            return appWebsite;
        }

        public void setAppWebsite(String appWebsite) {
            this.appWebsite = appWebsite;
        }

        public String getAppDescription() {
            return appDescription;
        }

        public void setAppDescription(String appDescription) {
            this.appDescription = appDescription;
        }

        public String getAppTerms() {
            return appTerms;
        }

        public void setAppTerms(String appTerms) {
            this.appTerms = appTerms;
        }

        public String getAppPrivacy() {
            return appPrivacy;
        }

        public void setAppPrivacy(String appPrivacy) {
            this.appPrivacy = appPrivacy;
        }

        public String getAppToken() {
            return appToken;
        }

        public void setAppToken(String appToken) {
            this.appToken = appToken;
        }

        public String getAdmobStatus() {
            return admobStatus;
        }

        public void setAdmobStatus(String admobStatus) {
            this.admobStatus = admobStatus;
        }

        public String getAdmobBannerKey() {
            return admobBannerKey;
        }

        public void setAdmobBannerKey(String admobBannerKey) {
            this.admobBannerKey = admobBannerKey;
        }

        public String getAdmobInterstialKey() {
            return admobInterstialKey;
        }

        public void setAdmobInterstialKey(String admobInterstialKey) {
            this.admobInterstialKey = admobInterstialKey;
        }

        public String getTitleHome() {
            return titleHome;
        }

        public void setTitleHome(String titleHome) {
            this.titleHome = titleHome;
        }

        public String getTitleCategory() {
            return titleCategory;
        }

        public void setTitleCategory(String titleCategory) {
            this.titleCategory = titleCategory;
        }

        public String getTitleVideo() {
            return titleVideo;
        }

        public void setTitleVideo(String titleVideo) {
            this.titleVideo = titleVideo;
        }

        public String getTitleFavorite() {
            return titleFavorite;
        }

        public void setTitleFavorite(String titleFavorite) {
            this.titleFavorite = titleFavorite;
        }

        public String getTitleProfile() {
            return titleProfile;
        }

        public void setTitleProfile(String titleProfile) {
            this.titleProfile = titleProfile;
        }

        public String getTitleSettings() {
            return titleSettings;
        }

        public void setTitleSettings(String titleSettings) {
            this.titleSettings = titleSettings;
        }

        public String getTitleLogin() {
            return titleLogin;
        }

        public void setTitleLogin(String titleLogin) {
            this.titleLogin = titleLogin;
        }

        public String getTitleRegister() {
            return titleRegister;
        }

        public void setTitleRegister(String titleRegister) {
            this.titleRegister = titleRegister;
        }

        public String getTitleAbout() {
            return titleAbout;
        }

        public void setTitleAbout(String titleAbout) {
            this.titleAbout = titleAbout;
        }

        public String getActionSettings() {
            return actionSettings;
        }

        public void setActionSettings(String actionSettings) {
            this.actionSettings = actionSettings;
        }

        public String getTitleActiviyNewsDetail() {
            return titleActiviyNewsDetail;
        }

        public void setTitleActiviyNewsDetail(String titleActiviyNewsDetail) {
            this.titleActiviyNewsDetail = titleActiviyNewsDetail;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getSearchHere() {
            return searchHere;
        }

        public void setSearchHere(String searchHere) {
            this.searchHere = searchHere;
        }

        public String getTimeoutError() {
            return timeoutError;
        }

        public void setTimeoutError(String timeoutError) {
            this.timeoutError = timeoutError;
        }

        public String getHintEmail() {
            return hintEmail;
        }

        public void setHintEmail(String hintEmail) {
            this.hintEmail = hintEmail;
        }

        public String getSettingsSaved() {
            return settingsSaved;
        }

        public void setSettingsSaved(String settingsSaved) {
            this.settingsSaved = settingsSaved;
        }

        public String getSuccesfull() {
            return succesfull;
        }

        public void setSuccesfull(String succesfull) {
            this.succesfull = succesfull;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getHintPassword() {
            return hintPassword;
        }

        public void setHintPassword(String hintPassword) {
            this.hintPassword = hintPassword;
        }

        public String getHintName() {
            return hintName;
        }

        public void setHintName(String hintName) {
            this.hintName = hintName;
        }

        public String getYouHavaRated() {
            return youHavaRated;
        }

        public void setYouHavaRated(String youHavaRated) {
            this.youHavaRated = youHavaRated;
        }

        public String getBtnLogin() {
            return btnLogin;
        }

        public void setBtnLogin(String btnLogin) {
            this.btnLogin = btnLogin;
        }

        public String getBtnRegister() {
            return btnRegister;
        }

        public void setBtnRegister(String btnRegister) {
            this.btnRegister = btnRegister;
        }

        public String getBtnLinkToRegister() {
            return btnLinkToRegister;
        }

        public void setBtnLinkToRegister(String btnLinkToRegister) {
            this.btnLinkToRegister = btnLinkToRegister;
        }

        public String getBtnLinkToLogin() {
            return btnLinkToLogin;
        }

        public void setBtnLinkToLogin(String btnLinkToLogin) {
            this.btnLinkToLogin = btnLinkToLogin;
        }

        public String getWelcome() {
            return welcome;
        }

        public void setWelcome(String welcome) {
            this.welcome = welcome;
        }

        public String getBtnLogout() {
            return btnLogout;
        }

        public void setBtnLogout(String btnLogout) {
            this.btnLogout = btnLogout;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotLoginFavoriteMessage() {
            return notLoginFavoriteMessage;
        }

        public void setNotLoginFavoriteMessage(String notLoginFavoriteMessage) {
            this.notLoginFavoriteMessage = notLoginFavoriteMessage;
        }

        public String getPleaseFillTheFields() {
            return pleaseFillTheFields;
        }

        public void setPleaseFillTheFields(String pleaseFillTheFields) {
            this.pleaseFillTheFields = pleaseFillTheFields;
        }

        public String getPleaseFillCredentials() {
            return pleaseFillCredentials;
        }

        public void setPleaseFillCredentials(String pleaseFillCredentials) {
            this.pleaseFillCredentials = pleaseFillCredentials;
        }

        public String getYouMustSignToFavorites() {
            return youMustSignToFavorites;
        }

        public void setYouMustSignToFavorites(String youMustSignToFavorites) {
            this.youMustSignToFavorites = youMustSignToFavorites;
        }

        public String getValidEmailAdres() {
            return validEmailAdres;
        }

        public void setValidEmailAdres(String validEmailAdres) {
            this.validEmailAdres = validEmailAdres;
        }

        public String getLatestNews() {
            return latestNews;
        }

        public void setLatestNews(String latestNews) {
            this.latestNews = latestNews;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getUpdateProfile() {
            return updateProfile;
        }

        public void setUpdateProfile(String updateProfile) {
            this.updateProfile = updateProfile;
        }

        public String getTermsConditions() {
            return termsConditions;
        }

        public void setTermsConditions(String termsConditions) {
            this.termsConditions = termsConditions;
        }

        public String getTitleTerms() {
            return titleTerms;
        }

        public void setTitleTerms(String titleTerms) {
            this.titleTerms = titleTerms;
        }

        public String getSaving() {
            return saving;
        }

        public void setSaving(String saving) {
            this.saving = saving;
        }

        public String getYouMustAcceptTerms() {
            return youMustAcceptTerms;
        }

        public void setYouMustAcceptTerms(String youMustAcceptTerms) {
            this.youMustAcceptTerms = youMustAcceptTerms;
        }

        public String getUsernameCannotBeLess() {
            return usernameCannotBeLess;
        }

        public void setUsernameCannotBeLess(String usernameCannotBeLess) {
            this.usernameCannotBeLess = usernameCannotBeLess;
        }

        public String getQueryCannotBeLess() {
            return queryCannotBeLess;
        }

        public void setQueryCannotBeLess(String queryCannotBeLess) {
            this.queryCannotBeLess = queryCannotBeLess;
        }

        public String getPasswordCannotBeLess() {
            return passwordCannotBeLess;
        }

        public void setPasswordCannotBeLess(String passwordCannotBeLess) {
            this.passwordCannotBeLess = passwordCannotBeLess;
        }

        public String getLoggingIn() {
            return loggingIn;
        }

        public void setLoggingIn(String loggingIn) {
            this.loggingIn = loggingIn;
        }

        public String getUpdating() {
            return updating;
        }

        public void setUpdating(String updating) {
            this.updating = updating;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getEmai() {
            return emai;
        }

        public void setEmai(String emai) {
            this.emai = emai;
        }

        public String getNameAbout() {
            return nameAbout;
        }

        public void setNameAbout(String nameAbout) {
            this.nameAbout = nameAbout;
        }

        public String getCategoriTitle() {
            return categoriTitle;
        }

        public void setCategoriTitle(String categoriTitle) {
            this.categoriTitle = categoriTitle;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInternetError() {
            return internetError;
        }

        public void setInternetError(String internetError) {
            this.internetError = internetError;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

    }


}