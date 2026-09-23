package com.app.aisheildme.bytrix.feature.profile;

import androidx.lifecycle.SavedStateHandle;
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
public final class StaticPageViewModel_Factory implements Factory<StaticPageViewModel> {
  private final Provider<ContentRepository> contentRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public StaticPageViewModel_Factory(Provider<ContentRepository> contentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.contentRepositoryProvider = contentRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public StaticPageViewModel get() {
    return newInstance(contentRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static StaticPageViewModel_Factory create(
      Provider<ContentRepository> contentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new StaticPageViewModel_Factory(contentRepositoryProvider, savedStateHandleProvider);
  }

  public static StaticPageViewModel newInstance(ContentRepository contentRepository,
      SavedStateHandle savedStateHandle) {
    return new StaticPageViewModel(contentRepository, savedStateHandle);
  }
}
