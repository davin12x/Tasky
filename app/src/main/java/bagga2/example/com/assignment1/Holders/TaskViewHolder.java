package bagga2.example.com.assignment1.Holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bagga2.example.com.assignment1.Adapters.TaskAdapter;
import bagga2.example.com.assignment1.Fragments.Recycler;
import bagga2.example.com.assignment1.R;

/**
 * Created by Davin12x on 16-06-07.
 */
//This class will push data into their correspondence field
public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View v;
    CardView cv;
    public TextView txtViewTitle,date,timeView;
    // each data item is just a string in this case
    public IMyViewHolderClicks mListener;

    public TaskViewHolder(View itemView,IMyViewHolderClicks mListener) {
        super(itemView);
        v= itemView;
        this.mListener = mListener;

        //refrence to fields
        cv = (CardView)itemView.findViewById(R.id.card_view);
        txtViewTitle = (TextView) itemView.findViewById(R.id.info_text);
        date = (TextView) itemView.findViewById(R.id.date);
        timeView = (TextView) itemView.findViewById(R.id.times);
        txtViewTitle.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            mListener.ontext((TextView)v);
            mListener.position(getAdapterPosition());
        }
    }

    //Custom interfave used to get the text on card click and its position
    public interface IMyViewHolderClicks {
        public void ontext(TextView textView);
        public void position(int position);

    }
}
