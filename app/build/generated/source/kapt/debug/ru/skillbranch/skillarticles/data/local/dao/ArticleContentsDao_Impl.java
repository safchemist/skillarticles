package ru.skillbranch.skillarticles.data.local.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import ru.skillbranch.skillarticles.data.local.Converters;
import ru.skillbranch.skillarticles.data.local.entities.ArticleContent;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleContentsDao_Impl implements ArticleContentsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleContent> __insertionAdapterOfArticleContent;

  private final Converters __converters = new Converters();

  public ArticleContentsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleContent = new EntityInsertionAdapter<ArticleContent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `article_contents` (`article_id`,`content`,`source`,`share_link`,`updated_at`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleContent value) {
        if (value.getArticleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getArticleId());
        }
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        if (value.getSource() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSource());
        }
        if (value.getShareLink() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getShareLink());
        }
        final long _tmp;
        _tmp = __converters.dateToTimestamp(value.getUpdatedAt());
        stmt.bindLong(5, _tmp);
      }
    };
  }

  @Override
  public long insert(final ArticleContent obj) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfArticleContent.insertAndReturnId(obj);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }
}
