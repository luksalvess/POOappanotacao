package anotacoes.lucas.anotacoes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class AtualizarAnotacao {
    public void atualizar(Anotacao anotacao){
        DatabaseReference notasReferencia = FirebaseDatabase.getInstance().getReference()
                .child("notas").child(anotacao.getUid());

        Map<String,Object> map = new HashMap<>();
        map.put("anotacao",anotacao.getValor());

        notasReferencia.updateChildren(map);
    }


}
