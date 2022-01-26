package br.com.android.ppm.myinvest.database

import android.content.Context
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.android.ppm.myinvest.dao.AtivoDAO
import br.com.android.ppm.myinvest.dao.CotacaoDAO
import br.com.android.ppm.myinvest.entity.AtivoEntity
import br.com.android.ppm.myinvest.entity.CotacaoEntity


@Database(entities = [AtivoEntity::class, CotacaoEntity::class], version = 1, exportSchema = false)
abstract class MyInvestDataBase: RoomDatabase() {
    abstract fun ativoDao(): AtivoDAO
    abstract fun cotacaoDAO(): CotacaoDAO

    companion object {
        @Volatile
        private var INSTANCE: MyInvestDataBase? = null

        fun getInstance(context: Context): MyInvestDataBase {
            synchronized(this) {
                return INSTANCE ?: databaseBuilder(
                    context.applicationContext,
                    MyInvestDataBase::class.java,
                    "myinvest_db"
                ).allowMainThreadQueries()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}