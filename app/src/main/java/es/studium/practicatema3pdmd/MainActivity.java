package es.studium.practicatema3pdmd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSeleccionarFecha, btnAceptar;
    int dia = -1, mes = -1, anio = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                new androidx.core.view.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(android.view.View v, WindowInsetsCompat insets) {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        return insets;
                    }
                }
        );

        btnSeleccionarFecha = findViewById(R.id.btnFecha);
        btnAceptar = findViewById(R.id.btnAceptar);

        // Listener del botón Seleccionar Fecha
        btnSeleccionarFecha.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Calendar calendario = Calendar.getInstance();
                int añoActual = calendario.get(Calendar.YEAR);
                int mesActual = calendario.get(Calendar.MONTH);
                int diaActual = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogoFecha = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dia = dayOfMonth;
                                mes = month + 1;
                                anio = year;
                                btnSeleccionarFecha.setText(dayOfMonth + "/" + mes + "/" + year);
                            }
                        }, añoActual, mesActual, diaActual);
                dialogoFecha.show();
            }
        });

        // Listener del botón Aceptar
        btnAceptar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Calendar hoy = Calendar.getInstance();
                Calendar fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.set(anio, mes - 1, dia); // mes - 1 porque Calendar usa 0-11

                // Validación: año mayor a 1900 y fecha no futura
                if (anio > 1900 && !fechaSeleccionada.after(hoy)) {
                    int edad = calcularEdad(anio, mes, dia);
                    String signo = obtenerSignoZodiaco(dia, mes);

                    Intent intent = new Intent(MainActivity.this, Result.class);
                    intent.putExtra("edad", edad);
                    intent.putExtra("signo", signo);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.TextError), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int calcularEdad(int year, int month, int day) {
        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.set(year, month - 1, day);

        int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }
        return edad;
    }

    private String obtenerSignoZodiaco(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Aries";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Tauro";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Géminis";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Cáncer";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Leo";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Virgo";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Libra";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Escorpio";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Sagitario";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Capricornio";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Acuario";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "Piscis";
        return "Desconocido";
    }
}