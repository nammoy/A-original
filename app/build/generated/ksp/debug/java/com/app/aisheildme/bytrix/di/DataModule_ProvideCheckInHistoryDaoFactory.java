package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.local.db.AppDatabase;
import com.app.aisheildme.bytrix.data.local.db.CheckInHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DataModule_ProvideCheckInHistoryDaoFactory implements Factory<CheckInHistoryDao> {
  private final Provider<AppDatabase> dbProvider;

  public DataModule_ProvideCheckInHistoryDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CheckInHistoryDao get() {
    return provideCheckInHistoryDao(dbProvider.get());
  }

  public static DataModule_ProvideCheckInHistoryDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DataModule_ProvideCheckInHistoryDaoFactory(dbProvider);
  }

  public static CheckInHistoryDao provideCheckInHistoryDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideCheckInHistoryDao(db));
  }
}
