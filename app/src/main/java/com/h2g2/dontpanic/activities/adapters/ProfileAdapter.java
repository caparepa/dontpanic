package com.h2g2.dontpanic.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.user.SelectProfileActivity;
import com.h2g2.dontpanic.bean.data.Profile;
import com.h2g2.dontpanic.bean.profile.ProfileDataBean;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/29
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView profileName;


        public ViewHolder(View itemView) {
            super(itemView);
            profileName =  itemView.findViewById(R.id.item_profile_name);

        }
    }

    private List<Profile> profiles;
    private SelectProfileActivity activity;

    public ProfileAdapter(List<Profile> profiles, SelectProfileActivity activity) {
        this.profiles = profiles;
        this.activity = activity;

        profiles.add(profiles.size(), new ProfileDataBean());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        System.out.println(position);
        Profile profile = profiles.get(position);
        holder.profileName.setText(profile.getName());

    }

    @Override
    public int getItemCount()
    {
        return profiles.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_row_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
}
