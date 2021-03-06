package com.example.talle4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ListPage extends AppCompatActivity {

    private String ipusuario;
    private boolean ipType;
    private ListView listaIp;
    private Button regresarBoton;
    private ArrayList<String> pingLista = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pingLista);

        listaIp= findViewById(R.id.lista);
        regresarBoton= findViewById(R.id.regresar);

        ipType=getIntent().getExtras().getBoolean("ipType");
        ipusuario= getIntent().getExtras().getString("ipusuario");

        if (ipType==true){

            ping();
        }else{

            host();
        }

        regresarBoton.setOnClickListener(

                v->{

                    finish();
                }

        );



    }

    public void ping() {

        new Thread(

                () -> {

                    try {
                        String ip = ipusuario; //Autoreferencia
                        String ips;
                        InetAddress inet = InetAddress.getByName(ip);

                        for (int i = 0; i <= 5; i++) {
                            boolean conectado = inet.isReachable(1000);
                            runOnUiThread(

                                    ()->{

                                        pingLista.add("Conectado: " + conectado);
                                        adapter.notifyDataSetChanged();
                                        listaIp.setAdapter(adapter);

                                    }

                            );
                            //listaIp.setText("ola");
                            Log.e(">>>", "Conectado: " + conectado);
                        }

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        ).start();
    }

    public void host(){

        new Thread(

                ()->{

                    String base = "192.168.1.";

                    for (int i = 0; i<255; i++){
                        String ip = base+i;

                        try {
                            InetAddress inet = InetAddress.getByName(ip);
                            boolean conectado = inet.isReachable(1000);
                            if (conectado){

                                runOnUiThread(

                                        ()->{

                                            pingLista.add("IP's: " + ip);
                                            adapter.notifyDataSetChanged();
                                            listaIp.setAdapter(adapter);

                                        }
                                );
                                Log.e(">>>", "Conectado: " + ip);
                                //listaIp.setText("");
                            }
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
    }
}