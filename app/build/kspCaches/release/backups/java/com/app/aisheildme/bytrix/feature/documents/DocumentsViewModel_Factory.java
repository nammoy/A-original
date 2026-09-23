package com.app.aisheildme.bytrix.feature.documents;

import androidx.lifecycle.SavedStateHandle;
import com.app.aisheildme.bytrix.core.util.FileOpener;
import com.app.aisheildme.bytrix.data.repository.AuthRepository;
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
public final class DocumentsViewModel_Factory implements Factory<DocumentsViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<FileOpener> fileOpenerProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public DocumentsViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider, Provider<FileOpener> fileOpenerProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.fileOpenerProvider = fileOpenerProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public DocumentsViewModel get() {
    return newInstance(documentRepositoryProvider.get(), authRepositoryProvider.get(), fileOpenerProvider.get(), savedStateHandleProvider.get());
  }

  public static DocumentsViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider, Provider<FileOpener> fileOpenerProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new DocumentsViewModel_Factory(documentRepositoryProvider, authRepositoryProvider, fileOpenerProvider, savedStateHandleProvider);
  }

  public static DocumentsViewModel newInstance(DocumentRepository documentRepository,
      AuthRepository authRepository, FileOpener fileOpener, SavedStateHandle savedStateHandle) {
    return new DocumentsViewModel(documentRepository, authRepository, fileOpener, savedStateHandle);
  }
}
