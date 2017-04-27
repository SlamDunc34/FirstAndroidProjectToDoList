package example.codeclan.com.suru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_suru);


        ArrayList<String> alltasks = SharedPreferencesManager.getTasks(this);
        TaskAdaptor taskAdaptor = new TaskAdaptor(this, alltasks);

        listView.setAdapter(taskAdaptor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.suru_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void updateList() {
        ArrayList<String> alltasks = SharedPreferencesManager.getTasks(this);
        TaskAdaptor taskAdaptor = new TaskAdaptor(this, alltasks);

        listView.setAdapter(taskAdaptor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_add_task:

                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SharedPreferencesManager.addTask(MainActivity.this, task);
                                MainActivity.this.updateList();
                                Log.d(TAG, "Added Task: " + task);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void onDeleteButtonClicked(View view){
        ArrayList<String> taskList = SharedPreferencesManager.getTasks(this);

        String task = (String) view.getTag();

        taskList.remove(task);

        SharedPreferencesManager.setAllTask(this, taskList);

        updateList();
    }
}
