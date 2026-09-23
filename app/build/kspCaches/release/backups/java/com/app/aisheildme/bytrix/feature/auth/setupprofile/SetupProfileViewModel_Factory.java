package com.app.aisheildme.bytrix.feature.auth.setupprofile;

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
public final class SetupProfileViewModel_Factory implements Factory<SetupProfileViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public SetupProfileViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SetupProfileViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SetupProfileViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new SetupProfileViewModel_Factory(authRepositoryProvider);
  }

  public static SetupProfileViewModel newInstance(AuthRepository authRepository) {
    return new SetupProfileViewModel(authRepository);
  }
}
