package example.codeclan.com.suru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 25/04/2017.
 */

public class TaskAdaptor extends ArrayAdapter<String> {

    public TaskAdaptor(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View item, ViewGroup viewGroup) {
        if(item == null) {
            item = LayoutInflater.from(getContext()).inflate(R.layout.suru_item, viewGroup, false);
        }

        TextView textView = (TextView) item.findViewById(R.id.task_title);

        String task = getItem(position);

        textView.setText(task);

        item.findViewById(R.id.task_delete).setTag(task);

        return item;
    }
}
