package com.app.aisheildme.bytrix.feature.profile;

import com.app.aisheildme.bytrix.data.repository.ContentRepository;
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
public final class AvailabilityViewModel_Factory implements Factory<AvailabilityViewModel> {
  private final Provider<ContentRepository> contentRepositoryProvider;

  public AvailabilityViewModel_Factory(Provider<ContentRepository> contentRepositoryProvider) {
    this.contentRepositoryProvider = contentRepositoryProvider;
  }

  @Override
  public AvailabilityViewModel get() {
    return newInstance(contentRepositoryProvider.get());
  }

  public static AvailabilityViewModel_Factory create(
      Provider<ContentRepository> contentRepositoryProvider) {
    return new AvailabilityViewModel_Factory(contentRepositoryProvider);
  }

  public static AvailabilityViewModel newInstance(ContentRepository contentRepository) {
    return new AvailabilityViewModel(contentRepository);
  }
}
