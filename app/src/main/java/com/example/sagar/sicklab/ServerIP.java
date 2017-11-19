package com.example.sagar.sicklab;

/**
 * Created by Sagar on 11/18/2017.
 */

public interface ServerIP {
    String IP = "http://192.168.1.50:8000/api/";
    String register_head = "register?";
    String login_head = "login?";
    String report_head = "report?";
    String register_name = "name=";
    String register_type = "&type=";
    String register_password = "&password=";
    String register_email = "&email=";


    String login_email = "email=";
    String login_password = "&password=";

    String report_disease = "disease=";
    String report_token = "&api_token=";
    String report_district = "&district=";
    String report_no_of_victims = "&no_of_victims=";
    String report_latitude = "&latitude=";
    String report_longitude = "&longitude=";

    String trending_head = "disease/trending?";
    String trending_token = "api_token=";
    String trending_district = "&district=";
    String trending_latitude = "&latitude=";
    String trending_longitude = "&longitude=";

    String history_head = "disease/history?";

    String suggestion_head = "suggestion?";
    String suggestion_text = "text=";
    String suggestion_disease = "&disease=";
    String suggestion_token = "&api_token=";


}
