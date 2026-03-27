package com.university.voting.data;

import androidx.annotation.NonNull;
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
public final class PositionDao_Impl implements PositionDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Position> __insertAdapterOfPosition;

  public PositionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfPosition = new EntityInsertAdapter<Position>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `positions` (`positionId`,`name`,`type`,`facultyId`,`courseId`,`subjectCombinationId`,`yearOfStudy`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Position entity) {
        if (entity.getPositionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getPositionId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getType());
        }
        if (entity.getFacultyId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getFacultyId());
        }
        if (entity.getCourseId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCourseId());
        }
        if (entity.getSubjectCombinationId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getSubjectCombinationId());
        }
        if (entity.getYearOfStudy() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getYearOfStudy());
        }
      }
    };
  }

  @Override
  public Object insert(final Position position, final Continuation<? super Unit> $completion) {
    if (position == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfPosition.insert(_connection, position);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getPositionsForElection(final String electionId,
      final Continuation<? super List<Position>> $completion) {
    final String _sql = "SELECT p.* FROM positions p INNER JOIN elections e ON p.positionId = e.positionId WHERE e.electionId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfSubjectCombinationId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombinationId");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final List<Position> _result = new ArrayList<Position>();
        while (_stmt.step()) {
          final Position _item;
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpFacultyId;
          if (_stmt.isNull(_columnIndexOfFacultyId)) {
            _tmpFacultyId = null;
          } else {
            _tmpFacultyId = _stmt.getText(_columnIndexOfFacultyId);
          }
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
          final String _tmpSubjectCombinationId;
          if (_stmt.isNull(_columnIndexOfSubjectCombinationId)) {
            _tmpSubjectCombinationId = null;
          } else {
            _tmpSubjectCombinationId = _stmt.getText(_columnIndexOfSubjectCombinationId);
          }
          final String _tmpYearOfStudy;
          if (_stmt.isNull(_columnIndexOfYearOfStudy)) {
            _tmpYearOfStudy = null;
          } else {
            _tmpYearOfStudy = _stmt.getText(_columnIndexOfYearOfStudy);
          }
          _item = new Position(_tmpPositionId,_tmpName,_tmpType,_tmpFacultyId,_tmpCourseId,_tmpSubjectCombinationId,_tmpYearOfStudy);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAllPositions(final Continuation<? super List<Position>> $completion) {
    final String _sql = "SELECT * FROM positions";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfSubjectCombinationId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombinationId");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final List<Position> _result = new ArrayList<Position>();
        while (_stmt.step()) {
          final Position _item;
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpFacultyId;
          if (_stmt.isNull(_columnIndexOfFacultyId)) {
            _tmpFacultyId = null;
          } else {
            _tmpFacultyId = _stmt.getText(_columnIndexOfFacultyId);
          }
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
          final String _tmpSubjectCombinationId;
          if (_stmt.isNull(_columnIndexOfSubjectCombinationId)) {
            _tmpSubjectCombinationId = null;
          } else {
            _tmpSubjectCombinationId = _stmt.getText(_columnIndexOfSubjectCombinationId);
          }
          final String _tmpYearOfStudy;
          if (_stmt.isNull(_columnIndexOfYearOfStudy)) {
            _tmpYearOfStudy = null;
          } else {
            _tmpYearOfStudy = _stmt.getText(_columnIndexOfYearOfStudy);
          }
          _item = new Position(_tmpPositionId,_tmpName,_tmpType,_tmpFacultyId,_tmpCourseId,_tmpSubjectCombinationId,_tmpYearOfStudy);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getPositionById(final String positionId,
      final Continuation<? super Position> $completion) {
    final String _sql = "SELECT * FROM positions WHERE positionId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (positionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, positionId);
        }
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfFacultyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "facultyId");
        final int _columnIndexOfCourseId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "courseId");
        final int _columnIndexOfSubjectCombinationId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombinationId");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final Position _result;
        if (_stmt.step()) {
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpFacultyId;
          if (_stmt.isNull(_columnIndexOfFacultyId)) {
            _tmpFacultyId = null;
          } else {
            _tmpFacultyId = _stmt.getText(_columnIndexOfFacultyId);
          }
          final String _tmpCourseId;
          if (_stmt.isNull(_columnIndexOfCourseId)) {
            _tmpCourseId = null;
          } else {
            _tmpCourseId = _stmt.getText(_columnIndexOfCourseId);
          }
          final String _tmpSubjectCombinationId;
          if (_stmt.isNull(_columnIndexOfSubjectCombinationId)) {
            _tmpSubjectCombinationId = null;
          } else {
            _tmpSubjectCombinationId = _stmt.getText(_columnIndexOfSubjectCombinationId);
          }
          final String _tmpYearOfStudy;
          if (_stmt.isNull(_columnIndexOfYearOfStudy)) {
            _tmpYearOfStudy = null;
          } else {
            _tmpYearOfStudy = _stmt.getText(_columnIndexOfYearOfStudy);
          }
          _result = new Position(_tmpPositionId,_tmpName,_tmpType,_tmpFacultyId,_tmpCourseId,_tmpSubjectCombinationId,_tmpYearOfStudy);
        } else {
          _result = null;
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
