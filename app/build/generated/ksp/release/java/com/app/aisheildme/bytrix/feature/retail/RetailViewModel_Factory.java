package com.app.aisheildme.bytrix.feature.retail;

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
public final class RetailViewModel_Factory implements Factory<RetailViewModel> {
  private final Provider<RecordRepository> recordRepositoryProvider;

  public RetailViewModel_Factory(Provider<RecordRepository> recordRepositoryProvider) {
    this.recordRepositoryProvider = recordRepositoryProvider;
  }

  @Override
  public RetailViewModel get() {
    return newInstance(recordRepositoryProvider.get());
  }

  public static RetailViewModel_Factory create(
      Provider<RecordRepository> recordRepositoryProvider) {
    return new RetailViewModel_Factory(recordRepositoryProvider);
  }

  public static RetailViewModel newInstance(RecordRepository recordRepository) {
    return new RetailViewModel(recordRepository);
  }
}
