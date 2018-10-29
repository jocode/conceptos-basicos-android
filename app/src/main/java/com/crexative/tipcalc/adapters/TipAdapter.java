package com.crexative.tipcalc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crexative.tipcalc.R;
import com.crexative.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private List<TipRecord> dataset;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, List<TipRecord> dataset, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        TipRecord tipRecord = dataset.get(position);
        String strTip = String.format(
                context.getString(R.string.global_message_tip), tipRecord.getTip());
        viewHolder.textView.setText(strTip);
        viewHolder.txtDate.setText(tipRecord.getDateFormat());
        viewHolder.setOnItemClickListener(tipRecord, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(TipRecord tipRecord){
        dataset.add(0, tipRecord);
        // Método propio de los adapters
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtContent)
        TextView textView;
        @BindView(R.id.txtDate)
        TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            // Especifica la vista y el objeto al cual hace el vínculo
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord tipRecord, final OnItemClickListener onItemClickListener) {
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onItemClickListener.onItemClick(tipRecord);
                  }
              });
        }
    }

}
