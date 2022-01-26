package br.com.android.ppm.testecotacaoatual.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.android.ppm.testecotacaoatual.dao.CotacaoDAO
import br.com.android.ppm.testecotacaoatual.entity.CotacaoEntity



@Database(entities = [CotacaoEntity::class], version = 1, exportSchema = false)
abstract class MyInvestDataBase: RoomDatabase() {
    abstract fun cotacaoDAO(): CotacaoDAO

    companion object {
        @Volatile
        private var INSTANCE: MyInvestDataBase? = null

        fun getInstance(context: Context): MyInvestDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
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