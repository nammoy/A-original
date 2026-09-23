package com.app.aisheildme.bytrix.data.repository;

import com.app.aisheildme.bytrix.data.local.db.CheckInHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class CheckInHistoryRepository_Factory implements Factory<CheckInHistoryRepository> {
  private final Provider<CheckInHistoryDao> daoProvider;

  public CheckInHistoryRepository_Factory(Provider<CheckInHistoryDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public CheckInHistoryRepository get() {
    return newInstance(daoProvider.get());
  }

  public static CheckInHistoryRepository_Factory create(Provider<CheckInHistoryDao> daoProvider) {
    return new CheckInHistoryRepository_Factory(daoProvider);
  }

  public static CheckInHistoryRepository newInstance(CheckInHistoryDao dao) {
    return new CheckInHistoryRepository(dao);
  }
}
