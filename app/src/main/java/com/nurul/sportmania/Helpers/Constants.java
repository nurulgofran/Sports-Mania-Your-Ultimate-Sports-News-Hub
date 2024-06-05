package com.nurul.sportmania.Helpers;

/**
 * Created by SESAM on 02.09.2018.
 */

public class Constants {
    // CHANGE IT
    public static final String BASE_URL = "http://nurulgofran.com/";
    public static final String PATH_URL = "admin/";
    public static final int LIMIT = 8;
    public static final int LIMIT_LATEST = 3;
    public static final Integer LAYOUT = 0;


    //DONT EDIT
    public static final String IMG_URL = PATH_URL+"upload/";
    public static final String NEWS_URL = PATH_URL+"index.php?r=api";
    public static final String CATEGORY_URL = PATH_URL+"index.php?r=api/category";
    public static final String NEWS_BY_CATEGORY_URL = PATH_URL+"index.php?r=api/CategoryNews";
    public static final String DETAIL_URL = PATH_URL+"index.php?r=api/NewsDetail";
    public static final String CHECK_USER_URL = PATH_URL+"index.php?r=api/checkuser";
    public static final String REGISTER_USER_URL = PATH_URL+"index.php?r=api/user";
    public static final String SETTINGS_URL = PATH_URL+"index.php?r=api/settings";
    public static final String EMOJI_URL = PATH_URL+"index.php?r=api/emojies";
    public static final String NEWS_SEARCH_URL = PATH_URL+"index.php?r=api/search";

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";


}
