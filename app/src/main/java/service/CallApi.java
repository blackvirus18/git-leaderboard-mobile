package service;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class CallApi extends AsyncTask<String, String, String> {

    private final String mBaseUrl;
    private ResponseListener mListner;
    public CallApi(String url) {
        super();
        mBaseUrl = url;
    }


    @Override
    protected String doInBackground(String... params) {
        InputStream in=null;
        String result=null;
        try {
            URL url = new URL("http://10.70.210.192:4000/"+mBaseUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in= new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader bReader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            in.close();
            result = sBuilder.toString();
            //System.out.println(result);
            //JSONObject jObj = new JSONObject(result);
            //System.out.println(jObj);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (mListner != null) {
            mListner.onResponse(result);
        }
    }
    public void setResponseListner(final ResponseListener listner) {
        mListner = listner;
    }
    public static void main(String arg[]){


    }
}
