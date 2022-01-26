package br.com.android.ppm.controleconsumodeagua.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;

@Dao
public interface ConsumoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long adiciona(ConsumoEntity consumoEntity);

    @Query("SELECT * FROM ConsumoEntity ORDER BY data_consumo")
    List<ConsumoEntity> lista();

//    @Query("SELECT * FROM ConsumoEntity WHERE numero_palete=:numeroPalete")
//    PaleteEntity getPalete(Long numeroPalete);
//
//    @Update
//    void atualiza(PaleteEntity paleteEntity);
//
//    @Delete
//    void remove(PaleteEntity paleteEntity);
//
//    @Insert
//    long[] insertAll(List<PaleteEntity> listaPalete);
//
//    @Query("DELETE FROM ConsumoEntity WHERE numero_palete=:numeroPalete")
//    void apagaPalete(String numeroPalete);
//
//    @Query("DELETE FROM ConsumoEntity")
//    void apagaTudo();
}