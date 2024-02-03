// Package containing code related to the TableApi
package application.afc.TableApi;

// Importing necessary Android classes for RecyclerView and resources
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import application.afc.R;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import java.util.List;

// Adapter class for RecyclerView to display table data
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Table> tables; // List of Table objects

    // Method to update the list of tables in the adapter
    public void updateTables(List<Table> newTables) {
        tables.clear();
        if (newTables != null) {
            tables.addAll(newTables);
        }
        notifyDataSetChanged();
    }

    // Constructor to initialize the TableAdapter with a list of tables
    public TableAdapter(List<Table> tables) {
        this.tables = tables;
    }

    // ViewHolder class to hold views for each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView TeamTextView;
        private ImageView TeamImageView;
        private TextView PositionTextView;
        private TextView PointsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initializing views within the ViewHolder
            TeamTextView = itemView.findViewById(R.id.Name);
            TeamImageView = itemView.findViewById(R.id.TeamImage);
            PositionTextView = itemView.findViewById(R.id.Starting);
            PointsTextView = itemView.findViewById(R.id.Result);
        }
    }

    // Inflating the layout for each item in the RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_table, parent, false);
        return new ViewHolder(itemView);
    }

    // Binding data to views for each item in the RecyclerView
    @Override
    public void onBindViewHolder(TableAdapter.ViewHolder holder, int position) {
        Table table = tables.get(position);

        if (table != null) {
            // Resolving team name and image resource
            String teamName = TeamNameResolver.resolveTeamName(table.getTeam(), holder.itemView.getResources());
            int imageRes = TeamNameResolver.resolveTeamImageResource(table.getTeam(), holder.itemView.getResources(), holder.itemView.getContext().getPackageName());

            Drawable image;
            if (imageRes != 0) {
                // Resolving team image
                image = holder.itemView.getResources().getDrawable(imageRes, holder.itemView.getContext().getTheme());
            } else {
                // If image resource not found, use default image
                Log.e("TableAdapter", "Image resource not found for team: " + table.getTeam());
                image = holder.itemView.getResources().getDrawable(R.drawable.default_image, holder.itemView.getContext().getTheme());
            }

            // Setting image bounds and size
            int imageSize = holder.TeamTextView.getLineHeight();
            image.setBounds(0, 0, imageSize, imageSize);

            // Building SpannableStringBuilder to include text and image
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(String.valueOf(table.getPosition()));

            // Adding spaces for alignment
            int gapLength = 4;
            for (int i = 0; i < gapLength; i++) {
                builder.append(" ");
            }

            int start = builder.length();

            builder.append("   ")
                    .append(teamName)
                    .append(" Points: ")
                    .append(String.valueOf(table.getPoints()));

            builder.setSpan(new ImageSpan(image, ImageSpan.ALIGN_BASELINE), start, start + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            holder.TeamTextView.setText(builder);
        } else {
            Log.e("TableAdapter", "Table at position " + position + " is null.");
        }
    }

    // Getting the total item count in the RecyclerView
    @Override
    public int getItemCount() {
        return tables != null ? tables.size() : 0;
    }

    // Setting tables in the adapter
    public void setTables(List<Table> tables) {
        this.tables.clear();
        if (tables != null) {
            this.tables.addAll(tables);
        }
        notifyDataSetChanged();
        Log.d("TableAdapter", "Tables updated. Count: " + getItemCount());
    }
}
