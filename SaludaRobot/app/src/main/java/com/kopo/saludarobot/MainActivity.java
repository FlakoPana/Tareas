package com.kopo.saludarobot;

import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

public class MainActivity extends ActionBarActivity {
    Button botonEnviar;
    EditText colorFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorFavorito = (EditText)findViewById(R.id.editTextColorFav);
        botonEnviar= (Button)findViewById(R.id.botonEnviar);
        final String AndroidId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        final httpHandler handler = new httpHandler(this);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                handler.execute("http://localhost/basededatos/recibeDatos.php");
                //httpHandler handler = new httpHandler();
               // String txt = handler.post("http://localhost/basededatos/recibeDatos.php");
               // new httpHandler().execute(new String[]{"http://localhost/basededatos/recibeDatos.php"} );
               // Toast.makeText(getBaseContext(), httpHandler.getTexto2(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
