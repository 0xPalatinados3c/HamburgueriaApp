package com.Gustavo.hamburgueria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // Declaração dos elementos
    private ImageView logoImage, menuImage, extraImage;
    private Button confirmButton, cancelButton;
    private RadioGroup radioGroup1, radioGroup2;
    private Switch switchOption1, switchOption2;
    private ProgressBar progressBar;
    private TextInputEditText nameInput, addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos elementos
        logoImage = findViewById(R.id.logoImage);
        menuImage = findViewById(R.id.menuImage);
        extraImage = findViewById(R.id.extraImage);

        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);

        switchOption1 = findViewById(R.id.switchOption1);
        switchOption2 = findViewById(R.id.switchOption2);

        progressBar = findViewById(R.id.progressBar);

        nameInput = findViewById(R.id.nameInput);
        addressInput = findViewById(R.id.addressInput);

        // Configurar eventos dos botões
        setupButtons();
    }

    private void setupButtons() {
        // Botão Confirmar Pedido
        confirmButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            String address = addressInput.getText().toString();

            // Obter opções selecionadas
            int selectedOption1 = radioGroup1.getCheckedRadioButtonId();
            int selectedOption2 = radioGroup2.getCheckedRadioButtonId();

            RadioButton option1 = findViewById(selectedOption1);
            RadioButton option2 = findViewById(selectedOption2);

            boolean addSauce = switchOption1.isChecked();
            boolean noOnions = switchOption2.isChecked();

            // Verificar se todas as informações foram preenchidas
            if (name.isEmpty() || address.isEmpty() || selectedOption1 == -1 || selectedOption2 == -1) {
                Toast.makeText(this, "Por favor, preencha todas as informações!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mostrar ProgressBar
            progressBar.setVisibility(View.VISIBLE);

            // Simular carregamento
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);

                // Exibir AlertDialog com resumo do pedido
                String summary = "Nome: " + name +
                        "\nEndereço: " + address +
                        "\nOpção 1: " + option1.getText().toString() +
                        "\nOpção 2: " + option2.getText().toString() +
                        "\nMolho Adicional: " + (addSauce ? "Sim" : "Não") +
                        "\nSem Cebola: " + (noOnions ? "Sim" : "Não");

                new AlertDialog.Builder(this)
                        .setTitle("Resumo do Pedido")
                        .setMessage(summary)
                        .setPositiveButton("OK", null)
                        .show();

            }, 2000); // Simula 2 segundos de carregamento
        });

        // Botão Cancelar Pedido
        cancelButton.setOnClickListener(view -> {
            // Exibir Toast informando que o pedido foi cancelado
            Toast.makeText(this, "Pedido cancelado!", Toast.LENGTH_SHORT).show();

            // Limpar os campos
            nameInput.setText("");
            addressInput.setText("");
            radioGroup1.clearCheck();
            radioGroup2.clearCheck();
            switchOption1.setChecked(false);
            switchOption2.setChecked(false);
        });
    }
}
