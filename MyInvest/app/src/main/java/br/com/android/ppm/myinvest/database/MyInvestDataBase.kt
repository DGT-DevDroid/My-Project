package br.com.android.ppm.myinvest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.android.ppm.myinvest.dao.AtivoDAO
import br.com.android.ppm.myinvest.entity.AtivoEntity


@Database(entities = [AtivoEntity::class], version = 1, exportSchema = false)
abstract class MyInvestDataBase: RoomDatabase() {
    abstract fun ativoDao(): AtivoDAO

    companion object {
        @Volatile
        private var INSTANCE: MyInvestDataBase? = null

        fun getInstance(context: Context): MyInvestDataBase {
            synchronized(this) {
                return INSTANCE ?: databaseBuilder(
                    context.applicationContext,
                    MyInvestDataBase::class.java,
                    "sample_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}