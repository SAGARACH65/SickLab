package com.example.sagar.sicklab;

/**
 * Created by Sagar on 11/18/2017.
 */

public interface ServerIP {
    String IP = "http://192.168.1.50:8000/api/";
    String register_head="register?";
    String login_head="login?";
    String report_head="report?";
    String register_name="name=";
    String register_type="&type=";
    String register_password="&password=";
    String register_email="&email=";


    String login_email="email=";
    String login_password="&password=";

    String report_disease="disease=";
    String report_token="&api_token=";
    String report_district="&district=";
    String report_no_of_victims="&no_of_victims=";


}
