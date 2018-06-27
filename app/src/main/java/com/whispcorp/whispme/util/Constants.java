package com.whispcorp.whispme.util;

import android.os.Environment;

public class Constants {

    public static class Action {
        public final static String LOCATION_UPDATE = "ACTION_LOCATION_UPDATE";
    }

    public static class Extra {
        public final static String LONGITUDE = "EXTRA_LONGITUDE";
        public final static String LATITUDE = "EXTRA_LATITUDE";
    }

    public static class RequestCode {
        public final static int LOCATION = 1111;
        public final static int SETTING_LOCATION = 1112;
        public final static int RECORD_AUDIO = 1113;
        public final static int WRITE_EXTERNAL_STORAGE = 1114;
        public final static int WHISP_RECORDED_AUDIO = 1115;
    }

    public static class UpdateTime {
        public final static int LOCATION = 60000;
    }

    public static class Default {
        public final static double LONGITUDE = -77.0540145;
        public final static double LATITUDE = -12.0877456;
        public final static double BOUNDS = 0.001;
        public final static String WHISP_AUDIO_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/recorded_audio.wav";
    }

    public static class Message {
        public final static String PERMISSION_RATIONALE_LOCATION_TITLE =
                "Permiso de localización";
        public final static String PERMISSION_RATIONALE_LOCATION_MESSAGE =
                "Whispme necesita permiso de localización para obtener whisps cercanos.";
        public final static String PERMISSION_RATIONALE_RECORD_AUDIO_TITLE =
                "Permiso de audio";
        public final static String PERMISSION_RATIONALE_RECORD_AUDIO_MESSAGE =
                "Whispme necesita permiso de grabado de audio para crear whisps.";
        public final static String PERMISSION_RATIONALE_WRITE_EXTERNAL_STORAGE_TITLE =
                "Permiso de escritura";
        public final static String PERMISSION_RATIONALE_WRITE_EXTERNAL_STORAGE_MESSAGE =
                "Whispme necesita permiso de escritura para crear whisps.";
    }

    public static class Auth {
        public static String TOKEN = "TOKEN";
        public static String PASSWORD = "PASSWORD";
        public static String REFRESH_TOKEN = "REFRESH_TOKEN";
    }

    public static class DateFormat {
        public static String TRENDFORMAT = "dd/MM/yyyy";

    }

    public static class SharedPreferencesConstant {
        public final static String USER_ID = "user_id";
        public final static String USER_EMAIL = "user_email";
        public final static String USER_NAME = "user_name";
        public final static String USER_PHOTO = "user_photo";
        public final static String USER_BIO = "user_bio";
    }


}
