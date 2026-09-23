package com.app.aisheildme.bytrix.feature.vault;

import com.app.aisheildme.bytrix.core.util.BiometricAvailability;
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
public final class VaultGateViewModel_Factory implements Factory<VaultGateViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<BiometricAvailability> biometricAvailabilityProvider;

  public VaultGateViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<BiometricAvailability> biometricAvailabilityProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.biometricAvailabilityProvider = biometricAvailabilityProvider;
  }

  @Override
  public VaultGateViewModel get() {
    return newInstance(authRepositoryProvider.get(), biometricAvailabilityProvider.get());
  }

  public static VaultGateViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<BiometricAvailability> biometricAvailabilityProvider) {
    return new VaultGateViewModel_Factory(authRepositoryProvider, biometricAvailabilityProvider);
  }

  public static VaultGateViewModel newInstance(AuthRepository authRepository,
      BiometricAvailability biometricAvailability) {
    return new VaultGateViewModel(authRepository, biometricAvailability);
  }
}
