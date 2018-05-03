package com.whispcorp.whispme.util;

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
    }

    public static class UpdateTime {
        public final static int LOCATION = 5000;
    }

    public static class Default {
        public final static double LONGITUDE = -77.0540145;
        public final static double LATITUDE = -12.0877456;
        public final static double BOUNDS = 0.001;
    }

    public static class Message {
        public final static String PERMISSION_RATIONALE_LOCATION_TITLE =
                "Permiso de localización";
        public final static String PERMISSION_RATIONALE_LOCATION_MESSAGE =
                "Whispme necesita permiso de la localización para obtener whisps cercanos.";
    }
}
