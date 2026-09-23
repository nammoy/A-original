package com.app.aisheildme.bytrix.feature.share;

import androidx.lifecycle.SavedStateHandle;
import com.app.aisheildme.bytrix.data.repository.DocumentRepository;
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
public final class ShareViewModel_Factory implements Factory<ShareViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ShareViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ShareViewModel get() {
    return newInstance(documentRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ShareViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ShareViewModel_Factory(documentRepositoryProvider, savedStateHandleProvider);
  }

  public static ShareViewModel newInstance(DocumentRepository documentRepository,
      SavedStateHandle savedStateHandle) {
    return new ShareViewModel(documentRepository, savedStateHandle);
  }
}
