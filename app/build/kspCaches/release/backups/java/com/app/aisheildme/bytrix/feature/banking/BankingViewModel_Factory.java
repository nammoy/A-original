package com.app.aisheildme.bytrix.feature.banking;

import com.app.aisheildme.bytrix.data.repository.RecordRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class BankingViewModel_Factory implements Factory<BankingViewModel> {
  private final Provider<RecordRepository> recordRepositoryProvider;

  public BankingViewModel_Factory(Provider<RecordRepository> recordRepositoryProvider) {
    this.recordRepositoryProvider = recordRepositoryProvider;
  }

  @Override
  public BankingViewModel get() {
    return newInstance(recordRepositoryProvider.get());
  }

  public static BankingViewModel_Factory create(
      Provider<RecordRepository> recordRepositoryProvider) {
    return new BankingViewModel_Factory(recordRepositoryProvider);
  }

  public static BankingViewModel newInstance(RecordRepository recordRepository) {
    return new BankingViewModel(recordRepository);
  }
}
