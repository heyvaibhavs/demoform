package com.hyv.demoform;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.hyv.demoform.http.DataManager;
import com.hyv.demoform.model.response.UserDetailsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.nameBox)
    EditText nameBox;
    @BindView(R.id.emailBox) EditText emailBox;
    @BindView(R.id.contactBox) EditText contactBox;
    @BindView(R.id.dobBox) EditText dobBox;
    @BindView(R.id.addressBox) EditText addressBox;
    @BindView(R.id.cityBox) EditText cityBox;
    @BindView(R.id.stateSpinner)
    Spinner stateSpinner;
    @BindView(R.id.countrySpinner) Spinner countrySpinner;
    @BindView(R.id.pincodeBox) EditText pincodeBox;
    @BindView(R.id.submitBtnUD)
    Button submitBtnUD;
    @BindView(R.id.progressBarUD)
    ProgressBar progressBarUD;
    final Calendar myCalendar = Calendar.getInstance();
    String nameS = "",emailS="", contactS="", dobS="", addressS="", cityS="", countryS="", stateS="", pincodeS="";
    String url = "https://havventure.com/UserDetails.php";


    String urlK = "https://havventure.com/UserKYC.php";
    @BindView(R.id.gstinBox) EditText gstinBox;
    @BindView(R.id.panNoBox) EditText panNoBox;
    @BindView(R.id.aadharNoBox) EditText addharNoBox;
    @BindView(R.id.drivingLicBox) EditText drivingLicBox;
    @BindView(R.id.voterIdBox) EditText voterIdBox;
    @BindView(R.id.upiIdBox) EditText upiIdBox;
    @BindView(R.id.submitBtnKYC) Button submitBtnKYC;
    @BindView(R.id.progressBarKYC) ProgressBar progressBarKYC;
    String gstinS = "", panS = "", aadharS = "", drivingLicS = "",voterIdS = "", upiIdS = "";

    @BindView(R.id.userDetailsBtn)
    TextView userDetailsBtn;
    @BindView(R.id.userKycBtn) TextView userKycBtn;
    @BindView(R.id.userProfileBtn) TextView userProfileBtn;

    @BindView(R.id.userDetailsLayout) LinearLayout userDetailsLayout;
    @BindView(R.id.userKycLayout)
    LinearLayout userKycLayout;
    @BindView(R.id.userProfileLayout) LinearLayout userProfileLayout;

    ProfileAdapter profileAdapter;
    ArrayList<ProfileModel> profileModels;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    String urlP = "https://havventure.com/UserProfile.php";
    @BindView(R.id.progressUserDetails) ProgressBar progressUserDetails;
    String lastUserDetailsUrl = "https://havventure.com/GetLastUserDetails.php";
    String lastUserKycUrl = "https://havventure.com/GetLastUserKYC.php";
    ArrayAdapter<String> countryAdapter;
    ArrayAdapter<String> stateAdapter;
    @BindView(R.id.stateTx) TextView stateTx;
    @BindView(R.id.countryTx) TextView countryTx;
    @BindView(R.id.countryLay) LinearLayout countryLay;
    @BindView(R.id.statelay) LinearLayout statelay;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int blueColorValue = Color.parseColor("#03179C");
        getWindow().setStatusBarColor(blueColorValue);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        final int yellowColorValue = Color.parseColor("#F7FF09");
        final int whiteColorValue = Color.parseColor("#ffffff");

        lastUser();

        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetailsBtn.setTextColor(yellowColorValue);
                userKycBtn.setTextColor(whiteColorValue);
                userProfileBtn.setTextColor(whiteColorValue);
                userDetailsLayout.setVisibility(View.VISIBLE);
                userKycLayout.setVisibility(View.GONE);
                userProfileLayout.setVisibility(View.GONE);
                lastUser();
            }
        });
        userKycBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetailsBtn.setTextColor(whiteColorValue);
                userKycBtn.setTextColor(yellowColorValue);
                userProfileBtn.setTextColor(whiteColorValue);
                userDetailsLayout.setVisibility(View.GONE);
                userKycLayout.setVisibility(View.VISIBLE);
                userProfileLayout.setVisibility(View.GONE);
                lastKYCDetails();
            }
        });
        userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileModels = new ArrayList<>();
                getProfile();
                userDetailsBtn.setTextColor(whiteColorValue);
                userKycBtn.setTextColor(whiteColorValue);
                userProfileBtn.setTextColor(yellowColorValue);
                userDetailsLayout.setVisibility(View.GONE);
                userKycLayout.setVisibility(View.GONE);
                userProfileLayout.setVisibility(View.VISIBLE);
            }
        });

        getCountryList();
        getIndiaStateList();

        countryTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrySpinner.setVisibility(View.VISIBLE);
                countryLay.setVisibility(View.GONE);
                statelay.setVisibility(View.GONE);
                stateSpinner.setVisibility(View.VISIBLE);
                countrySpinner.setOnItemSelectedListener(MainActivity.this);
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dobBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submitBtnUD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                nameS = nameBox.getText().toString();
                emailS = emailBox.getText().toString();
                contactS = contactBox.getText().toString();
                dobS = dobBox.getText().toString();
                addressS = addressBox.getText().toString();
                cityS = cityBox.getText().toString();
                pincodeS = pincodeBox.getText().toString();

                if (nameS.isEmpty()){
                    nameBox.setError("Enter Name");
                }else if (!emailS.contains("@")){
                    emailBox.setError("Enter Valid Email");
                }else if (contactS.isEmpty()){
                    contactBox.setError("Enter Valid Number");
                }else if (dobS.isEmpty()){
                    dobBox.setError("Enter Date of Birth");
                }else if (addressS.isEmpty()){
                    addressBox.setError("Enter Address");
                }else if (cityS.isEmpty()){
                    cityBox.setError("Enter City");
                }else if (countryS.isEmpty()){
                    Toast.makeText(MainActivity.this, "Select Country", Toast.LENGTH_SHORT).show();
                }else if (stateS.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Select State", Toast.LENGTH_SHORT).show();
                }else if (pincodeS.isEmpty()){
                    pincodeBox.setError("Enter Valid Pin Code");
                }else {
                    submitBtnUD.setVisibility(View.GONE);
                    progressBarUD.setVisibility(View.VISIBLE);
                    submitUserDetails();
                }
            }
        });

        submitBtnKYC.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                gstinS = gstinBox.getText().toString();
                panS = panNoBox.getText().toString();
                aadharS = addharNoBox.getText().toString();
                drivingLicS = drivingLicBox.getText().toString();
                voterIdS = voterIdBox.getText().toString();
                upiIdS = upiIdBox.getText().toString();

                if (gstinS.isEmpty()){
                    gstinBox.setError("Enter GST No.");
                }else if (panS.isEmpty()){
                    panNoBox.setError("Enter PAN No.");
                }else if (aadharS.isEmpty()){
                    addharNoBox.setError("Enter Aadhaar No.");
                }else if (drivingLicS.isEmpty()){
                    drivingLicBox.setError("Enter Driving Licence");
                }else if (voterIdS.isEmpty()){
                    voterIdBox.setError("Enter Voter ID");
                }else if (upiIdS.isEmpty()){
                    upiIdBox.setError("Enter UPI ID");
                }else {
                    submitBtnKYC.setVisibility(View.GONE);
                    progressBarKYC.setVisibility(View.VISIBLE);
                    submitKYCDetails();
                }
            }
        });
    }

    private void getNoStateList() {
        final List<String> state = new ArrayList<String>();
        state.add("Select State");
        stateAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, state);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    private void getIndiaStateList() {
        final List<String> state = new ArrayList<String>();
        state.add("Select State");
        state.add("Bihar");
        state.add("Jharkhand");
        state.add("Uttar Pradesh");
        state.add("Maharastra");
        state.add("Andhra Pradesh");
        state.add("Arunachal Pradesh");
        stateAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, state);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    private void getCountryList() {
        final List<String> country = new ArrayList<String>();
        country.add("Select Country");
        country.add("India");
        country.add("North America");
        country.add("South America");
        country.add("Algeria");
        country.add("Afghanistan");
        country.add("Andorra");
        countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);
    }

    private void lastKYCDetails() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, lastUserKycUrl, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("LastUserKYC");
                    JSONObject jsonObject =jsonArray.getJSONObject(0);
                    gstinBox.setText(jsonObject.getString("gstin_no"));
                    panNoBox.setText(jsonObject.getString("pan_no"));
                    addharNoBox.setText(jsonObject.getString("aadhaar_no"));
                    drivingLicBox.setText(jsonObject.getString("driving_licence"));
                    voterIdBox.setText(jsonObject.getString("voter_id"));
                    upiIdBox.setText(jsonObject.getString("upi_id"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void lastUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, lastUserDetailsUrl, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("LastUserDetails");
                    JSONObject jsonObject =jsonArray.getJSONObject(0);
                    nameBox.setText(jsonObject.getString("user_name"));
                    emailBox.setText(jsonObject.getString("email_id"));
                    contactBox.setText(jsonObject.getString("contact_number"));
                    dobBox.setText(jsonObject.getString("date_of_birth"));
                    addressBox.setText(jsonObject.getString("address"));
                    cityBox.setText(jsonObject.getString("city"));
                    pincodeBox.setText(jsonObject.getString("Pincode"));
                    countryTx.setText(jsonObject.getString("country"));
                    stateTx.setText(jsonObject.getString("state"));

//                    int spinerPos = countryAdapter.getPosition(jsonObject.getString("country"));
//                    countrySpinner.setSelection(spinerPos);
//                    if (countrySpinner.getSelectedItem().equals("India")){
//                        int spinerPosState = stateAdapter.getPosition(jsonObject.getString("state"));
//                        stateSpinner.setSelection(spinerPosState);
//
//                    }else {
//                       getNoStateList();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void getProfile() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlP, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressUserDetails.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = response.getJSONArray("UserDetails");
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);
                        ProfileModel profileModel =new ProfileModel();
                        profileModel.setUser_name(jsonObject.getString("user_name"));
                        profileModel.setEmail_id(jsonObject.getString("email_id"));
                        profileModel.setContact_number(jsonObject.getString("contact_number"));
                        profileModel.setDate_of_birth(jsonObject.getString("date_of_birth"));
                        profileModel.setAddress(jsonObject.getString("address"));
                        profileModel.setCity(jsonObject.getString("city"));
                        profileModel.setState(jsonObject.getString("state"));
                        profileModel.setCountry(jsonObject.getString("country"));
                        profileModel.setPincode(jsonObject.getString("Pincode"));
                        profileModel.setGstin_no(jsonObject.getString("gstin_no"));
                        profileModel.setPan_no(jsonObject.getString("pan_no"));
                        profileModel.setAadhaar_no(jsonObject.getString("aadhaar_no"));
                        profileModel.setDriving_licence(jsonObject.getString("driving_licence"));
                        profileModel.setVoter_id(jsonObject.getString("voter_id"));
                        profileModel.setUpi_id(jsonObject.getString("upi_id"));
                        profileModels.add(profileModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setAdapter(profileModels);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void setAdapter(ArrayList<ProfileModel> data) {
        profileAdapter = new ProfileAdapter(data,MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileAdapter);
    }

    private void submitKYCDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                submitBtnKYC.setVisibility(View.VISIBLE);
                progressBarKYC.setVisibility(View.GONE);
                gstinBox.getText().clear();
                panNoBox.getText().clear();
                addharNoBox.getText().clear();
                drivingLicBox.getText().clear();
                voterIdBox.getText().clear();
                upiIdBox.getText().clear();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                submitBtnKYC.setVisibility(View.VISIBLE);
                progressBarKYC.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("gstin_no",gstinS);
                params.put("pan_no", panS);
                params.put("aadhaar_no",aadharS);
                params.put("driving_licence",drivingLicS);
                params.put("voter_id",voterIdS);
                params.put("upi_id",upiIdS);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    private void submitUserDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                submitBtnUD.setVisibility(View.VISIBLE);
                progressBarUD.setVisibility(View.GONE);
                nameBox.getText().clear();
                emailBox.getText().clear();
                contactBox.getText().clear();
                dobBox.getText().clear();
                addressBox.getText().clear();
                cityBox.getText().clear();
                pincodeBox.getText().clear();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                submitBtnUD.setVisibility(View.VISIBLE);
                progressBarUD.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_name", nameS);
                params.put("email_id", emailS);
                params.put("contact_number",contactS);
                params.put("date_of_birth", dobS);
                params.put("address", addressS);
                params.put("city", cityS);
                params.put("state", stateS);
                params.put("country", countryS);
                params.put("Pincode",pincodeS);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dobBox.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String country = parent.getItemAtPosition(position).toString();
        if (country.equals("India")){
            getIndiaStateList();
        }else {
            getNoStateList();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}