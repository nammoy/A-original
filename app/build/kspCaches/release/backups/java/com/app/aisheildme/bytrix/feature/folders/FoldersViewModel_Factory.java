package com.app.aisheildme.bytrix.feature.folders;

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
public final class FoldersViewModel_Factory implements Factory<FoldersViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public FoldersViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public FoldersViewModel get() {
    return newInstance(documentRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static FoldersViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new FoldersViewModel_Factory(documentRepositoryProvider, savedStateHandleProvider);
  }

  public static FoldersViewModel newInstance(DocumentRepository documentRepository,
      SavedStateHandle savedStateHandle) {
    return new FoldersViewModel(documentRepository, savedStateHandle);
  }
}
