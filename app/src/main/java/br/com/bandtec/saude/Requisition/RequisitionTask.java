package br.com.bandtec.saude.Requisition;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by enzo on 26/09/2015.
 */
public abstract class RequisitionTask
{
    public interface OnRequisitionEnd
    {
        void onRequisitionEnd(String json, int status, Exception e);
    }

    public static void enviarRequisicao(final OnRequisitionEnd callback,final String url,final String metodo, final Object data, final Context context)
    {
        final String json = new Gson().toJson(data);

        (new AsyncTask<Void, Void, String>()
        {
            private int status;
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute()
            {
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Aguarde...");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(Void... params)
            {
                HttpClient httpClient = new DefaultHttpClient();
                HttpUriRequest httpUriRequest = null;

                try
                {
                    if (metodo.toUpperCase().equals("GET"))
                    {
                        httpUriRequest = new HttpGet(url);
                    }
                    else
                    {
                        if (metodo.toUpperCase().equals("POST"))
                        {
                            HttpPost httpPost = new HttpPost(url);
                            httpPost.setEntity(new ByteArrayEntity(json.getBytes()));

                            httpUriRequest = httpPost;
                        }
                    }

                    httpUriRequest.setHeader("Content-Type", "application/json");

                    HttpResponse httpResponse = httpClient.execute(httpUriRequest);

                    status = httpResponse.getStatusLine().getStatusCode();

                    String entity = EntityUtils.toString(httpResponse.getEntity());
                    return entity;
                }
                catch(Exception e)
                {
                    progressDialog.dismiss();
                    callback.onRequisitionEnd(null, status, e);
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s)
            {
                progressDialog.dismiss();
                if (s != null)
                {
                    callback.onRequisitionEnd(s, status, null);
                }
            }
        }).execute();
    }
}
