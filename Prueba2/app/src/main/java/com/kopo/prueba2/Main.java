package com.kopo.prueba2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener{

    private EditText value, ETapellido, ETcolorFav, ETedad;
    private Button btn;
    private ProgressBar pb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value=(EditText)findViewById(R.id.editTextNombre);
        ETapellido = (EditText)findViewById(R.id.editTextApellido);
        ETedad = (EditText)findViewById(R.id.editTextEdad);
        ETcolorFav=(EditText)findViewById(R.id.editTextColor);
        btn=(Button)findViewById(R.id.button1);
        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        btn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(value.getText().toString().length()<1){

            // out of range
            Toast.makeText(this, "please enter something", Toast.LENGTH_LONG).show();
        }else{
            pb.setVisibility(View.VISIBLE);
            new MyAsyncTask().execute(value.getText().toString());
        }


    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double>{

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result){
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Datos Enviados", Toast.LENGTH_LONG).show();
        }
        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);
        }

        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost("http://192.168.0.20/basededatos/recibeDatos.php");
            HttpPost httppost = new HttpPost("http://45.55.21.26/prueba/recibeDatos.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                final String AndroidId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
                BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
                String blueAdress = "";
                if (ba != null){
                    blueAdress = ba.getAddress();
                    if ( !ba.isEnabled() ) {
                        Intent turnOnIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
                        startActivityForResult( turnOnIntent, 1 );
                       // txtStatus.setText("Status: Bluetooth ACTIVADO");
                    }else{
                       // txtStatus.setText("Status: Bluetooth YA ESTA ACTIVADO");
                    }
                }else {
                    blueAdress = "no soportado";
                }

                nameValuePairs.add(new BasicNameValuePair("IDcel",AndroidId));
                nameValuePairs.add(new BasicNameValuePair("Bluetooth",blueAdress));
                nameValuePairs.add(new BasicNameValuePair("Nombre", value.getText().toString()));//valueIWantToSend));
                nameValuePairs.add(new BasicNameValuePair("Apellido", ETapellido.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("Edad", ETedad.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("ColorFav", ETcolorFav.getText().toString()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity ent = response.getEntity();
                String text = EntityUtils.toString(ent);
                Log.i("Esto recibi: ",text);
                String e = "no no";
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("log_tag", "Error in http connection", e);
                //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        }

    }
}
