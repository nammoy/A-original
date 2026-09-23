package com.app.aisheildme.bytrix.feature.profile;

import com.app.aisheildme.bytrix.data.repository.AuthRepository;
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
public final class ResetPinViewModel_Factory implements Factory<ResetPinViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public ResetPinViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ResetPinViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static ResetPinViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new ResetPinViewModel_Factory(authRepositoryProvider);
  }

  public static ResetPinViewModel newInstance(AuthRepository authRepository) {
    return new ResetPinViewModel(authRepository);
  }
}
