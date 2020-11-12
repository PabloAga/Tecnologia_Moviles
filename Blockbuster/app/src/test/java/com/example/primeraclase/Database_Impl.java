package com.example.primeraclase;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.primeraclase.persistance.Database;
import com.example.primeraclase.persistance.dao.CategoriaUsuarioDAO;

import java.util.HashMap;
import java.util.HashSet;


public final class Database_Impl extends Database {
  private volatile CategoriaUsuarioDAO _CategoriaUsuarioDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CategoriaUsuario` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `categoria1` INTEGER NOT NULL, `categoria2` INTEGER NOT NULL, `categoria3` INTEGER NOT NULL )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"76dfa3f767929ed219b87a340d00561f\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `CategoriaUsuario`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, Column> _columnsCategoriaUsuario = new HashMap<String, Column>(16);
        _columnsCategoriaUsuario.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCategoriaUsuario.put("categoria1", new TableInfo.Column("categoria1", "INTEGER", true, 0));
        _columnsCategoriaUsuario.put("categoria2", new TableInfo.Column("categoria2", "INTEGER", true, 0));
        _columnsCategoriaUsuario.put("categoria3", new TableInfo.Column("categoria3", "INTEGER", true, 0));
        final HashSet<ForeignKey> _foreignKeysCategoriaUsuario = new HashSet<ForeignKey>(0);
        final HashSet<Index> _indicesCategoriaUsuario = new HashSet<Index>(0);
        final TableInfo _infoCategoriaUsuario = new TableInfo("CategoriaUsuario", _columnsCategoriaUsuario, _foreignKeysCategoriaUsuario, _indicesCategoriaUsuario);
        final TableInfo _existingCategoriaUsuario = TableInfo.read(_db, "CategoriaUsuario");
        if (! _infoCategoriaUsuario.equals(_existingCategoriaUsuario)) {
          throw new IllegalStateException("Migration didn't properly handle CategoriaUsuario(com.example.primeraclase.model.CategoriaUsuario).\n"
                  + " Expected:\n" + _infoCategoriaUsuario + "\n"
                  + " Found:\n" + _existingCategoriaUsuario);
        }
      }
    }, "76dfa3f767929ed219b87a340d00561f", "ddb120a7a5764288d388676f5acb862d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "CategoriaUsuario");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `CategoriaUsuario`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }


  public CategoriaUsuarioDAO CategoriaUsuarioDAO() {
    if (_CategoriaUsuarioDAO != null) {
      return _CategoriaUsuarioDAO;
    } else {
      synchronized(this) {
        if(_CategoriaUsuarioDAO == null) {
          _CategoriaUsuarioDAO = new CategoriaUsuarioDAO_Impl(this);
        }
        return _CategoriaUsuarioDAO;
      }
    }
  }


  @Override
  public CategoriaUsuarioDAO climaDAO() {
    return null;
  }
}
