package com.dkv.ui.question.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ItemQuestionBinding;
import com.dkv.entity.Options;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionItemViewHolder> {

    private List<Options> optionsList;
    private int prvSelection = -1;

    QuestionAdapter(List<Options> options) {
        this.optionsList = options;
    }

    void updateList(List<Options> options) {

        this.optionsList = options;
        notifyDataSetChanged();
    }

    @Override
    public QuestionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_question, parent, false);
        return new QuestionAdapter.QuestionItemViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(QuestionItemViewHolder holder, int position) {

        Options assetTrackingModel = optionsList.get(position);
        holder.bindData(assetTrackingModel, position);

    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    class QuestionItemViewHolder extends RecyclerView.ViewHolder {

        private ItemQuestionBinding binding;

        private QuestionItemViewHolder(View view) {

            super(view);
            binding = DataBindingUtil.bind(itemView);

        }

        private void bindData(Options questionDbModel, int position) {

            String options = questionDbModel.getOptionId() + " . " + android.text.Html.fromHtml(questionDbModel.getValue()).toString();
            binding.optionsText.setText(options);

            if (questionDbModel.isAnswered()) {
                binding.optionsCheckbox.setChecked(true);
                prvSelection = position;
            } else {
                binding.optionsCheckbox.setChecked(false);
            }


            binding.optionsContainer.setOnClickListener(view -> {

                binding.optionsCheckbox.setChecked(!binding.optionsCheckbox.isChecked());


                if (binding.optionsCheckbox.isChecked()) {

                    optionsList.get(position).setAnswered(true);
                } else {
                    optionsList.get(position).setAnswered(false);
                }

                if (position != prvSelection && prvSelection != -1) {
                    optionsList.get(prvSelection).setAnswered(false);

                }
                notifyDataSetChanged();
                prvSelection = position;

            });

        }

    }

}
