package com.example.primeraclase;

import android.database.Cursor;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.example.primeraclase.model.CategoriaUsuario;
import com.example.primeraclase.persistance.dao.CategoriaUsuarioDAO;

@SuppressWarnings("unchecked")
public final class CategoriaUsuarioDAO_Impl implements CategoriaUsuarioDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCategoriaUsuario;

  private final SharedSQLiteStatement __preparedStmtOfEliminar;


  public CategoriaUsuarioDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategoriaUsuario = new EntityInsertionAdapter<CategoriaUsuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CategoriaUsuario`(`id`,`categoria1`,`categoria2`,`categoria3`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CategoriaUsuario value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getCategoria1() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCategoria1());
        }
        if (value.getCategoria2() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getCategoria2());
        }
        if (value.getCategoria3() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getCategoria3());
        }

      }
    };
    this.__preparedStmtOfEliminar = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CategoriaUsuario WHERE id == ?";
        return _query;
      }
    };


  }

  @Override
  public void insertar(CategoriaUsuario CategoriaUsuario) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategoriaUsuario.insert(CategoriaUsuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void eliminar(Integer idCategoriaUsuario) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfEliminar.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (idCategoriaUsuario == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, idCategoriaUsuario);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfEliminar.release(_stmt);
    }
  }

  @Override
  public CategoriaUsuario getLastActual() {
    final String _sql = "SELECT * FROM CategoriaUsuario";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfcategoria1 = _cursor.getColumnIndexOrThrow("categoria1");
      final int _cursorIndexOfcategoria2 = _cursor.getColumnIndexOrThrow("categoria2");
      final int _cursorIndexOfcategoria3 = _cursor.getColumnIndexOrThrow("categoria3");

      final CategoriaUsuario _result;

      if(_cursor.moveToFirst()) {
        _result = new CategoriaUsuario();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _result.setId(_tmpId);

        final Integer _tmpcategoria1;
        if (_cursor.isNull(_cursorIndexOfcategoria1)) {
          _tmpcategoria1 = null;
        } else {
          _tmpcategoria1 = _cursor.getInt(_cursorIndexOfcategoria1);
        }
        _result.setCategoria1(_tmpcategoria1);
        final Integer _tmpcategoria2;
        if (_cursor.isNull(_cursorIndexOfcategoria2)) {
          _tmpcategoria2 = null;
        } else {
          _tmpcategoria2 = _cursor.getInt(_cursorIndexOfcategoria1);
        }
        _result.setCategoria2(_tmpcategoria2);
        final Integer _tmpcategoria3;
        if (_cursor.isNull(_cursorIndexOfcategoria3)) {
          _tmpcategoria3 = null;
        } else {
          _tmpcategoria3 = _cursor.getInt(_cursorIndexOfcategoria3);
        }
        _result.setCategoria3(_tmpcategoria3);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }


}
