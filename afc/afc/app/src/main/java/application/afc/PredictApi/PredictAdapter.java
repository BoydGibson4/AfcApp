// Package containing code related to the PredictApi
package application.afc.PredictApi;

// Importing necessary Android classes
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Adapter class for spinners
public class PredictAdapter<T> extends BaseAdapter {

    private final List<T> data; // Data to populate the spinner
    private final LayoutInflater inflater; // Inflater to inflate layout for spinner items

    // Constructor initializing adapter with context and data
    public PredictAdapter(Context context, List<T> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    // Get the count of items in the data
    @Override
    public int getCount() {
        return data.size();
    }

    // Get an item at a specific position
    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    // Get the item's Id at a specific position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Get the view for each spinner item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // Inflate layout for the spinner item if the view is null
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        // Setting the text for the spinner item
        TextView text = view.findViewById(android.R.id.text1);
        text.setText(getItem(position).toString());

        return view; // Return the modified view for the spinner item
    }
}
