package br.com.android.ppm.myinvest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.android.ppm.myinvest.entity.AtivoEntity

@Dao
interface AtivoDAO {
    @Query("SELECT * from my_ativo_list")
    fun getAllData(): LiveData<List<AtivoEntity>>
    @Query("SELECT * from my_ativo_list where ativoId = :id")
    fun getById(id: Int) : AtivoEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<AtivoEntity>)
    @Update
    suspend fun update(item: AtivoEntity)
    @Delete
    suspend fun delete(item: AtivoEntity)
    @Query("DELETE FROM my_ativo_list")
    suspend fun deleteAllAtivos()
}