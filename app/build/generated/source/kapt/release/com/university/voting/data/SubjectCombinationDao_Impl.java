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
public final class SubjectCombinationDao_Impl implements SubjectCombinationDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<SubjectCombination> __insertAdapterOfSubjectCombination;

  private final EntityDeleteOrUpdateAdapter<SubjectCombination> __deleteAdapterOfSubjectCombination;

  public SubjectCombinationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfSubjectCombination = new EntityInsertAdapter<SubjectCombination>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `subject_combinations` (`id`,`courseId`,`name`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final SubjectCombination entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getCourseId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getCourseId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getName());
        }
      }
    };
    this.__deleteAdapterOfSubjectCombination = new EntityDeleteOrUpdateAdapter<SubjectCombination>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `subject_combinations` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final SubjectCombination entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final SubjectCombination item,
      final Continuation<? super Unit> $completion) {
    if (item == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfSubjectCombination.insert(_connection, item);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final SubjectCombination item,
      final Continuation<? super Unit> $completion) {
    if (item == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfSubjectCombination.handle(_connection, item);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getByCourse(final String courseId,
      final Continuation<? super List<SubjectCombination>> $completion) {
    final String _sql = "SELECT * FROM subject_combinations WHERE courseId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (courseId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, courseId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final List<SubjectCombination> _result = new ArrayList<SubjectCombination>();
        while (_stmt.step()) {
          final SubjectCombination _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item = new SubjectCombination(_tmpId,_tmpCourseId,_tmpName);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<SubjectCombination>> $completion) {
    final String _sql = "SELECT * FROM subject_combinations";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final List<SubjectCombination> _result = new ArrayList<SubjectCombination>();
        while (_stmt.step()) {
          final SubjectCombination _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item = new SubjectCombination(_tmpId,_tmpCourseId,_tmpName);
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
