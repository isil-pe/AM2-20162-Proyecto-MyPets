
package com.isil.mypets.storage;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    private static final String MYPETS_PREFERENCES = "myPetsPreferences";
    private static final String PREFERENCES_EMAIL = MYPETS_PREFERENCES + ".email";
    private static final String PREFERENCES_PASSWORD = MYPETS_PREFERENCES + ".password";

    private PreferencesHelper() {
        //no instance
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_EMAIL);
        editor.remove(PREFERENCES_PASSWORD);
        editor.apply();
    }

    public static void saveSession(Context context,String email,String password)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_EMAIL, email);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.apply();
    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String email= sharedPreferences.getString(PREFERENCES_EMAIL,null);

        return email;
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_EMAIL) &&
                preferences.contains(PREFERENCES_PASSWORD);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MYPETS_PREFERENCES, Context.MODE_PRIVATE);
    }
}
