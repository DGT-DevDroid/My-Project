package br.com.correios.android.ppm.myinvest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AtivoDAO {
    @Query("SELECT * from my_ativo_list")
    fun getAllData(): LiveData<List<AtivoEntity>>
    @Query("SELECT * from my_ativo_list where ativoId = :id")
    fun getById(id: Int) : AtivoEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<AtivoEntity>)
    @Update
    suspend fun update(item:AtivoEntity)
    @Delete
    suspend fun delete(item:AtivoEntity)
    @Query("DELETE FROM my_ativo_list")
    suspend fun deleteAllAtivos()
}