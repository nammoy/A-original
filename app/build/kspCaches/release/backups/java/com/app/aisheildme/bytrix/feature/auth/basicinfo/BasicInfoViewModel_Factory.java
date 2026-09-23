package com.app.aisheildme.bytrix.feature.auth.basicinfo;

import androidx.lifecycle.SavedStateHandle;
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
public final class BasicInfoViewModel_Factory implements Factory<BasicInfoViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public BasicInfoViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public BasicInfoViewModel get() {
    return newInstance(authRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static BasicInfoViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new BasicInfoViewModel_Factory(authRepositoryProvider, savedStateHandleProvider);
  }

  public static BasicInfoViewModel newInstance(AuthRepository authRepository,
      SavedStateHandle savedStateHandle) {
    return new BasicInfoViewModel(authRepository, savedStateHandle);
  }
}
