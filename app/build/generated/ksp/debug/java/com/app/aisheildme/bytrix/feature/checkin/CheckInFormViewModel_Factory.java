package com.app.aisheildme.bytrix.feature.checkin;

import com.app.aisheildme.bytrix.core.common.DetailHandoff;
import com.app.aisheildme.bytrix.data.ai.DocumentTextReader;
import com.app.aisheildme.bytrix.data.repository.AuthRepository;
import com.app.aisheildme.bytrix.data.repository.CheckInAiRepository;
import com.app.aisheildme.bytrix.data.repository.CheckInHistoryRepository;
import com.app.aisheildme.bytrix.data.repository.PatientProfileRepository;
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
public final class CheckInFormViewModel_Factory implements Factory<CheckInFormViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<PatientProfileRepository> profileRepositoryProvider;

  private final Provider<CheckInAiRepository> aiRepositoryProvider;

  private final Provider<CheckInHistoryRepository> historyRepositoryProvider;

  private final Provider<DocumentTextReader> textReaderProvider;

  private final Provider<DetailHandoff> handoffProvider;

  public CheckInFormViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<PatientProfileRepository> profileRepositoryProvider,
      Provider<CheckInAiRepository> aiRepositoryProvider,
      Provider<CheckInHistoryRepository> historyRepositoryProvider,
      Provider<DocumentTextReader> textReaderProvider, Provider<DetailHandoff> handoffProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.aiRepositoryProvider = aiRepositoryProvider;
    this.historyRepositoryProvider = historyRepositoryProvider;
    this.textReaderProvider = textReaderProvider;
    this.handoffProvider = handoffProvider;
  }

  @Override
  public CheckInFormViewModel get() {
    return newInstance(authRepositoryProvider.get(), profileRepositoryProvider.get(), aiRepositoryProvider.get(), historyRepositoryProvider.get(), textReaderProvider.get(), handoffProvider.get());
  }

  public static CheckInFormViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<PatientProfileRepository> profileRepositoryProvider,
      Provider<CheckInAiRepository> aiRepositoryProvider,
      Provider<CheckInHistoryRepository> historyRepositoryProvider,
      Provider<DocumentTextReader> textReaderProvider, Provider<DetailHandoff> handoffProvider) {
    return new CheckInFormViewModel_Factory(authRepositoryProvider, profileRepositoryProvider, aiRepositoryProvider, historyRepositoryProvider, textReaderProvider, handoffProvider);
  }

  public static CheckInFormViewModel newInstance(AuthRepository authRepository,
      PatientProfileRepository profileRepository, CheckInAiRepository aiRepository,
      CheckInHistoryRepository historyRepository, DocumentTextReader textReader,
      DetailHandoff handoff) {
    return new CheckInFormViewModel(authRepository, profileRepository, aiRepository, historyRepository, textReader, handoff);
  }
}
