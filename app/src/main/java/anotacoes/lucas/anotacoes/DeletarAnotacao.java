package anotacoes.lucas.anotacoes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DeletarAnotacao {
    public void deletar(Anotacao anotacao_item){
        DatabaseReference notasReferencia = FirebaseDatabase.getInstance().getReference()
                .child("notas").child(anotacao_item.getUid());


        notasReferencia.setValue(null);

    }
}
