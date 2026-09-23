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
public final class EditProfileViewModel_Factory implements Factory<EditProfileViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public EditProfileViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public EditProfileViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static EditProfileViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new EditProfileViewModel_Factory(authRepositoryProvider);
  }

  public static EditProfileViewModel newInstance(AuthRepository authRepository) {
    return new EditProfileViewModel(authRepository);
  }
}
