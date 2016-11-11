package anotacoes.lucas.anotacoes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SalvarAnotacao {
    public void novoRegistro(String texto){
        DatabaseReference notasReferencia = FirebaseDatabase.getInstance().getReference()
                .child("notas");

        DatabaseReference novoRegistro = notasReferencia.push();
        novoRegistro.child("anotacao").setValue(texto);
    }
}
