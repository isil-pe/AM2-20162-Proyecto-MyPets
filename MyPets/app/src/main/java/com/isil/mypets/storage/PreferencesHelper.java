
package com.isil.mypets.storage;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    private static final String MYPETS_PREFERENCES = "myPetsPreferences";
    private static final String PREFERENCES_EMAIL = MYPETS_PREFERENCES + ".email";
    private static final String PREFERENCES_PASSWORD = MYPETS_PREFERENCES + ".password";
    private static final String PREFERENCES_OBJECTID = MYPETS_PREFERENCES + ".objectId";
    private static final String PREFERENCES_ALIAS = MYPETS_PREFERENCES + ".alias";

    private PreferencesHelper() {
        //no instance
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_EMAIL);
        editor.remove(PREFERENCES_PASSWORD);
        editor.remove(PREFERENCES_OBJECTID);
        editor.remove(PREFERENCES_ALIAS);
        editor.apply();
    }

    public static void saveSession(Context context,String email,String password,String objectID,String alias)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_EMAIL, email);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.putString(PREFERENCES_OBJECTID, objectID);
        editor.putString(PREFERENCES_ALIAS, alias);
        editor.apply();
    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String email= sharedPreferences.getString(PREFERENCES_EMAIL,null);

        return email;
    }

    public static String getPassSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String pass= sharedPreferences.getString(PREFERENCES_PASSWORD, null);

        return pass;
    }

    public static String getIdSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String id= sharedPreferences.getString(PREFERENCES_OBJECTID, null);

        return id;
    }

    public static String getAliasSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String alias= sharedPreferences.getString(PREFERENCES_ALIAS, null);
        return alias;
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_EMAIL) &&
                preferences.contains(PREFERENCES_PASSWORD)&&
                preferences.contains(PREFERENCES_OBJECTID)&&
                preferences.contains(PREFERENCES_ALIAS);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MYPETS_PREFERENCES, Context.MODE_PRIVATE);
    }
}
