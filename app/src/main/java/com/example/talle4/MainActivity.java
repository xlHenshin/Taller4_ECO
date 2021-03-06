package com.example.talle4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText ip1, ip2, ip3, ip4;
    private Button ping, host;
    private TextView ipMia;
    private ConstraintLayout pagina1;
    private boolean verification, ipType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip1 = findViewById(R.id.ip1);
        ip2 = findViewById(R.id.ip2);
        ip3 = findViewById(R.id.ip3);
        ip4 = findViewById(R.id.ip4);
        ping = findViewById(R.id.ping);
        host = findViewById(R.id.host);
        ipMia = findViewById(R.id.ipmia);
        pagina1 = findViewById(R.id.pagina1);

        verification = false;
        ipMia.setText("192.168.1.2");

        pingButton();
        hostButton();

    }

    public void pingButton() {

        ping.setOnClickListener(

                v -> {

                    verification = true;

                    String ipUno = ip1.getText().toString();
                    String ipDos = ip2.getText().toString();
                    String ipTres = ip3.getText().toString();
                    String ipCuatro = ip4.getText().toString();

                    String[] listaIp = new String[]{
                            ipUno,
                            ipDos,
                            ipTres,
                            ipCuatro
                    };
                    if (verification) {
                        for (int i = 0; i < listaIp.length; i++) {

                            if (listaIp[i] == null || listaIp[i].isEmpty()) {
                                Toast.makeText(this, "Ingrese todos los valores", Toast.LENGTH_LONG).show();
                                verification = false;
                            }
                        }
                    }


                    if (verification == true) {
                        String ipCompleta = ipUno + "." + ipDos + "." + ipTres + "." + ipCuatro;
                        Log.e(">>>", "IP: " + ipCompleta);

                        Intent i = new Intent(this, ListPage.class);
                        ipType = true;
                        i.putExtra("ipType", ipType);
                        i.putExtra("ipusuario", ipCompleta);
                        startActivity(i);

                        ip1.setText("");
                        ip2.setText("");
                        ip3.setText("");
                        ip4.setText("");
                    }


                }
        );

    }

    public void hostButton() {

        host.setOnClickListener(

                v -> {

                    verification = true;

                    String ipUno = ip1.getText().toString();
                    String ipDos = ip2.getText().toString();
                    String ipTres = ip3.getText().toString();

                    String[] listaIp = new String[]{
                            ipUno,
                            ipDos,
                            ipTres
                    };

                    if (verification) {

                        for (int i = 0; i < listaIp.length; i++) {

                            if (listaIp[i] == null || listaIp[i].isEmpty()) {
                                Toast.makeText(this, "Ingrese todos los valores", Toast.LENGTH_LONG).show();
                                verification = false;
                            }
                        }
                    }

                    if (verification == true) {

                        String ipCompleta = ipUno + "." + ipDos + "." + ipTres;
                        Log.e(">>>", "IP: " + ipCompleta);

                        Intent i = new Intent(this, ListPage.class);
                        ipType = false;
                        i.putExtra("ipType", ipType);
                        i.putExtra("ipusuario", ipCompleta);
                        startActivity(i);
                        ip1.setText("");
                        ip2.setText("");
                        ip3.setText("");
                        ip4.setText("");

                    }
                }
        );

    }
}