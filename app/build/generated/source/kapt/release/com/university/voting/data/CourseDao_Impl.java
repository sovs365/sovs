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
public final class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Course> __insertAdapterOfCourse;

  private final EntityDeleteOrUpdateAdapter<Course> __deleteAdapterOfCourse;

  private final EntityDeleteOrUpdateAdapter<Course> __updateAdapterOfCourse;

  public CourseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCourse = new EntityInsertAdapter<Course>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `courses` (`courseId`,`facultyId`,`name`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Course entity) {
        if (entity.getCourseId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCourseId());
        }
        if (entity.getFacultyId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getFacultyId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getName());
        }
      }
    };
    this.__deleteAdapterOfCourse = new EntityDeleteOrUpdateAdapter<Course>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `courses` WHERE `courseId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Course entity) {
        if (entity.getCourseId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCourseId());
        }
      }
    };
    this.__updateAdapterOfCourse = new EntityDeleteOrUpdateAdapter<Course>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `courses` SET `courseId` = ?,`facultyId` = ?,`name` = ? WHERE `courseId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Course entity) {
        if (entity.getCourseId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCourseId());
        }
        if (entity.getFacultyId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getFacultyId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getName());
        }
        if (entity.getCourseId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCourseId());
        }
      }
    };
  }

  @Override
  public Object insert(final Course course, final Continuation<? super Unit> $completion) {
    if (course == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfCourse.insert(_connection, course);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Course course, final Continuation<? super Unit> $completion) {
    if (course == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfCourse.handle(_connection, course);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Course course, final Continuation<? super Unit> $completion) {
    if (course == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfCourse.handle(_connection, course);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getByFaculty(final String facultyId,
      final Continuation<? super List<Course>> $completion) {
    final String _sql = "SELECT * FROM courses WHERE facultyId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (facultyId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, facultyId);
        }
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final List<Course> _result = new ArrayList<Course>();
        while (_stmt.step()) {
          final Course _item;
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
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
          _item = new Course(_tmpCourseId,_tmpFacultyId,_tmpName);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Course>> $completion) {
    final String _sql = "SELECT * FROM courses";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final List<Course> _result = new ArrayList<Course>();
        while (_stmt.step()) {
          final Course _item;
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
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
          _item = new Course(_tmpCourseId,_tmpFacultyId,_tmpName);
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
