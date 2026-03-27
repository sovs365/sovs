package com.university.voting.data;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
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
public final class VoteDao_Impl implements VoteDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Vote> __insertAdapterOfVote;

  public VoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfVote = new EntityInsertAdapter<Vote>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `votes` (`voteId`,`electionId`,`positionId`,`voterId`,`candidateId`,`timestamp`,`hash`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Vote entity) {
        if (entity.getVoteId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getVoteId());
        }
        if (entity.getElectionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getElectionId());
        }
        if (entity.getPositionId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getPositionId());
        }
        if (entity.getVoterId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getVoterId());
        }
        if (entity.getCandidateId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCandidateId());
        }
        statement.bindLong(6, entity.getTimestamp());
        if (entity.getHash() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getHash());
        }
      }
    };
  }

  @Override
  public Object insert(final Vote vote, final Continuation<? super Unit> $completion) {
    if (vote == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfVote.insert(_connection, vote);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object hasVoted(final String electionId, final String positionId, final String voterId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM votes WHERE electionId = ? AND positionId = ? AND voterId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        _argIndex = 2;
        if (positionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, positionId);
        }
        _argIndex = 3;
        if (voterId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, voterId);
        }
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getVoteCounts(final String electionId, final String positionId,
      final Continuation<? super List<VoteCount>> $completion) {
    final String _sql = "SELECT candidateId, COUNT(*) as count FROM votes WHERE electionId = ? AND positionId = ? GROUP BY candidateId";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        _argIndex = 2;
        if (positionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, positionId);
        }
        final int _columnIndexOfCandidateId = 0;
        final int _columnIndexOfCount = 1;
        final List<VoteCount> _result = new ArrayList<VoteCount>();
        while (_stmt.step()) {
          final VoteCount _item;
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final int _tmpCount;
          _tmpCount = (int) (_stmt.getLong(_columnIndexOfCount));
          _item = new VoteCount(_tmpCandidateId,_tmpCount);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getVotesForElection(final String electionId,
      final Continuation<? super List<Vote>> $completion) {
    final String _sql = "SELECT * FROM votes WHERE electionId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        final int _columnIndexOfVoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "voteId");
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfVoterId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "voterId");
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "hash");
        final List<Vote> _result = new ArrayList<Vote>();
        while (_stmt.step()) {
          final Vote _item;
          final String _tmpVoteId;
          if (_stmt.isNull(_columnIndexOfVoteId)) {
            _tmpVoteId = null;
          } else {
            _tmpVoteId = _stmt.getText(_columnIndexOfVoteId);
          }
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
          final String _tmpVoterId;
          if (_stmt.isNull(_columnIndexOfVoterId)) {
            _tmpVoterId = null;
          } else {
            _tmpVoterId = _stmt.getText(_columnIndexOfVoterId);
          }
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final String _tmpHash;
          if (_stmt.isNull(_columnIndexOfHash)) {
            _tmpHash = null;
          } else {
            _tmpHash = _stmt.getText(_columnIndexOfHash);
          }
          _item = new Vote(_tmpVoteId,_tmpElectionId,_tmpPositionId,_tmpVoterId,_tmpCandidateId,_tmpTimestamp,_tmpHash);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAllVotes(final Continuation<? super List<Vote>> $completion) {
    final String _sql = "SELECT * FROM votes";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfVoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "voteId");
        final int _columnIndexOfElectionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "electionId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfVoterId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "voterId");
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "hash");
        final List<Vote> _result = new ArrayList<Vote>();
        while (_stmt.step()) {
          final Vote _item;
          final String _tmpVoteId;
          if (_stmt.isNull(_columnIndexOfVoteId)) {
            _tmpVoteId = null;
          } else {
            _tmpVoteId = _stmt.getText(_columnIndexOfVoteId);
          }
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
          final String _tmpVoterId;
          if (_stmt.isNull(_columnIndexOfVoterId)) {
            _tmpVoterId = null;
          } else {
            _tmpVoterId = _stmt.getText(_columnIndexOfVoterId);
          }
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final String _tmpHash;
          if (_stmt.isNull(_columnIndexOfHash)) {
            _tmpHash = null;
          } else {
            _tmpHash = _stmt.getText(_columnIndexOfHash);
          }
          _item = new Vote(_tmpVoteId,_tmpElectionId,_tmpPositionId,_tmpVoterId,_tmpCandidateId,_tmpTimestamp,_tmpHash);
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
