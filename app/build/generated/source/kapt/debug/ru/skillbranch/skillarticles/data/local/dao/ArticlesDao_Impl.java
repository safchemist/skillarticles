package ru.skillbranch.skillarticles.data.local.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import ru.skillbranch.skillarticles.data.local.Converters;
import ru.skillbranch.skillarticles.data.local.MarkdownConverter;
import ru.skillbranch.skillarticles.data.local.entities.Article;
import ru.skillbranch.skillarticles.data.local.entities.ArticleFull;
import ru.skillbranch.skillarticles.data.local.entities.ArticleItem;
import ru.skillbranch.skillarticles.data.local.entities.Author;
import ru.skillbranch.skillarticles.data.local.entities.Category;
import ru.skillbranch.skillarticles.data.repositories.MarkdownElement;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticlesDao_Impl implements ArticlesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Article> __insertionAdapterOfArticle;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Article> __deletionAdapterOfArticle;

  private final EntityDeletionOrUpdateAdapter<Article> __updateAdapterOfArticle;

  private final MarkdownConverter __markdownConverter = new MarkdownConverter();

  public ArticlesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticle = new EntityInsertionAdapter<Article>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `articles` (`id`,`title`,`description`,`category_id`,`poster`,`date`,`updated_at`,`author_user_id`,`author_avatar`,`author_name`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Article value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getCategoryId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCategoryId());
        }
        if (value.getPoster() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPoster());
        }
        final long _tmp;
        _tmp = __converters.dateToTimestamp(value.getDate());
        stmt.bindLong(6, _tmp);
        final long _tmp_1;
        _tmp_1 = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(7, _tmp_1);
        final Author _tmpAuthor = value.getAuthor();
        if(_tmpAuthor != null) {
          if (_tmpAuthor.getUserId() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, _tmpAuthor.getUserId());
          }
          if (_tmpAuthor.getAvatar() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmpAuthor.getAvatar());
          }
          if (_tmpAuthor.getName() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpAuthor.getName());
          }
        } else {
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
      }
    };
    this.__deletionAdapterOfArticle = new EntityDeletionOrUpdateAdapter<Article>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `articles` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Article value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfArticle = new EntityDeletionOrUpdateAdapter<Article>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `articles` SET `id` = ?,`title` = ?,`description` = ?,`category_id` = ?,`poster` = ?,`date` = ?,`updated_at` = ?,`author_user_id` = ?,`author_avatar` = ?,`author_name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Article value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getCategoryId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCategoryId());
        }
        if (value.getPoster() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPoster());
        }
        final long _tmp;
        _tmp = __converters.dateToTimestamp(value.getDate());
        stmt.bindLong(6, _tmp);
        final long _tmp_1;
        _tmp_1 = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(7, _tmp_1);
        final Author _tmpAuthor = value.getAuthor();
        if(_tmpAuthor != null) {
          if (_tmpAuthor.getUserId() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, _tmpAuthor.getUserId());
          }
          if (_tmpAuthor.getAvatar() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmpAuthor.getAvatar());
          }
          if (_tmpAuthor.getName() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpAuthor.getName());
          }
        } else {
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
        if (value.getId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getId());
        }
      }
    };
  }

  @Override
  public List<Long> insert(final List<? extends Article> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfArticle.insertAndReturnIdsList(list);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(final Article obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfArticle.insertAndReturnId(obj);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Article article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfArticle.handle(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final List<? extends Article> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticle.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Article obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArticle.handle(obj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsert(final List<Article> list) {
    __db.beginTransaction();
    try {
      ArticlesDao.DefaultImpls.upsert(ArticlesDao_Impl.this, list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Article>> findArticles() {
    final String _sql = "\n"
            + "            SELECT * FROM articles\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"articles"}, false, new Callable<List<Article>>() {
      @Override
      public List<Article> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "author_user_id");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "author_name");
          final List<Article> _result = new ArrayList<Article>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Article _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpCategoryId;
            _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final Date _tmpDate;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __converters.timestampToDate(_tmp);
            final Date _tmpUpdatedAt;
            final long _tmp_1;
            _tmp_1 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp_1);
            final Author _tmpAuthor;
            if (! (_cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfAvatar) && _cursor.isNull(_cursorIndexOfName))) {
              final String _tmpUserId;
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
              final String _tmpAvatar;
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _tmpAuthor = new Author(_tmpUserId,_tmpAvatar,_tmpName);
            }  else  {
              _tmpAuthor = null;
            }
            _item = new Article(_tmpId,_tmpTitle,_tmpDescription,_tmpAuthor,_tmpCategoryId,_tmpPoster,_tmpDate,_tmpUpdatedAt);
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
  public LiveData<Article> findArticleById(final String id) {
    final String _sql = "\n"
            + "            SELECT * FROM articles\n"
            + "            WHERE id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"articles"}, false, new Callable<Article>() {
      @Override
      public Article call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "author_user_id");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "author_name");
          final Article _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpCategoryId;
            _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final Date _tmpDate;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __converters.timestampToDate(_tmp);
            final Date _tmpUpdatedAt;
            final long _tmp_1;
            _tmp_1 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __converters.timestampToDate(_tmp_1);
            final Author _tmpAuthor;
            if (! (_cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfAvatar) && _cursor.isNull(_cursorIndexOfName))) {
              final String _tmpUserId;
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
              final String _tmpAvatar;
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _tmpAuthor = new Author(_tmpUserId,_tmpAvatar,_tmpName);
            }  else  {
              _tmpAuthor = null;
            }
            _result = new Article(_tmpId,_tmpTitle,_tmpDescription,_tmpAuthor,_tmpCategoryId,_tmpPoster,_tmpDate,_tmpUpdatedAt);
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
  public LiveData<List<ArticleItem>> findArticleItems() {
    final String _sql = "\n"
            + "            SELECT * FROM ArticleItem\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ArticleItem"}, false, new Callable<List<ArticleItem>>() {
      @Override
      public List<ArticleItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfAuthorAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "like_count");
          final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
          final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "read_duration");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCategoryIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "category_icon");
          final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
          final List<ArticleItem> _result = new ArrayList<ArticleItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticleItem _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final Date _tmpDate;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __converters.timestampToDate(_tmp);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpAuthorAvatar;
            _tmpAuthorAvatar = _cursor.getString(_cursorIndexOfAuthorAvatar);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpCategoryId;
            _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            final int _tmpLikeCount;
            _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
            final int _tmpCommentCount;
            _tmpCommentCount = _cursor.getInt(_cursorIndexOfCommentCount);
            final int _tmpReadDuration;
            _tmpReadDuration = _cursor.getInt(_cursorIndexOfReadDuration);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCategoryIcon;
            _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            final boolean _tmpIsBookmark;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsBookmark);
            _tmpIsBookmark = _tmp_1 != 0;
            _item = new ArticleItem(_tmpId,_tmpDate,_tmpAuthor,_tmpAuthorAvatar,_tmpTitle,_tmpDescription,_tmpPoster,_tmpCategoryId,_tmpCategory,_tmpCategoryIcon,_tmpLikeCount,_tmpCommentCount,_tmpReadDuration,_tmpIsBookmark);
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
  public List<ArticleItem> findArticleItemsByCategoryIds(final List<String> categoryIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("            SELECT ");
    _stringBuilder.append("*");
    _stringBuilder.append(" FROM ArticleItem");
    _stringBuilder.append("\n");
    _stringBuilder.append("            WHERE category_id IN (");
    final int _inputSize = categoryIds.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("        ");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : categoryIds) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfAuthorAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "like_count");
      final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
      final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "read_duration");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfCategoryIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "category_icon");
      final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
      final List<ArticleItem> _result = new ArrayList<ArticleItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ArticleItem _item_1;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = __converters.timestampToDate(_tmp);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        final String _tmpAuthorAvatar;
        _tmpAuthorAvatar = _cursor.getString(_cursorIndexOfAuthorAvatar);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpPoster;
        _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
        final String _tmpCategoryId;
        _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
        final int _tmpLikeCount;
        _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
        final int _tmpCommentCount;
        _tmpCommentCount = _cursor.getInt(_cursorIndexOfCommentCount);
        final int _tmpReadDuration;
        _tmpReadDuration = _cursor.getInt(_cursorIndexOfReadDuration);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpCategoryIcon;
        _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
        final boolean _tmpIsBookmark;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsBookmark);
        _tmpIsBookmark = _tmp_1 != 0;
        _item_1 = new ArticleItem(_tmpId,_tmpDate,_tmpAuthor,_tmpAuthorAvatar,_tmpTitle,_tmpDescription,_tmpPoster,_tmpCategoryId,_tmpCategory,_tmpCategoryIcon,_tmpLikeCount,_tmpCommentCount,_tmpReadDuration,_tmpIsBookmark);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ArticleItem> findArticlesByTagId(final String tag) {
    final String _sql = "\n"
            + "            SELECT * FROM ArticleItem\n"
            + "            INNER JOIN article_tag_x_ref AS refs ON refs.a_id = id WHERE refs.t_id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (tag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, tag);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfAuthorAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "like_count");
      final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
      final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "read_duration");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfCategoryIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "category_icon");
      final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
      final List<ArticleItem> _result = new ArrayList<ArticleItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ArticleItem _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = __converters.timestampToDate(_tmp);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        final String _tmpAuthorAvatar;
        _tmpAuthorAvatar = _cursor.getString(_cursorIndexOfAuthorAvatar);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpPoster;
        _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
        final String _tmpCategoryId;
        _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
        final int _tmpLikeCount;
        _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
        final int _tmpCommentCount;
        _tmpCommentCount = _cursor.getInt(_cursorIndexOfCommentCount);
        final int _tmpReadDuration;
        _tmpReadDuration = _cursor.getInt(_cursorIndexOfReadDuration);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpCategoryIcon;
        _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
        final boolean _tmpIsBookmark;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsBookmark);
        _tmpIsBookmark = _tmp_1 != 0;
        _item = new ArticleItem(_tmpId,_tmpDate,_tmpAuthor,_tmpAuthorAvatar,_tmpTitle,_tmpDescription,_tmpPoster,_tmpCategoryId,_tmpCategory,_tmpCategoryIcon,_tmpLikeCount,_tmpCommentCount,_tmpReadDuration,_tmpIsBookmark);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<ArticleFull> findFullArticle(final String articleId) {
    final String _sql = "\n"
            + "            SELECT * FROM ArticleFull\n"
            + "            WHERE id = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (articleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, articleId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"ArticleFull"}, false, new Callable<ArticleFull>() {
      @Override
      public ArticleFull call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "author_user_id");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "author_avatar");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "author_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_category_id");
          final int _cursorIndexOfTitle_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "category_title");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "category_icon");
          final int _cursorIndexOfShareLink = CursorUtil.getColumnIndexOrThrow(_cursor, "share_link");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndexOrThrow(_cursor, "is_bookmark");
          final int _cursorIndexOfIsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "is_like");
          final ArticleFull _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Date _tmpDate;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __converters.timestampToDate(_tmp);
            final String _tmpShareLink;
            _tmpShareLink = _cursor.getString(_cursorIndexOfShareLink);
            final List<MarkdownElement> _tmpContent;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfContent);
            _tmpContent = __markdownConverter.toMarkdown(_tmp_1);
            final boolean _tmpIsBookmark;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsBookmark);
            _tmpIsBookmark = _tmp_2 != 0;
            final boolean _tmpIsLike;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLike);
            _tmpIsLike = _tmp_3 != 0;
            final Author _tmpAuthor;
            if (! (_cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfAvatar) && _cursor.isNull(_cursorIndexOfName))) {
              final String _tmpUserId;
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
              final String _tmpAvatar;
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _tmpAuthor = new Author(_tmpUserId,_tmpAvatar,_tmpName);
            }  else  {
              _tmpAuthor = null;
            }
            final Category _tmpCategory;
            if (! (_cursor.isNull(_cursorIndexOfCategoryId) && _cursor.isNull(_cursorIndexOfTitle_1) && _cursor.isNull(_cursorIndexOfIcon))) {
              final String _tmpCategoryId;
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
              final String _tmpTitle_1;
              _tmpTitle_1 = _cursor.getString(_cursorIndexOfTitle_1);
              final String _tmpIcon;
              _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
              _tmpCategory = new Category(_tmpCategoryId,_tmpIcon,_tmpTitle_1);
            }  else  {
              _tmpCategory = null;
            }
            _result = new ArticleFull(_tmpId,_tmpTitle,_tmpDescription,_tmpAuthor,_tmpCategory,_tmpShareLink,_tmpIsBookmark,_tmpIsLike,_tmpDate,_tmpContent);
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
  public DataSource.Factory<Integer, ArticleItem> findArticlesByRaw(final SimpleSQLiteQuery simpleSQLiteQuery) {
    final SupportSQLiteQuery _internalQuery = simpleSQLiteQuery;
    return new DataSource.Factory<Integer, ArticleItem>() {
      @Override
      public LimitOffsetDataSource<ArticleItem> create() {
        return new LimitOffsetDataSource<ArticleItem>(__db, _internalQuery, false , "ArticleItem") {
          @Override
          protected List<ArticleItem> convertRows(Cursor cursor) {
            final int _cursorIndexOfId = CursorUtil.getColumnIndex(cursor, "id");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndex(cursor, "date");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndex(cursor, "author");
            final int _cursorIndexOfAuthorAvatar = CursorUtil.getColumnIndex(cursor, "author_avatar");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndex(cursor, "title");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndex(cursor, "description");
            final int _cursorIndexOfPoster = CursorUtil.getColumnIndex(cursor, "poster");
            final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndex(cursor, "category_id");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndex(cursor, "category");
            final int _cursorIndexOfCategoryIcon = CursorUtil.getColumnIndex(cursor, "category_icon");
            final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndex(cursor, "like_count");
            final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndex(cursor, "comment_count");
            final int _cursorIndexOfReadDuration = CursorUtil.getColumnIndex(cursor, "read_duration");
            final int _cursorIndexOfIsBookmark = CursorUtil.getColumnIndex(cursor, "is_bookmark");
            final List<ArticleItem> _res = new ArrayList<ArticleItem>(cursor.getCount());
            while(cursor.moveToNext()) {
              final ArticleItem _item;
              final String _tmpId;
              if (_cursorIndexOfId == -1) {
                _tmpId = null;
              } else {
                _tmpId = cursor.getString(_cursorIndexOfId);
              }
              final Date _tmpDate;
              if (_cursorIndexOfDate == -1) {
                _tmpDate = null;
              } else {
                final long _tmp;
                _tmp = cursor.getLong(_cursorIndexOfDate);
                _tmpDate = __converters.timestampToDate(_tmp);
              }
              final String _tmpAuthor;
              if (_cursorIndexOfAuthor == -1) {
                _tmpAuthor = null;
              } else {
                _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              }
              final String _tmpAuthorAvatar;
              if (_cursorIndexOfAuthorAvatar == -1) {
                _tmpAuthorAvatar = null;
              } else {
                _tmpAuthorAvatar = cursor.getString(_cursorIndexOfAuthorAvatar);
              }
              final String _tmpTitle;
              if (_cursorIndexOfTitle == -1) {
                _tmpTitle = null;
              } else {
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              }
              final String _tmpDescription;
              if (_cursorIndexOfDescription == -1) {
                _tmpDescription = null;
              } else {
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpPoster;
              if (_cursorIndexOfPoster == -1) {
                _tmpPoster = null;
              } else {
                _tmpPoster = cursor.getString(_cursorIndexOfPoster);
              }
              final String _tmpCategoryId;
              if (_cursorIndexOfCategoryId == -1) {
                _tmpCategoryId = null;
              } else {
                _tmpCategoryId = cursor.getString(_cursorIndexOfCategoryId);
              }
              final String _tmpCategory;
              if (_cursorIndexOfCategory == -1) {
                _tmpCategory = null;
              } else {
                _tmpCategory = cursor.getString(_cursorIndexOfCategory);
              }
              final String _tmpCategoryIcon;
              if (_cursorIndexOfCategoryIcon == -1) {
                _tmpCategoryIcon = null;
              } else {
                _tmpCategoryIcon = cursor.getString(_cursorIndexOfCategoryIcon);
              }
              final int _tmpLikeCount;
              if (_cursorIndexOfLikeCount == -1) {
                _tmpLikeCount = 0;
              } else {
                _tmpLikeCount = cursor.getInt(_cursorIndexOfLikeCount);
              }
              final int _tmpCommentCount;
              if (_cursorIndexOfCommentCount == -1) {
                _tmpCommentCount = 0;
              } else {
                _tmpCommentCount = cursor.getInt(_cursorIndexOfCommentCount);
              }
              final int _tmpReadDuration;
              if (_cursorIndexOfReadDuration == -1) {
                _tmpReadDuration = 0;
              } else {
                _tmpReadDuration = cursor.getInt(_cursorIndexOfReadDuration);
              }
              final boolean _tmpIsBookmark;
              if (_cursorIndexOfIsBookmark == -1) {
                _tmpIsBookmark = false;
              } else {
                final int _tmp_1;
                _tmp_1 = cursor.getInt(_cursorIndexOfIsBookmark);
                _tmpIsBookmark = _tmp_1 != 0;
              }
              _item = new ArticleItem(_tmpId,_tmpDate,_tmpAuthor,_tmpAuthorAvatar,_tmpTitle,_tmpDescription,_tmpPoster,_tmpCategoryId,_tmpCategory,_tmpCategoryIcon,_tmpLikeCount,_tmpCommentCount,_tmpReadDuration,_tmpIsBookmark);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }
}
