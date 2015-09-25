package codeforamerica.sanjose311.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import codeforamerica.sanjose311.R;
import codeforamerica.sanjose311.dto.RequestsDTO;

/**
 * Created by bharath.yarlaga on 8/23/2015.
 */
public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {
    private String[] mDataSet;

    public RequestsAdapter(String[] mDataSet){
        this.mDataSet = mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public ViewHolder(View v) {
            super(v);
            id = (TextView)itemView.findViewById(R.id.info_text);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requests_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(""+ System.currentTimeMillis());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

}
