package com.example.jeffnyangoya.alkibapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.example.jeffnyangoya.alkibapp.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;

/**
 * Created by Jeff Nyangoya on 11/8/2015.
 */
public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT=1000*15;
    public static final String SERVER_ADDRESS ="http://172.0.0.1/";

    public ServerRequests(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user,GetUserCallback userCallback){
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user,GetUserCallback callback){
        progressDialog.show();
        new fetchUserDataAsyncTask(user,callback).execute();

    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void,Void,Void>{
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user,GetUserCallback userCallback){
            this.user = user;
            this.userCallback= userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList< NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("regNo", user.regNo));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client =new DefaultHttpClient(httpRequestParams);
            HttpPost post =new HttpPost(SERVER_ADDRESS + "Register.php");
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }

    }
    public class fetchUserDataAsyncTask extends AsyncTask<Void,Void,User>{
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user,GetUserCallback userCallback){
            this.user = user;
            this.userCallback= userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList< NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client =new DefaultHttpClient(httpRequestParams);
            HttpPost post =new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length()== 0){
                    user =null;
                }else {
                    String name = jObject.getString("name");
                    String regNo = jObject.getString("regNo");

                    returnedUser = new User(name,regNo,user.username, user.password);
                }

            }catch(Exception e){

                e.printStackTrace();
            }


            return returnedUser;
        }
        @Override
        protected void onPostExecute(User returnedUser){
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
}
