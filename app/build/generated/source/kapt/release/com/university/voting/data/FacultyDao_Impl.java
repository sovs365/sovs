package com.university.voting.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class FacultyDao_Impl implements FacultyDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Faculty> __insertAdapterOfFaculty;

  private final EntityDeleteOrUpdateAdapter<Faculty> __deleteAdapterOfFaculty;

  private final EntityDeleteOrUpdateAdapter<Faculty> __updateAdapterOfFaculty;

  public FacultyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfFaculty = new EntityInsertAdapter<Faculty>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `faculties` (`facultyId`,`name`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Faculty entity) {
        if (entity.getFacultyId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getFacultyId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
      }
    };
    this.__deleteAdapterOfFaculty = new EntityDeleteOrUpdateAdapter<Faculty>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `faculties` WHERE `facultyId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Faculty entity) {
        if (entity.getFacultyId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getFacultyId());
        }
      }
    };
    this.__updateAdapterOfFaculty = new EntityDeleteOrUpdateAdapter<Faculty>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `faculties` SET `facultyId` = ?,`name` = ? WHERE `facultyId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Faculty entity) {
        if (entity.getFacultyId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getFacultyId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getFacultyId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getFacultyId());
        }
      }
    };
  }

  @Override
  public Object insert(final Faculty faculty, final Continuation<? super Unit> $completion) {
    if (faculty == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfFaculty.insert(_connection, faculty);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Faculty faculty, final Continuation<? super Unit> $completion) {
    if (faculty == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfFaculty.handle(_connection, faculty);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Faculty faculty, final Continuation<? super Unit> $completion) {
    if (faculty == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfFaculty.handle(_connection, faculty);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Faculty>> $completion) {
    final String _sql = "SELECT * FROM faculties";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final List<Faculty> _result = new ArrayList<Faculty>();
        while (_stmt.step()) {
          final Faculty _item;
          final String _tmpFacultyId;
          if (_stmt.isNull(_columnIndexOfFacultyId)) {
            _tmpFacultyId = null;
          } else {
            _tmpFacultyId = _stmt.getText(_columnIndexOfFacultyId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item = new Faculty(_tmpFacultyId,_tmpName);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
