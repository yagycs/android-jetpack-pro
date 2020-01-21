package com.adeeva.academy.ui.academy;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adeeva.academy.R;
import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.ui.detail.DetailCourseActivity;
import com.adeeva.academy.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AcademyAdapter extends RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder> {
    private final Activity activity;
    private List<CourseEntity> mCourses = new ArrayList<>();

    AcademyAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<CourseEntity> getListCourses() {
        return mCourses;
    }

    void setListCourses(List<CourseEntity> listCourses) {
        if (listCourses == null) return;
        this.mCourses.clear();
        this.mCourses.addAll(listCourses);
    }

    @NonNull
    @Override
    public AcademyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_academy, parent, false);
        return new AcademyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademyViewHolder holder, int position) {
        holder.tvTitle.setText(getListCourses().get(position).getTitle());
        holder.tvDescription.setText(getListCourses().get(position).getDescription());
        //holder.tvDate.setText(String.format("Deadline %s", getListCourses().get(position).getCourseId()));
        holder.tvDate.setText(String.format("Deadline %s", getListCourses().get(position).getDeadline()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailCourseActivity.class);
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, getListCourses().get(position).getCourseId());
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(getListCourses().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListCourses().size();
    }

    public class AcademyViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        public AcademyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
