package br.com.android.ppm.testecotacaoatual.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.android.ppm.testecotacaoatual.entity.CotacaoEntity

@Dao
interface CotacaoDAO {
    @Query("SELECT * from cotacao_list")
    fun getAllData(): LiveData<List<CotacaoEntity>>

    @Query("SELECT * from cotacao_list where idCotacao = :id")
    fun getById(id: Int) : CotacaoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<CotacaoEntity>)

    @Update
    suspend fun update(item: CotacaoEntity)

    @Delete
    suspend fun delete(item: CotacaoEntity)

    @Query("DELETE FROM cotacao_list")
    suspend fun deleteAllCotacao()
}