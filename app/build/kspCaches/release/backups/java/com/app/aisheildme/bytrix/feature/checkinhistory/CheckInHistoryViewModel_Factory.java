package com.app.aisheildme.bytrix.feature.checkinhistory;

import com.app.aisheildme.bytrix.data.repository.CheckInHistoryRepository;
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
public final class CheckInHistoryViewModel_Factory implements Factory<CheckInHistoryViewModel> {
  private final Provider<CheckInHistoryRepository> repositoryProvider;

  public CheckInHistoryViewModel_Factory(Provider<CheckInHistoryRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CheckInHistoryViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CheckInHistoryViewModel_Factory create(
      Provider<CheckInHistoryRepository> repositoryProvider) {
    return new CheckInHistoryViewModel_Factory(repositoryProvider);
  }

  public static CheckInHistoryViewModel newInstance(CheckInHistoryRepository repository) {
    return new CheckInHistoryViewModel(repository);
  }
}
