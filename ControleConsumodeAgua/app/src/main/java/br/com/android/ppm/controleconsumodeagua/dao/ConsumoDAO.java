package br.com.android.ppm.controleconsumodeagua.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;

@Dao
public interface ConsumoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long adiciona(ConsumoEntity consumoEntity);

    @Query("SELECT * FROM ConsumoEntity ORDER BY qtd")
    List<ConsumoEntity> lista();

    @Query("SELECT Max(qtd) FROM ConsumoEntity")
    Double maior();

    @Query("SELECT Min(qtd) FROM ConsumoEntity")
    Double menor();

    @Query("SELECT Max(data_consumo) FROM ConsumoEntity")
    int maiorData();

    @Query("SELECT Min(data_consumo) FROM ConsumoEntity")
    int menorData();

    @Query("SELECT * FROM ConsumoEntity WHERE id_consumo=:idConsumo")
    ConsumoEntity getIdConsumo(Long idConsumo);
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

    @Query("DELETE FROM ConsumoEntity WHERE id_consumo=:idConsumo")
    void delete(int idConsumo);

    @Query("DELETE FROM ConsumoEntity")
    void apagaTudo();
}