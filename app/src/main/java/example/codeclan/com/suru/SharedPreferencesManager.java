package example.codeclan.com.suru;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by user on 25/04/2017.
 */

public class SharedPreferencesManager {
    public static ArrayList<String> getTasks(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String tasks = sharedPreferences.getString("tasks", null);

        if(tasks == null) {
            return new ArrayList<String>();
        }

        Gson gson = new Gson();
        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>(){};

        ArrayList<String> taskslist = gson.fromJson(tasks, typeToken.getType() );

        return taskslist;

    }

    public static void addTask(Context context, String task) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<String> tasks = getTasks(context);

        tasks.add(task);

        Gson gson = new Gson();

        String tasksListAsAString = gson.toJson(tasks);

        editor.putString("tasks", tasksListAsAString);
        editor.apply();
    }

    public static void setAllTask(Context context, ArrayList<String> taskList) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String tasksListAsAString = gson.toJson(taskList);

        editor.putString("tasks", tasksListAsAString);
        editor.apply();
    }
}
