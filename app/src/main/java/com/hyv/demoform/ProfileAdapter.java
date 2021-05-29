package com.hyv.demoform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    List<ProfileModel> profileModels;
    private Context mContext;

    public ProfileAdapter(List<ProfileModel> profileModels, Context mContext) {
        this.profileModels = profileModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.bindData(profileModels.get(position));
    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nameTxt) TextView nameTxt;
        @BindView(R.id.emailTxt) TextView emailTxt;
        @BindView(R.id.contactTxt) TextView contactTxt;
        @BindView(R.id.dobTxt) TextView dobTxt;
        @BindView(R.id.addressTxt) TextView addressTxt;
        @BindView(R.id.cityTxt) TextView cityTxt;
        @BindView(R.id.pincodeTxt) TextView pincodeTxt;
        @BindView(R.id.stateTxt) TextView stateTxt;
        @BindView(R.id.countryTxt) TextView countryTxt;
        @BindView(R.id.gstNoTxt) TextView gstNoTxt;
        @BindView(R.id.panNoTxt) TextView panNoTxt;
        @BindView(R.id.aadharnoTxt) TextView aadharnoTxt;
        @BindView(R.id.drivingLicTxt) TextView drivingLicTxt;
        @BindView(R.id.voterIdTxt) TextView voterIdTxt;
        @BindView(R.id.upiIdTxt) TextView upiIdTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ProfileModel profileModel) {
            nameTxt.setText(profileModel.getUser_name());
            emailTxt.setText(profileModel.getEmail_id());
            contactTxt.setText(profileModel.getContact_number());
            dobTxt.setText(profileModel.getDate_of_birth());
            addressTxt.setText(profileModel.getAddress());
            cityTxt.setText(profileModel.getCity());
            pincodeTxt.setText(profileModel.getPincode());
            stateTxt.setText(profileModel.getState());
            countryTxt.setText(profileModel.getCountry());
            gstNoTxt.setText(profileModel.getGstin_no());
            panNoTxt.setText(profileModel.getPan_no());
            aadharnoTxt.setText(profileModel.getAadhaar_no());
            drivingLicTxt.setText(profileModel.getDriving_licence());
            voterIdTxt.setText(profileModel.getVoter_id());
            upiIdTxt.setText(profileModel.getUpi_id());
        }
    }
}
