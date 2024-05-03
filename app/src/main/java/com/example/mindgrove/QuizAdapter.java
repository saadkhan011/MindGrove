package com.example.mindgrove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    ArrayList<Quiz> quiz;
    ArrayList<String> selectedOptions; // New ArrayList to store selected options
    Context context;

    public QuizAdapter(Context context, ArrayList<Quiz> items){
        this.context = context;
        quiz = items;
        selectedOptions = new ArrayList<>(); // Initialize the ArrayList
        // Populate selectedOptions with default values (empty strings) for each Quiz object
        for (int i = 0; i < items.size(); i++) {
            selectedOptions.add("");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView question, option1, option2, option3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);

            // Set click listeners to options
            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOption(getAdapterPosition(), option1.getText().toString());
                }
            });

            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOption(getAdapterPosition(), option2.getText().toString());
                }
            });

            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOption(getAdapterPosition(), option3.getText().toString());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_question, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.question.setText(quiz.get(position).getQuestion());
        holder.option1.setText(quiz.get(position).getOption1());
        holder.option2.setText(quiz.get(position).getOption2());
        holder.option3.setText(quiz.get(position).getOption3());
    }

    @Override
    public int getItemCount() {
        return quiz.size();
    }

    private void saveOption(int position, String selectedOption) {
        // Update the selected option ArrayList with the selected option at the given position
        selectedOptions.set(position, selectedOption);
    }

    // Method to get the submitted options
    public ArrayList<String> getSubmittedOptions() {
        return selectedOptions;
    }
}
