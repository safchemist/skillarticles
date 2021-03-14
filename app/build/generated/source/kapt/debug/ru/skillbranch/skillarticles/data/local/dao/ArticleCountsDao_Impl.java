package ru.skillbranch.skillarticles.data.local.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import ru.skillbranch.skillarticles.data.local.Converters;
import ru.skillbranch.skillarticles.data.local.entities.ArticleCounts;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleCountsDao_Impl implements ArticleCountsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleCounts> __insertionAdapterOfArticleCounts;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ArticleCounts> __updateAdapterOfArticleCounts;

  private final SharedSQLiteStatement __preparedStmtOfIncrementLike;

  private final SharedSQLiteStatement __preparedStmtOfDecrementLike;

  private final SharedSQLiteStatement __preparedStmtOfIncrementCommentsCount;

  public ArticleCountsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleCounts = new EntityInsertionAdapter<ArticleCounts>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `article_counts` (`article_id`,`likes`,`comments`,`read_duration`,`updated_at`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleCounts value) {
        if (value.getArticleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getArticleId());
        }
        stmt.bindLong(2, value.getLikes());
        stmt.bindLong(3, value.getComments());
        stmt.bindLong(4, value.getReadDuration());
        final long _tmp;
        _tmp = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(5, _tmp);
      }
    };
    this.__updateAdapterOfArticleCounts = new EntityDeletionOrUpdateAdapter<ArticleCounts>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `article_counts` SET `article_id` = ?,`likes` = ?,`comments` = ?,`read_duration` = ?,`updated_at` = ? WHERE `article_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleCounts value) {
        if (value.getArticleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getArticleId());
        }
        stmt.bindLong(2, value.getLikes());
        stmt.bindLong(3, value.getComments());
        stmt.bindLong(4, value.getReadDuration());
        final long _tmp;
        _tmp = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(5, _tmp);
        if (value.getArticleId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getArticleId());
        }
      }
    };
    this.__preparedStmtOfIncrementLike = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "            UPDATE article_counts SET likes = likes+1, updated_at =  CURRENT_TIMESTAMP\n"
                + "            WHERE article_id = ?\n"
                + "        ";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementLike = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "            UPDATE article_counts SET likes = MAX(0, likes-1), updated_at =  CURRENT_TIMESTAMP\n"
                + "            WHERE article_id = ?\n"
                + "        ";
        return _query;
      }
    };
    this.__preparedStmtOfIncrementCommentsCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "            UPDATE article_counts SET comments = comments+1, updated_at =  CURRENT_TIMESTAMP\n"
                + "            WHERE article_id = ?\n"
                + "        ";
        return _query;
      }
    };
  }

  @Override
  public List<Long> insert(final List<? extends ArticleCounts> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfArticleCounts.insertAndReturnIdsList(list);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(final ArticleCounts obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfArticleCounts.insertAndReturnId(obj);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final List<? extends ArticleCounts> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticleCounts.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ArticleCounts obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticleCounts.handle(obj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsert(final List<ArticleCounts> list) {
    __db.beginTransaction();
    try {
      ArticleCountsDao.DefaultImpls.upsert(ArticleCountsDao_Impl.this, list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int incrementLike(final String articleId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementLike.acquire();
    int _argIndex = 1;
    if (articleId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, articleId);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfIncrementLike.release(_stmt);
    }
  }

  @Override
  public int decrementLike(final String articleId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementLike.acquire();
    int _argIndex = 1;
    if (articleId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, articleId);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDecrementLike.release(_stmt);
    }
  }

  @Override
  public int incrementCommentsCount(final String articleId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementCommentsCount.acquire();
    int _argIndex = 1;
    if (articleId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, articleId);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfIncrementCommentsCount.release(_stmt);
    }
  }

  @Override
  public LiveData<List<ArticleCounts>> findArticleCounts() {
    final String _sql = "\n"
            + "            SELECT * FROM article_counts\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"article_counts"}, false, new Callable<List<ArticleCounts>>() {
      @Override
      public List<ArticleCounts> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArticleId = CursorUtil.getColumnIndexOrThrow(_cursor, "article_id");
          final int _cursorIndexOfLikes = CursorUtil.getColumnIndexOrThrow(_cursor, "likes");
          final int _cursorIndexOfComments = CursorUtil.getColumnIndexOrThrow(_cursor, "comments");
          final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "read_duration");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ArticleCounts> _result = new ArrayList<ArticleCounts>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticleCounts _item;
            final String _tmpArticleId;
            _tmpArticleId = _cursor.getString(_cursorIndexOfArticleId);
            final int _tmpLikes;
            _tmpLikes = _cursor.getInt(_cursorIndexOfLikes);
            final int _tmpComments;
            _tmpComments = _cursor.getInt(_cursorIndexOfComments);
            final int _tmpReadDuration;
            _tmpReadDuration = _cursor.getInt(_cursorIndexOfReadDuration);
            final Date _tmpUpdatedAt;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp);
            _item = new ArticleCounts(_tmpArticleId,_tmpLikes,_tmpComments,_tmpReadDuration,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<ArticleCounts> findArticleCounts(final String id) {
    final String _sql = "\n"
            + "            SELECT * FROM article_counts\n"
            + "            WHERE article_id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"article_counts"}, false, new Callable<ArticleCounts>() {
      @Override
      public ArticleCounts call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArticleId = CursorUtil.getColumnIndexOrThrow(_cursor, "article_id");
          final int _cursorIndexOfLikes = CursorUtil.getColumnIndexOrThrow(_cursor, "likes");
          final int _cursorIndexOfComments = CursorUtil.getColumnIndexOrThrow(_cursor, "comments");
          final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "read_duration");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final ArticleCounts _result;
          if(_cursor.moveToFirst()) {
            final String _tmpArticleId;
            _tmpArticleId = _cursor.getString(_cursorIndexOfArticleId);
            final int _tmpLikes;
            _tmpLikes = _cursor.getInt(_cursorIndexOfLikes);
            final int _tmpComments;
            _tmpComments = _cursor.getInt(_cursorIndexOfComments);
            final int _tmpReadDuration;
            _tmpReadDuration = _cursor.getInt(_cursorIndexOfReadDuration);
            final Date _tmpUpdatedAt;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp);
            _result = new ArticleCounts(_tmpArticleId,_tmpLikes,_tmpComments,_tmpReadDuration,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getCommentsCount(final String articleId) {
    final String _sql = "\n"
            + "            SELECT comments FROM article_counts\n"
            + "            WHERE article_id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (articleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, articleId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"article_counts"}, false, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getInt(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
