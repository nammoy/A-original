package com.app.aisheildme.bytrix.feature.healthcare;

import com.app.aisheildme.bytrix.data.repository.CheckInHistoryRepository;
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
public final class HealthcareViewModel_Factory implements Factory<HealthcareViewModel> {
  private final Provider<RecordRepository> recordRepositoryProvider;

  private final Provider<CheckInHistoryRepository> checkInHistoryRepositoryProvider;

  public HealthcareViewModel_Factory(Provider<RecordRepository> recordRepositoryProvider,
      Provider<CheckInHistoryRepository> checkInHistoryRepositoryProvider) {
    this.recordRepositoryProvider = recordRepositoryProvider;
    this.checkInHistoryRepositoryProvider = checkInHistoryRepositoryProvider;
  }

  @Override
  public HealthcareViewModel get() {
    return newInstance(recordRepositoryProvider.get(), checkInHistoryRepositoryProvider.get());
  }

  public static HealthcareViewModel_Factory create(
      Provider<RecordRepository> recordRepositoryProvider,
      Provider<CheckInHistoryRepository> checkInHistoryRepositoryProvider) {
    return new HealthcareViewModel_Factory(recordRepositoryProvider, checkInHistoryRepositoryProvider);
  }

  public static HealthcareViewModel newInstance(RecordRepository recordRepository,
      CheckInHistoryRepository checkInHistoryRepository) {
    return new HealthcareViewModel(recordRepository, checkInHistoryRepository);
  }
}
