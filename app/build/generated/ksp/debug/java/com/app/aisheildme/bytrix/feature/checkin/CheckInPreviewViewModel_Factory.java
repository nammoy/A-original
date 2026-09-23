package com.app.aisheildme.bytrix.feature.checkin;

import com.app.aisheildme.bytrix.core.common.DetailHandoff;
import com.app.aisheildme.bytrix.data.repository.PatientProfileRepository;
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
public final class CheckInPreviewViewModel_Factory implements Factory<CheckInPreviewViewModel> {
  private final Provider<PatientProfileRepository> profileRepositoryProvider;

  private final Provider<DetailHandoff> handoffProvider;

  public CheckInPreviewViewModel_Factory(
      Provider<PatientProfileRepository> profileRepositoryProvider,
      Provider<DetailHandoff> handoffProvider) {
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.handoffProvider = handoffProvider;
  }

  @Override
  public CheckInPreviewViewModel get() {
    return newInstance(profileRepositoryProvider.get(), handoffProvider.get());
  }

  public static CheckInPreviewViewModel_Factory create(
      Provider<PatientProfileRepository> profileRepositoryProvider,
      Provider<DetailHandoff> handoffProvider) {
    return new CheckInPreviewViewModel_Factory(profileRepositoryProvider, handoffProvider);
  }

  public static CheckInPreviewViewModel newInstance(PatientProfileRepository profileRepository,
      DetailHandoff handoff) {
    return new CheckInPreviewViewModel(profileRepository, handoff);
  }
}
