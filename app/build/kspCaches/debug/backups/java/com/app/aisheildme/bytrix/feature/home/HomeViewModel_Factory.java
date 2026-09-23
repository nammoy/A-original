package com.app.aisheildme.bytrix.feature.home;

import com.app.aisheildme.bytrix.data.repository.AuthRepository;
import com.app.aisheildme.bytrix.data.repository.DocumentRepository;
import com.app.aisheildme.bytrix.data.repository.RecordRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<RecordRepository> recordRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public HomeViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<RecordRepository> recordRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.recordRepositoryProvider = recordRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(documentRepositoryProvider.get(), recordRepositoryProvider.get(), authRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<RecordRepository> recordRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new HomeViewModel_Factory(documentRepositoryProvider, recordRepositoryProvider, authRepositoryProvider);
  }

  public static HomeViewModel newInstance(DocumentRepository documentRepository,
      RecordRepository recordRepository, AuthRepository authRepository) {
    return new HomeViewModel(documentRepository, recordRepository, authRepository);
  }
}
