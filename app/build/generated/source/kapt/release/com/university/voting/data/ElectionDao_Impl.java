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
public final class ElectionDao_Impl implements ElectionDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Election> __insertAdapterOfElection;

  private final EntityDeleteOrUpdateAdapter<Election> __updateAdapterOfElection;

  public ElectionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfElection = new EntityInsertAdapter<Election>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `elections` (`electionId`,`positionId`,`title`,`description`,`startDate`,`endDate`,`status`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Election entity) {
        if (entity.getElectionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getElectionId());
        }
        if (entity.getPositionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPositionId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getDescription());
        }
        statement.bindLong(5, entity.getStartDate());
        statement.bindLong(6, entity.getEndDate());
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getStatus());
        }
      }
    };
    this.__updateAdapterOfElection = new EntityDeleteOrUpdateAdapter<Election>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `elections` SET `electionId` = ?,`positionId` = ?,`title` = ?,`description` = ?,`startDate` = ?,`endDate` = ?,`status` = ? WHERE `electionId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Election entity) {
        if (entity.getElectionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getElectionId());
        }
        if (entity.getPositionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPositionId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getDescription());
        }
        statement.bindLong(5, entity.getStartDate());
        statement.bindLong(6, entity.getEndDate());
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getStatus());
        }
        if (entity.getElectionId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getElectionId());
        }
      }
    };
  }

  @Override
  public Object insert(final Election election, final Continuation<? super Unit> $completion) {
    if (election == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfElection.insert(_connection, election);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Election election, final Continuation<? super Unit> $completion) {
    if (election == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfElection.handle(_connection, election);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllElections(final Continuation<? super List<Election>> $completion) {
    final String _sql = "SELECT * FROM elections";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final List<Election> _result = new ArrayList<Election>();
        while (_stmt.step()) {
          final Election _item;
          final String _tmpElectionId;
          if (_stmt.isNull(_columnIndexOfElectionId)) {
            _tmpElectionId = null;
          } else {
            _tmpElectionId = _stmt.getText(_columnIndexOfElectionId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpStartDate;
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          final long _tmpEndDate;
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          _item = new Election(_tmpElectionId,_tmpPositionId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpStatus);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getOpenElections(final Continuation<? super List<Election>> $completion) {
    final String _sql = "SELECT * FROM elections WHERE status = 'open'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final List<Election> _result = new ArrayList<Election>();
        while (_stmt.step()) {
          final Election _item;
          final String _tmpElectionId;
          if (_stmt.isNull(_columnIndexOfElectionId)) {
            _tmpElectionId = null;
          } else {
            _tmpElectionId = _stmt.getText(_columnIndexOfElectionId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpStartDate;
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          final long _tmpEndDate;
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          _item = new Election(_tmpElectionId,_tmpPositionId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpStatus);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getExpiredOpenElections(final long now,
      final Continuation<? super List<Election>> $completion) {
    final String _sql = "SELECT * FROM elections WHERE endDate < ? AND status = 'open'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, now);
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final List<Election> _result = new ArrayList<Election>();
        while (_stmt.step()) {
          final Election _item;
          final String _tmpElectionId;
          if (_stmt.isNull(_columnIndexOfElectionId)) {
            _tmpElectionId = null;
          } else {
            _tmpElectionId = _stmt.getText(_columnIndexOfElectionId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpStartDate;
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          final long _tmpEndDate;
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          _item = new Election(_tmpElectionId,_tmpPositionId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpStatus);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getElection(final String electionId,
      final Continuation<? super Election> $completion) {
    final String _sql = "SELECT * FROM elections WHERE electionId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfStartDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startDate");
        final int _columnIndexOfEndDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endDate");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final Election _result;
        if (_stmt.step()) {
          final String _tmpElectionId;
          if (_stmt.isNull(_columnIndexOfElectionId)) {
            _tmpElectionId = null;
          } else {
            _tmpElectionId = _stmt.getText(_columnIndexOfElectionId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpStartDate;
          _tmpStartDate = _stmt.getLong(_columnIndexOfStartDate);
          final long _tmpEndDate;
          _tmpEndDate = _stmt.getLong(_columnIndexOfEndDate);
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          _result = new Election(_tmpElectionId,_tmpPositionId,_tmpTitle,_tmpDescription,_tmpStartDate,_tmpEndDate,_tmpStatus);
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
