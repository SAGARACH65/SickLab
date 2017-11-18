package com.example.sagar.sicklab;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sagar.database.DataStoreUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginPage extends AppCompatActivity implements ServerIP {
    private String user_name, pass_word;
    Dialog dialog;
    private static final String PREF_NAME = "LOGIN_PREF";
    private boolean has_error_occured = false;
    private static final String PREF_IS_LOGGED_IN = "IS_LOGGED";
    //call intent variable is used to ensure that web service is not called even if pw and un are not entered
    private boolean callintent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);

        boolean hasLogged = settings.getBoolean("FirstLogin", true);

        if (!hasLogged) {



            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            startActivity(intent);
            finish();
        }

        TextView tv1 = (TextView) findViewById(R.id.textView2);
        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUP.class);

                startActivity(intent);

            }
        });

        TextView tv2 = (TextView) findViewById(R.id.textView5);
        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUP.class);

                startActivity(intent);

            }
        });


        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//
//                startActivity(intent);


                boolean isAvailable = Utility.isNetworkAvailable(LoginPage.this);
                if (!isAvailable) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection Available", Toast.LENGTH_LONG).show();
                    callintent = true;
                } else {
                    EditText login_field = (EditText) findViewById(R.id.input_email);
                    EditText password_field = (EditText) findViewById(R.id.input_password);
                    if (isempty(login_field)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Username", Toast.LENGTH_LONG).show();
                        callintent = false;
                    }
                    if (isempty(password_field)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();
                        callintent = false;

                    }
                    user_name = login_field.getText().toString();
                    pass_word = password_field.getText().toString();

                    if (callintent) {


                        LoginPage.ConnectToLogin connect = new LoginPage.ConnectToLogin();
                        connect.execute();

                    }

                }
            }
        });

    }

    private void storeInfo(String token, String type, String email, String user_name) {
        DataStoreUser dba = new DataStoreUser(getApplicationContext());
        dba.storeCredentials(token, type, email, user_name, true, false);
    }

    private boolean isempty(EditText et1) {

        return et1.getText().toString().trim().length() == 0;
    }

    private class ConnectToLogin extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

             //showning dialog box
            dialog = new Dialog(LoginPage.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.progress_dialog_box);
            dialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = IP + login_head + login_email + user_name + login_password + pass_word;

            String jsonStr = sh.makeServiceCall(url);

            Log.e("LoginPage", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
//                    JSONArray loginresponse = jsonObj.json("contacts");
                    String check_message = jsonObj.getString("success");


                    if (check_message.equals("true")) {


                        String token = jsonObj.getString("api_token");
                        String user_type = jsonObj.getString("type");
                        String user_name = jsonObj.getString("name");
                        String user_email = jsonObj.getString("email");

                        storeInfo(token, user_type, user_email, user_name);

                        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("FirstLogin", false);
                        editor.apply();


                        dialog.dismiss();
                        //starts another aCTIVITY
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        dialog.dismiss();
                        has_error_occured = true;
                    }


//                    // looping through All Contacts
//                    for (int i = 0; i < loginresponse.length(); i++) {
//                        JSONObject c = loginresponse.getJSONObject(i);
//                        String id = c.getString("id");
//                        String name = c.getString("name");
//                        String email = c.getString("email");
//                        String address = c.getString("address");
//                        String gender = c.getString("gender");
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        contact.put("id", id);
//                        contact.put("name", name);
//                        contact.put("email", email);
//                        contact.put("mobile", mobile);
//
//                        // adding contact to contact list
//
//                    }

                } catch (final JSONException e) {
                    Log.e("LoginPage", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("LoginPage", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server.",
//                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            if (has_error_occured) {
                Toast.makeText(getApplicationContext(), "Please Enter The Right Credentials", Toast.LENGTH_LONG).show();

            }


        }
    }
}
