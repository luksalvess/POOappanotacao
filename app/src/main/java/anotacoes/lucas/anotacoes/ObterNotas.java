package anotacoes.lucas.anotacoes;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ObterNotas {
    public void todos(final OnObterAnotacoesListener listener){

        DatabaseReference notasReferencia = FirebaseDatabase.getInstance().getReference()
                .child("notas");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Anotacao> list = new ArrayList<>();

                Iterable<DataSnapshot> i = dataSnapshot.getChildren();

                while(i.iterator().hasNext()){

                    DataSnapshot d = i.iterator().next();

                    Anotacao p = new Anotacao();

                    p.setValor(d.child("anotacao").getValue().toString());
                    p.setUid(d.getKey());

                    list.add(p);
                }

                if(listener != null){

                    listener.onAnotacoesObtidas(list);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG", "deu erro ao buscar anotações do firebase", databaseError.toException());

            }
        };

        notasReferencia.addListenerForSingleValueEvent(postListener);
    }
    public interface OnObterAnotacoesListener{
        void onAnotacoesObtidas(List<Anotacao> lista);
    }
}
