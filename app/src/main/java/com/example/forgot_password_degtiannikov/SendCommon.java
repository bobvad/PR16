package com.example.forgot_password_degtiannikov;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;

@SuppressLint("StaticFieldLeak")
public class SendCommon extends AsyncTask<Void, Void, String> {

    public String Url = "http://192.168.0.109:5000/api/CommonController/Send";
    public String Code;
    public EditText TbEmail;
    CallbackResponse CallbackResponse;
    CallbackResponse CallbackError;

    public SendCommon(EditText tbEmail, CallbackResponse callbackResponse, CallbackResponse callbackError) {
        this.TbEmail = tbEmail;
        this.CallbackResponse = callbackResponse;
        this.CallbackError = callbackError;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Document Response = (Document) Jsoup.connect(Url + "?Email=" + TbEmail.getText())
                    .ignoreContentType(true)
                    .get();

            Code = Response.getTextContent();
        } catch (IOException e) {
            Log.e("Errors", e.getMessage());
            return null;
        }
        return Code;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        if (Code == null) {
            CallbackError.toString();
        } else {
            CallbackResponse.returner(Code);
        }
    }
}