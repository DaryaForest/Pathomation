package com.pathomation.util;

public class ConstantsPma2 {
    public static final String DEFAULT_BROWSER = System.getProperty("browser");

    public static final int ELEMENT_MEGA_EXTRALONG_TIMEOUT_SECONDS = 360;
    public static final int ELEMENT_EXTRALONG_TIMEOUT_SECONDS = 180;
    public static final int ELEMENT_LONG_TIMEOUT_SECONDS = 120;
    public static final int ELEMENT_TIMEOUT_SECONDS = 20;
    public static final int ELEMENT_SMALL_TIMEOUT_MILLISECONDS = 10;
    public static final int ELEMENT_EXTRASMALL_TIMEOUT_SECONDS = 5;
    public static final int ELEMENT_MICRO_TIMEOUT_SECONDS = 2;

    public static String BASE_URL = System.getProperty("baseurl");
    public static String LOGIN_URL = BASE_URL + "LogOn";
    public static String ROOT_DIRECTORY_URL = BASE_URL + "RootDirectories";
    public static String ROOT_DIRECTORY_CONTENT_HEADER = "Root directories";


    // public static String API_URL = System.getProperty("baseurl") + "api?wsdl";
    //XML
    public static String VERSION_INFO_JSON = "{\"d\":\"1.1.0.855\"}";
    public static String ADMIN_GET_USERS_XML = System.getProperty("baseurl") + "admin/xml/GetUsers";
    //public static String ADMIN_GET_USERS_XML_PMA2 = System.getProperty("baseurl") + "admin/xml/GetUsers?sessionID=%s";

    public static String API_URL = System.getProperty("baseurl") + "api?singleWsdl";
    public static String API_ADMIN_URL = System.getProperty("baseurl") + "admin?singleWsdl";
    public static String USERS_TABLE = System.getProperty("baseurl") + "User";


    public static String API_XML_URL = System.getProperty("baseurl") + "api/xml/";
    public static String ADMIN_XML_URL = System.getProperty("baseurl") + "admin/xml/";
    public static String CAN_ANNOTATE_API_XML_URL = System.getProperty("baseurl") + "/api/xml/CanAnnotate?sessionID=%s";
    public static String GET_ANNOTATION_API_XML_URL = System.getProperty("baseurl") + "/api/xml/GetAnnotations?sessionID=%s";
    public static String VERSION_API_URL = System.getProperty("baseurl") + "api/xml/GetVersionInfo";
    public static String VERSION_API_JSON_URL = System.getProperty("baseurl") + "api/json/GetVersionInfo";
    public static String ADMIN_API_CREATE_USER_URL_PMA2 = "http://localhost:8081/test/pma.core.2/admin/json/CreateUser";
    public static String AUTHENTICATE_API_URL = System.getProperty("baseurl")
            + "api/xml/Authenticate?username=%s&password=%s&caller=test";
    public static String ADMIN_AUTHENTICATE_API_URL = System.getProperty("baseurl")
            + "admin/xml/AdminAuthenticate?username=%s&password=%s&caller=test";
    public static String AUTHENTICATE_API_URL_PMA2 =
            "http://localhost:8081/test/pma.core.2/api/xml/Authenticate?username=%s&password=%s&caller=test";
    public static String CREATE_ROOT_DIRECTORY_URL = System.getProperty("baseurl") + "RootDirectories/CreateFs";


    public static String GET_ROOT_DIRECTORY_URL = "GetRootDirectories";
    public static String GET_DIRECTORY_URL = "GetDirectories";
    public static String GET_ANNOTATION_DISTANCE_URL = "GetAnnotationDistance";
    public static String GET_ANNOTATION_SURFACE_AREA_URL = "GetAnnotationSurfaceArea";
    public static String GET_FILES_DIRECTORY_URL = "GetFiles";
    public static String GET_UID_URL = "GetUID";
    public static String REVERSE_LOOKUP_ID = "ReverseLookupUID";
    public static String REVERSE_LOOKUP_ROOT_DIRECTORY = "ReverseLookupRootDirectory";

    public static String VIEWER_URL = System.getProperty("baseurl") + "Viewer/index.htm?sessionID=%s&pathOrUid=%s/%s/%s/%s";

    public static String WSI_DATA_PATH = "d:/pathomation/wsi_data/reference/brightfield";
    public static String REFERENCE_PATH = "d:\\pathomation\\wsi_data\\reference\\brightfield";
    public static String _SYS_REF_PATH = "D:\\pathomation\\wsi_data\\reference";
    public static String CONTENSUS_DATA_PATH = "D:\\test_environment\\jenkins\\workspace\\PMA.core 2.x\\src\\_tests\\ConsensusData\\";

    public static String ALIAS = "_sys_ref";
    public static String ALIAS_REF = "ref";

    public static String EDIT_BREAD_CRUMB = "Edit";


    public static String DEFAULT_LIB_DIR = System.getProperty("lib.dir");

    //Users
    public static String ADMIN_USER_NAME = "admin";
    public static String INCORRECT_ADMIN_USER_PASSWORD = "ADMIN";
    public static String ADMIN_PASSWORD = "admin";

    public static String USER1_NAME = "user1";
    public static String USER1_B_NAME = "b_user1";
    public static String USER1_PASSWORD = "user1";
    public static String USER_CAN_ANNOTATE_NAME = "b_user1an";
    public static String USER_CAN_NOT_ANNOTATE_NAME = "b_user1nan";

    public static String ADD_USER_FIRST_NAME = "foo";
    public static String ADD_USER_LAST_NAME = "bar";
    public static String ADD_USER_PASSWORD = "12345";
    public static String ADD_USER_LOGIN = "user_foo_bar";
    public static String ADD_USER_EMAIL = "a@test.com";

    //Tiles
    public static String IMAGE_TILE_STANDART =
            "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2Ftest.scn&x=7&y=6&z=5";
    public static String IMAGE_TILE_HREF_QUALITY_90 =
            "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2Ftest.scn&x=7&y=6&z=5&format=jpg&quality=90";
    public static String IMAGE_TILE_HREF_FORMAT_JPG =
            "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2Ftest.scn&x=7&y=6&z=5&format=jpg";
    public static String IMAGE_TILE_HREF_FORMAT_PNG =
            "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2Ftest.scn&x=7&y=6&z=5&format=png";
    public static String IMAGE_TILE_HREF_QUALITY_10 =
            "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2Ftest.scn&x=7&y=6&z=5&format=jpg&quality=10";


    //Settings
    public static String IMAGE_DEFAULT_FORMAT = "Jpeg";
    public static String IMAGE_DEFAULT_SIZE = "512*512";
    public static String IMAGE_TILE_HREF1 = "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2FMenarini%2Fiqnpath02_P15_443_HE.gxp&x=27&y=2&z=6";
    public static String IMAGE_TILE_HREF2 = "&channels=0&timeframe=0&layer=0&pathOrUid=_sys_ref%2FMenarini%2Fiqnpath02_P15_443_HE.gxp&x=24&y=3&z=6";
    public static String IMAGE_PNG_FORMAT = "image/png";
    public static String IMAGE_JPEG_FORMAT = "image/jpeg";
    public static String SESSION_ID = "z-v4vDdBpH4nD5R0MzNpnw2";
    public static String TEST_EMAIL = "test@test.ts";
    public static String PATH_TO_LIC_RANDOM = "D:\\test_environment\\pma.core.1\\trunk\\scripts\\consensus_data\\lic_random.lic";
    public static String PATH_TO_LIC_DEPENDS_FROM_REQ_CODE = "D:\\test_environment\\pma.core.1\\trunk\\scripts\\consensus_data\\lic_%s.lic";
    //XML tags
    public static String LOGON_FALURE_REASON = "LDAP log on failure";
    public static String LOGON_FALURE_REASON_LDAP = "LDAP log on failure";
    //pages
    public static String USER_AUDIT_HREF = System.getProperty("baseurl") + "User/AuditTrail/%s";;
    public static String USER_DETAILS_HREF = System.getProperty("baseurl") + "User/Details/%s";;

    //package name
    public static String BACKEND_TESTS_PACKAGE_NAME = "backendapi";

    //Content
    public static String MANUAL_NAME = "PMA.core.pdf";




}
