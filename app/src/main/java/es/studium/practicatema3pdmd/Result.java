package es.studium.practicatema3pdmd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    TextView txtEdad, txtSigno;
    ImageView imgSigno;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

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

        txtEdad = findViewById(R.id.textViewEdad);
        txtSigno = findViewById(R.id.textViewSigno);
        imgSigno = findViewById(R.id.imageView);
        btnVolver = findViewById(R.id.btnVolver);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int edad = extras.getInt("edad");
            String signo = extras.getString("signo");

            txtEdad.setText("Tienes " + edad + " años");
            txtSigno.setText("Eres " + signo);

            switch (signo) {
                case "Aries":
                    imgSigno.setImageResource(R.drawable.aries);
                    break;
                case "Tauro":
                    imgSigno.setImageResource(R.drawable.tauro);
                    break;
                case "Géminis":
                    imgSigno.setImageResource(R.drawable.gemini);
                    break;
                case "Cáncer":
                    imgSigno.setImageResource(R.drawable.cancer);
                    break;
                case "Leo":
                    imgSigno.setImageResource(R.drawable.leo);
                    break;
                case "Virgo":
                    imgSigno.setImageResource(R.drawable.virgo);
                    break;
                case "Libra":
                    imgSigno.setImageResource(R.drawable.libra);
                    break;
                case "Escorpio":
                    imgSigno.setImageResource(R.drawable.escorpio);
                    break;
                case "Sagitario":
                    imgSigno.setImageResource(R.drawable.sagitario);
                    break;
                case "Capricornio":
                    imgSigno.setImageResource(R.drawable.capricornio);
                    break;
                case "Acuario":
                    imgSigno.setImageResource(R.drawable.acuario);
                    break;
                case "Piscis":
                    imgSigno.setImageResource(R.drawable.piscis);
                    break;
            }
        }

        // Listener del botón Volver
        btnVolver.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}