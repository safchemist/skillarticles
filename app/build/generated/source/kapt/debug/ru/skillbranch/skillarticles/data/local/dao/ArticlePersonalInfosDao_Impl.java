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
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import ru.skillbranch.skillarticles.data.local.Converters;
import ru.skillbranch.skillarticles.data.local.entities.ArticlePersonalInfo;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticlePersonalInfosDao_Impl implements ArticlePersonalInfosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticlePersonalInfo> __insertionAdapterOfArticlePersonalInfo;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ArticlePersonalInfo> __updateAdapterOfArticlePersonalInfo;

  private final SharedSQLiteStatement __preparedStmtOfToggleLike;

  private final SharedSQLiteStatement __preparedStmtOfToggleBookmark;

  public ArticlePersonalInfosDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticlePersonalInfo = new EntityInsertionAdapter<ArticlePersonalInfo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `article_personal_infos` (`article_id`,`is_like`,`is_bookmark`,`updated_at`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticlePersonalInfo value) {
        if (value.getArticleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getArticleId());
        }
        final int _tmp;
        _tmp = value.isLike() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isBookmark() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        final long _tmp_2;
        _tmp_2 = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(4, _tmp_2);
      }
    };
    this.__updateAdapterOfArticlePersonalInfo = new EntityDeletionOrUpdateAdapter<ArticlePersonalInfo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `article_personal_infos` SET `article_id` = ?,`is_like` = ?,`is_bookmark` = ?,`updated_at` = ? WHERE `article_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticlePersonalInfo value) {
        if (value.getArticleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getArticleId());
        }
        final int _tmp;
        _tmp = value.isLike() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isBookmark() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        final long _tmp_2;
        _tmp_2 = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(4, _tmp_2);
        if (value.getArticleId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getArticleId());
        }
      }
    };
    this.__preparedStmtOfToggleLike = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "            UPDATE article_personal_infos SET is_like = NOT is_like, updated_at = CURRENT_TIMESTAMP\n"
                + "            WHERE article_id = ?\n"
                + "        ";
        return _query;
      }
    };
    this.__preparedStmtOfToggleBookmark = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "            UPDATE article_personal_infos SET is_bookmark =  NOT is_bookmark, updated_at = CURRENT_TIMESTAMP\n"
                + "            WHERE article_id = ?\n"
                + "        ";
        return _query;
      }
    };
  }

  @Override
  public List<Long> insert(final List<? extends ArticlePersonalInfo> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfArticlePersonalInfo.insertAndReturnIdsList(list);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(final ArticlePersonalInfo obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfArticlePersonalInfo.insertAndReturnId(obj);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final List<? extends ArticlePersonalInfo> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticlePersonalInfo.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ArticlePersonalInfo obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticlePersonalInfo.handle(obj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsert(final List<ArticlePersonalInfo> list) {
    __db.beginTransaction();
    try {
      ArticlePersonalInfosDao.DefaultImpls.upsert(ArticlePersonalInfosDao_Impl.this, list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void toggleBookmarkOrInsert(final String articleId) {
    __db.beginTransaction();
    try {
      ArticlePersonalInfosDao.DefaultImpls.toggleBookmarkOrInsert(ArticlePersonalInfosDao_Impl.this, articleId);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void toggleLikeOrInsert(final String articleId) {
    __db.beginTransaction();
    try {
      ArticlePersonalInfosDao.DefaultImpls.toggleLikeOrInsert(ArticlePersonalInfosDao_Impl.this, articleId);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int toggleLike(final String articleId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfToggleLike.acquire();
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
      __preparedStmtOfToggleLike.release(_stmt);
    }
  }

  @Override
  public int toggleBookmark(final String articleId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfToggleBookmark.acquire();
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
      __preparedStmtOfToggleBookmark.release(_stmt);
    }
  }

  @Override
  public LiveData<List<ArticlePersonalInfo>> findPersonalInfos() {
    final String _sql = "\n"
            + "            SELECT * FROM article_personal_infos\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"article_personal_infos"}, false, new Callable<List<ArticlePersonalInfo>>() {
      @Override
      public List<ArticlePersonalInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArticleId = CursorUtil.getColumnIndexOrThrow(_cursor, "article_id");
          final int _cursorIndexOfIsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "is_like");
          final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ArticlePersonalInfo> _result = new ArrayList<ArticlePersonalInfo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticlePersonalInfo _item;
            final String _tmpArticleId;
            _tmpArticleId = _cursor.getString(_cursorIndexOfArticleId);
            final boolean _tmpIsLike;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLike);
            _tmpIsLike = _tmp != 0;
            final boolean _tmpIsBookmark;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsBookmark);
            _tmpIsBookmark = _tmp_1 != 0;
            final Date _tmpUpdatedAt;
            final long _tmp_2;
            _tmp_2 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp_2);
            _item = new ArticlePersonalInfo(_tmpArticleId,_tmpIsLike,_tmpIsBookmark,_tmpUpdatedAt);
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
  public LiveData<ArticlePersonalInfo> findPersonalInfos(final String id) {
    final String _sql = "\n"
            + "            SELECT * FROM article_personal_infos\n"
            + "            WHERE article_id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"article_personal_infos"}, false, new Callable<ArticlePersonalInfo>() {
      @Override
      public ArticlePersonalInfo call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArticleId = CursorUtil.getColumnIndexOrThrow(_cursor, "article_id");
          final int _cursorIndexOfIsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "is_like");
          final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final ArticlePersonalInfo _result;
          if(_cursor.moveToFirst()) {
            final String _tmpArticleId;
            _tmpArticleId = _cursor.getString(_cursorIndexOfArticleId);
            final boolean _tmpIsLike;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLike);
            _tmpIsLike = _tmp != 0;
            final boolean _tmpIsBookmark;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsBookmark);
            _tmpIsBookmark = _tmp_1 != 0;
            final Date _tmpUpdatedAt;
            final long _tmp_2;
            _tmp_2 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp_2);
            _result = new ArticlePersonalInfo(_tmpArticleId,_tmpIsLike,_tmpIsBookmark,_tmpUpdatedAt);
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
